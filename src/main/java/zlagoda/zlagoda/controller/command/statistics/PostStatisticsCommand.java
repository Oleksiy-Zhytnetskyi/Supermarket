package zlagoda.zlagoda.controller.command.statistics;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.*;
import zlagoda.zlagoda.entity.enums.UserRole;
import zlagoda.zlagoda.service.*;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PostStatisticsCommand implements Command {

    private final static UserService userService = UserService.getInstance();
    private final static ProductService productService = ProductService.getInstance();
    private final static SaleService saleService = SaleService.getInstance();
    private final static ReceiptService receiptService = ReceiptService.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        UserEntity userEntity = null;
        ProductEntity productEntity = null;
        if(!req.getParameter(Attribute.USER).equals("null"))
            userEntity = userService.getUserById(Integer.valueOf(req.getParameter(Attribute.USER))).get();
        if(!req.getParameter(Attribute.PRODUCT).equals("null"))
            productEntity = productService.getProductById(Integer.valueOf(req.getParameter(Attribute.PRODUCT))).get();
        LocalDate startDate = LocalDate.now().minusDays(30);
        LocalDate endDate = LocalDate.now();
        if (!req.getParameter("startDate").isBlank()) {
            startDate = LocalDate.parse(req.getParameter("startDate"));
        }
        if (!req.getParameter("endDate").isBlank()) {
            endDate = LocalDate.parse(req.getParameter("endDate"));
        }

        Double totalSum = 0.0;
        Integer totalQuantity = 0;
        List<SaleEntity> sales;
        if(productEntity != null) {
            sales = saleService.getSalesByProductAndTimePeriod(productEntity.getId(), startDate, endDate);
        } else {
            sales = saleService.getSalesByTimePeriod(startDate, endDate);
        }

        if(userEntity != null) {
            for (SaleEntity saleEntity : sales) {
                ReceiptEntity receipt = receiptService.getReceiptById(saleEntity.getPk().getReceiptId()).get();
                if(receipt.getUserId() == userEntity.getId()) {
                    totalSum += saleEntity.getSellingPrice();
                    totalQuantity += saleEntity.getProductQuantity();
                }
            }
        } else {
            for (SaleEntity saleEntity : sales) {
                totalSum += saleEntity.getSellingPrice();
                totalQuantity += saleEntity.getProductQuantity();
            }
        }
        req.setAttribute("totalSum", totalSum);
        req.setAttribute("totalQuantity", totalQuantity);
        List<UserEntity> cashiers = userService.searchUsersByRole(UserRole.CASHIER);
        List<ProductEntity> products = productService.getAllProducts();
        req.setAttribute(Attribute.USERS, cashiers);
        req.setAttribute(Attribute.PRODUCTS, products);
        return Page.ALL_STATISTICS;
    }
}
