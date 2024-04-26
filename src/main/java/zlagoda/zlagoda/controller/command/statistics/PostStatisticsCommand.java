package zlagoda.zlagoda.controller.command.statistics;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.SaleEntity;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.service.ProductService;
import zlagoda.zlagoda.service.SaleService;
import zlagoda.zlagoda.service.StoreProductService;
import zlagoda.zlagoda.service.UserService;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PostStatisticsCommand implements Command {

    private final static UserService userService = UserService.getInstance();
    private final static StoreProductService storeProductService = StoreProductService.getInstance();
    private final static ProductService productService = ProductService.getInstance();
    private final SaleService saleService = SaleService.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        UserEntity userEntity = null;
        StoreProductEntity storeProductEntity = null;
        if(!req.getParameter(Attribute.USER).equals("null"))
            userEntity = userService.getUserById(Integer.valueOf(req.getParameter(Attribute.USER))).get();
        if(!req.getParameter(Attribute.STORE_PRODUCT).equals("null"))
            storeProductEntity = storeProductService.getStoreProductById(Integer.valueOf(req.getParameter(Attribute.STORE_PRODUCT))).get();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        if (!req.getParameter("startDate").isBlank()) {
            startDate = LocalDate.parse(req.getParameter("startDate"));
        }
        if (!req.getParameter("endDate").isBlank()) {
            endDate = LocalDate.parse(req.getParameter("endDate"));
        }

        Double totalSum = 0.0;
        Integer totalQuantity = 0;

        if(storeProductEntity != null) {
            List<SaleEntity> sales = saleService.getSoldProductQuantityByProductAndTimePeriod(storeProductEntity.getProductId(), startDate, endDate);
        }

        return Page.ALL_STATISTICS;
    }
}
