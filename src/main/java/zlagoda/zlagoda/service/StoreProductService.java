package zlagoda.zlagoda.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.repository.BaseRepositoryFactory;
import zlagoda.zlagoda.repository.StoreProductRepository;
import zlagoda.zlagoda.view.StoreProductView;

import java.util.List;
import java.util.Optional;

public class StoreProductService {

    private static final Logger LOGGER = LogManager.getLogger(StoreProductService.class);

    private static final String GET_ALL_STORE_PRODUCTS = "Get all store products";
    private static final String GET_STORE_PRODUCT_BY_ID = "Get store product by id: %s";
    private static final String CREATE_STORE_PRODUCT = "Create store product: %s";
    private static final String UPDATE_STORE_PRODUCT = "Update store product: %s";
    private static final String DELETE_STORE_PRODUCT = "Delete store product: %s";
    private static final String GET_DISCOUNTED_PRODUCTS = "Get discounted store products";
    private static final String GET_NON_DISCOUNTED_PRODUCTS = "Get non discounted store products";

    private final BaseRepositoryFactory repositoryFactory;

    StoreProductService(BaseRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    private static class Holder {
        static final StoreProductService INSTANCE = new StoreProductService(BaseRepositoryFactory.getRepositoryFactory());
    }

    public static StoreProductService getInstance() { return Holder.INSTANCE; }

    public List<StoreProductEntity> getAllStoreProducts() {
        LOGGER.info(GET_ALL_STORE_PRODUCTS);
        try (StoreProductRepository repository = repositoryFactory.createStoreProductRepository()) {
            return repository.getAll();
        }
    }

    public Optional<StoreProductEntity> getStoreProductById(String id) {
        LOGGER.info(String.format(GET_STORE_PRODUCT_BY_ID, id));
        try (StoreProductRepository repository = repositoryFactory.createStoreProductRepository()) {
            return repository.getById(id);
        }
    }

    public void createStoreProduct(StoreProductView storeProductView) {
        LOGGER.info(String.format(CREATE_STORE_PRODUCT, storeProductView.getId()));
        StoreProductEntity storeProduct = buildStoreProductFromView(storeProductView);
        try (StoreProductRepository repository = repositoryFactory.createStoreProductRepository()) {
            repository.create(storeProduct);
        }
    }

    public void updateStoreProduct(StoreProductView storeProductView) {
        LOGGER.info(String.format(UPDATE_STORE_PRODUCT, storeProductView.getId()));
        StoreProductEntity storeProduct = buildStoreProductFromView(storeProductView);
        try (StoreProductRepository repository = repositoryFactory.createStoreProductRepository()) {
            repository.update(storeProduct);
        }
    }

    public void deleteStoreProduct(String id) {
        LOGGER.info(String.format(DELETE_STORE_PRODUCT, id));
        try (StoreProductRepository repository = repositoryFactory.createStoreProductRepository()) {
            repository.delete(id);
        }
    }

    public List<StoreProductEntity> getDiscountedStoreProducts() {
        LOGGER.info(String.format(GET_DISCOUNTED_PRODUCTS));
        try (StoreProductRepository repository = repositoryFactory.createStoreProductRepository()) {
            return repository.getDiscountedProducts();
        }
    }

    public List<StoreProductEntity> getNonDiscountedStoreProducts() {
        LOGGER.info(String.format(GET_NON_DISCOUNTED_PRODUCTS));
        try (StoreProductRepository repository = repositoryFactory.createStoreProductRepository()) {
            return repository.getNonDiscountedProducts();
        }
    }

    private static StoreProductEntity buildStoreProductFromView(StoreProductView view) {
        StoreProductEntity.StoreProductEntityBuilder result = StoreProductEntity.builder()
                .id(view.getId())
                .sellingPrice(view.getSellingPrice())
                .productQuantity(view.getProductQuantity())
                .isPromotional(view.getIsPromotional())
                .productId(view.getProductId());
        if (!view.getIsPromotional()) {
            result.promotionalEntity(StoreProductEntity.builder()
                    .id(view.getPromotionalView().getId())
                    .sellingPrice(view.getPromotionalView().getSellingPrice())
                    .productQuantity(view.getPromotionalView().getProductQuantity())
                    .isPromotional(true)
                    .promotionalEntity(null)
                    .productId(view.getPromotionalView().getProductId())
                    .build()
            );
        }
        return result.build();
    }
}
