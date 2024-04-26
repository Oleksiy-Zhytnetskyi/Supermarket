package zlagoda.zlagoda.controller.command.receipt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.*;
import zlagoda.zlagoda.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetViewReceiptCommand implements Command {

    private final ReceiptService receiptService = ReceiptService.getInstance();
    private final SaleService saleService = SaleService.getInstance();
    private final StoreProductService storeProductService = StoreProductService.getInstance();
    private final ProductService productService = ProductService.getInstance();
    private final UserService userService = UserService.getInstance();
    private final CardService cardService = CardService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceiptEntity entity = receiptService.getReceiptById(Integer.valueOf(req.getParameter(Attribute.ID))).get();
        UserEntity cashier = userService.getUserById(entity.getUserId()).get();
        List<StoreProductEntity> storeProducts = storeProductService.getAllStoreProducts();
        List<ProductEntity> products = productService.getAllProducts();
        List<SaleEntity> sales = new ArrayList<>();
        saleService.getAllSales().stream().forEach(sale -> {
                    if(sale.getPk().getReceiptId() == entity.getId()) {
                        sales.add(sale);
                    }
                });

        if (entity.getCardId() != null) {
            CardEntity card = cardService.getCardById(entity.getCardId()).get();
            req.setAttribute(Attribute.CARD_VIEW, card);
        }

        req.setAttribute(Attribute.RECEIPT_VIEW, entity);
        req.setAttribute(Attribute.STORE_PRODUCTS, storeProducts);
        req.setAttribute(Attribute.PRODUCTS, products);
        req.setAttribute(Attribute.SALES, sales);
        req.setAttribute(Attribute.USER_VIEW, cashier);
        return Page.VIEW_RECEIPT;
    }
}
