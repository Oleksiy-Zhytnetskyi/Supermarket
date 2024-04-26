package zlagoda.zlagoda.controller.command.statistics;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.ProductEntity;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.entity.enums.UserRole;
import zlagoda.zlagoda.service.ProductService;
import zlagoda.zlagoda.service.StoreProductService;
import zlagoda.zlagoda.service.UserService;

import java.io.IOException;
import java.util.List;

public class GetAllStatisticsCommand implements Command {

    private static final UserService userService = UserService.getInstance();
    private static final ProductService productService = ProductService.getInstance();
    private static final StoreProductService storeProductService = StoreProductService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserEntity> cashiers = userService.searchUsersByRole(UserRole.CASHIER);
        List<ProductEntity> products = productService.getAllProducts();
        List<StoreProductEntity> storeProducts = storeProductService.getAllStoreProducts();
        req.setAttribute(Attribute.USERS, cashiers);
        req.setAttribute(Attribute.PRODUCTS, products);
        req.setAttribute(Attribute.STORE_PRODUCTS, storeProducts);
        return Page.ALL_STATISTICS;
    }
}
