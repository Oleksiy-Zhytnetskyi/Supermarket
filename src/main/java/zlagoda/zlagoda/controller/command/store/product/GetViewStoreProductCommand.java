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

public class GetViewStoreProductCommand implements Command {

    private ProductService productService;
    private final CategoryService categoryService;
    private final StoreProductService storeProductService;

    public GetViewStoreProductCommand(StoreProductService storeProductService, ProductService productService, CategoryService categoryService) {
        this.storeProductService = storeProductService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StoreProductEntity storeProductEntity = storeProductService.getStoreProductById(req.getParameter(Attribute.ID)).get();
        ProductEntity productEntity = productService.getProductById(storeProductEntity.getProductId()).get();
        List<CategoryEntity> categories = categoryService.getAllCategories();
        req.setAttribute(Attribute.STORE_PRODUCT_VIEW, storeProductEntity);
        req.setAttribute(Attribute.PRODUCT_VIEW, productEntity);
        req.setAttribute(Attribute.CATEGORIES, categories);
        req.setAttribute("view", true);
        return Page.VIEW_STORE_PRODUCT;
    }
}
