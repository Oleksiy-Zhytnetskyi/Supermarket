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

public class GetSortStoreProductsCommand implements Command {

    private final StoreProductService storeProductService = StoreProductService.getInstance();
    private final ProductService productService = ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StoreProductEntity> storeProducts = new ArrayList<>();
        List<ProductEntity> products = productService.getAllProducts();

        String name = req.getParameter(Attribute.PRODUCTS_NAME);
        String sortBy = req.getParameter(Attribute.SORT_STORE_PRODUCTS_BY);
        String type = req.getParameter(Attribute.STORE_PRODUCTS_TYPE);

        if(sortBy.equals("byName")) {
            storeProductService.getAllStoreProducts().stream()
                    .forEach(
                            productEntity -> {
                                ProductEntity product = null;
                                for(int i = 0; i < products.size(); i++) {
                                    if(products.get(i).getId() == productEntity.getProductId()) {
                                        product = products.get(i);
                                        break;
                                    }
                                }

                                if(product.getName().toLowerCase().contains(name.toLowerCase())) {
                                    if (type.equals("non-promotional")) {
                                        if(!productEntity.getIsPromotional())
                                            storeProducts.add(productEntity);
                                    } else if (type.equals("promotional")) {
                                        if(productEntity.getIsPromotional())
                                            storeProducts.add(productEntity);
                                    } else {
                                        storeProducts.add(productEntity);
                                    }
                                }
                            }
                    );
        } else {

        }

        req.setAttribute("storeProducts", storeProducts);
        req.setAttribute(Attribute.PRODUCTS, products);
        req.setAttribute(Attribute.STORE_PRODUCTS, storeProducts);
        req.setAttribute(Attribute.PRODUCTS_NAME, name);
        req.setAttribute(Attribute.SORT_STORE_PRODUCTS_BY, sortBy);
        req.setAttribute(Attribute.STORE_PRODUCTS_TYPE, type);
        return Page.ALL_STORE_PRODUCTS;
    }
}

