package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.SaleEntity;
import zlagoda.zlagoda.entity.keys.SaleEntityComplexKey;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends BaseRepository<SaleEntity, SaleEntityComplexKey> {
    List<SaleEntity> getSalesByProductAndTimePeriod(int productId, LocalDate timeStart, LocalDate timeEnd);
    List<SaleEntity> getSalesByTimePeriod(LocalDate timeStart, LocalDate timeEnd);
}
