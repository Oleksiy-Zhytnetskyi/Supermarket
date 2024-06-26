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

public class GetUpdateStoreProductCommand implements Command {

    private static final StoreProductService storeProductService = StoreProductService.getInstance();
    private static final ProductService productService = ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StoreProductEntity storeProductEntity = storeProductService.getStoreProductById(Integer.valueOf(req.getParameter(Attribute.ID))).get();
        List<ProductEntity> products = productService.getAllProducts();
        List<StoreProductEntity> saleStoreProducts = new ArrayList<>();
        storeProductService.getAllStoreProducts().stream().forEach(
                storeProduct -> {
                    if (storeProduct.getIsPromotional() /*&& Objects.equals(storeProduct.getProductId(), storeProductEntity.getProductId())*/) {
                        saleStoreProducts.add(storeProduct);
                    }
                });
        req.setAttribute(Attribute.STORE_PRODUCT_VIEW, storeProductEntity);
        req.setAttribute(Attribute.SALE_STORE_PRODUCTS, saleStoreProducts);
        req.setAttribute(Attribute.PRODUCTS, products);
        req.setAttribute("update", true);
        return Page.ADD_STORE_PRODUCT;
    }
}
