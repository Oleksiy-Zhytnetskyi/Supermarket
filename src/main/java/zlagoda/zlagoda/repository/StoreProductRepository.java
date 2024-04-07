package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.StoreProductEntity;

import java.util.List;

public interface StoreProductRepository extends BaseRepository<StoreProductEntity, String> {
    StoreProductEntity getStoreProductByUPC(String UPC);
    List<StoreProductEntity> getDiscountedProducts();
    List<StoreProductEntity> getNonDiscountedProducts();
}
