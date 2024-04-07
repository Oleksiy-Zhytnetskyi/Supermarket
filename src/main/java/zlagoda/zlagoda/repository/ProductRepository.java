package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends BaseRepository<ProductEntity, Integer> {
    List<ProductEntity> searchProductsByCategory(int categoryId);
    List<ProductEntity> searchProductsByName(String name);
}
