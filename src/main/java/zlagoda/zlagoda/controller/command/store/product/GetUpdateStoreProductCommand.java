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
import java.util.ArrayList;
import java.util.List;

public class GetUpdateStoreProductCommand implements Command {

    private final StoreProductService storeProductService;
    private final ProductService productService;
    private final CategoryService categoryService;

    public GetUpdateStoreProductCommand(StoreProductService storeProductService, ProductService productService, CategoryService categoryService) {
        this.storeProductService = storeProductService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StoreProductEntity storeProductEntity = storeProductService.getStoreProductById(req.getParameter(Attribute.ID)).get();
        List<ProductEntity> products = productService.getAllProducts();
        List<CategoryEntity> categories = categoryService.getAllCategories();
        List<StoreProductEntity> saleStoreProducts = new ArrayList<>();
        storeProductService.getAllStoreProducts().stream()
                .forEach(
                        storeProduct -> {
                            if(storeProduct.getIsPromotional()) saleStoreProducts.add(storeProduct);
                        });
        req.setAttribute(Attribute.STORE_PRODUCT_VIEW, storeProductEntity);
        req.setAttribute(Attribute.SALE_STORE_PRODUCTS, saleStoreProducts);
        req.setAttribute(Attribute.PRODUCTS, products);
        req.setAttribute("update", true);
        return Page.ADD_STRORE_PRODUCT;
    }
}
