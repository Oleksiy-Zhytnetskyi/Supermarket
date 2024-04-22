package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.SaleEntity;
import zlagoda.zlagoda.entity.keys.SaleEntityComplexKey;

import java.time.LocalDate;

public interface SaleRepository extends BaseRepository<SaleEntity, SaleEntityComplexKey> {
    Integer getSoldProductQuantityByProductAndTimePeriod(int productId, LocalDate timeStart, LocalDate timeEnd);
}
