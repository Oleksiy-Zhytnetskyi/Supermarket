package zlagoda.zlagoda.service;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.ProductEntity;
import zlagoda.zlagoda.repository.BaseRepositoryFactory;
import zlagoda.zlagoda.repository.ProductRepository;
import zlagoda.zlagoda.view.ProductView;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);

    private static final String GET_ALL_PRODUCTS = "Get all products";
    private static final String GET_PRODUCT_BY_ID = "Get product by id: %d";
    private static final String CREATE_PRODUCT = "Create product: %s";
    private static final String UPDATE_PRODUCT = "Update product: %s";
    private static final String DELETE_PRODUCT = "Delete product: %s";
    private static final String SEARCH_PRODUCTS_BY_CATEGORY = "Search products by categoryId: %d";
    private static final String SEARCH_PRODUCTS_BY_NAME = "Search products by name: %s";

    private final BaseRepositoryFactory repositoryFactory;

    private static class Holder {
        static final ProductService INSTANCE = new ProductService(BaseRepositoryFactory.getRepositoryFactory());
    }

    public static ProductService getInstance() { return Holder.INSTANCE; }

    public List<ProductEntity> getAllProducts() {
        LOGGER.info(GET_ALL_PRODUCTS);
        try (ProductRepository repository = repositoryFactory.createProductRepository()) {
            return repository.getAll();
        }
    }

    public Optional<ProductEntity> getProductById(Integer id) {
        LOGGER.info(String.format(GET_PRODUCT_BY_ID, id));
        try (ProductRepository repository = repositoryFactory.createProductRepository()) {
            return repository.getById(id);
        }
    }

    public void createProduct(ProductView productView) {
        LOGGER.info(String.format(CREATE_PRODUCT, productView.getId()));
        ProductEntity product = buildProductFromView(productView);
        try (ProductRepository repository = repositoryFactory.createProductRepository()) {
            repository.create(product);
        }
    }

    public void updateProduct(ProductView productView) {
        LOGGER.info(String.format(UPDATE_PRODUCT, productView.getId()));
        ProductEntity product = buildProductFromView(productView);
        try (ProductRepository repository = repositoryFactory.createProductRepository()) {
            repository.update(product);
        }
    }

    public void deleteProduct(Integer id) {
        LOGGER.info(String.format(DELETE_PRODUCT, id));
        try (ProductRepository repository = repositoryFactory.createProductRepository()) {
            repository.delete(id);
        }
    }

    public List<ProductEntity> searchProductsByCategory(int categoryId) {
        LOGGER.info(String.format(SEARCH_PRODUCTS_BY_CATEGORY, categoryId));
        try (ProductRepository repository = repositoryFactory.createProductRepository()) {
            return repository.searchProductsByCategory(categoryId);
        }
    }

    public List<ProductEntity> searchProductsByName(String name) {
        LOGGER.info(String.format(SEARCH_PRODUCTS_BY_NAME, name));
        try (ProductRepository repository = repositoryFactory.createProductRepository()) {
            return repository.searchProductsByName(name);
        }
    }

    private static ProductEntity buildProductFromView(ProductView view) {
        return ProductEntity.builder()
                .id(view.getId())
                .name(view.getName())
                .characteristics(view.getCharacteristics())
                .categoryId(view.getCategoryId())
                .build();
    }
}
