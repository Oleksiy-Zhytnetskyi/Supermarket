package zlagoda.zlagoda.controller.command.store.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.CategoryEntity;
import zlagoda.zlagoda.entity.ProductEntity;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.service.CategoryService;
import zlagoda.zlagoda.service.ProductService;
import zlagoda.zlagoda.service.StoreProductService;

import java.io.IOException;
import java.util.List;

public class GetAllStoreProductCommand implements Command {

    private final ProductService productService;
    private final StoreProductService storeProductService;

    public GetAllStoreProductCommand(ProductService service, StoreProductService storeProductService) {
        this.productService = service;
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductEntity> products = productService.getAllProducts();
        List<StoreProductEntity> storeProducts = storeProductService.getAllStoreProducts();
        req.setAttribute(Attribute.STORE_PRODUCTS, storeProducts);
        req.setAttribute(Attribute.PRODUCTS, products);
        return Page.ALL_STORE_PRODUCTS;
    }
}
