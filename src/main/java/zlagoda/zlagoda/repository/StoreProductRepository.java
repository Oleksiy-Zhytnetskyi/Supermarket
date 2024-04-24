package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.StoreProductEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface StoreProductRepository extends BaseRepository<StoreProductEntity, Integer> {
    List<StoreProductEntity> getDiscountedProducts();
    List<StoreProductEntity> getNonDiscountedProducts();
}
