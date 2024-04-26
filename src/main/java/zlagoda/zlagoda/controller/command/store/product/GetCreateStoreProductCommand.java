package zlagoda.zlagoda.controller.command.store.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.ProductEntity;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.service.ProductService;
import zlagoda.zlagoda.service.StoreProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetCreateStoreProductCommand implements Command {

    private final StoreProductService storeProductService = StoreProductService.getInstance();
    private final ProductService productService = ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductEntity> products = productService.getAllProducts();
        List<StoreProductEntity> saleStoreProducts = new ArrayList<>();
        storeProductService.getAllStoreProducts().stream().forEach(
                storeProduct -> {
                    if (storeProduct.getIsPromotional()) saleStoreProducts.add(storeProduct);
                });
        req.setAttribute(Attribute.SALE_STORE_PRODUCTS, saleStoreProducts);
        req.setAttribute(Attribute.PRODUCTS, products);
        req.setAttribute("create", true);
        return Page.ADD_STORE_PRODUCT;
    }
}
