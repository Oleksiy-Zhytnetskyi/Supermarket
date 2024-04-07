package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.SaleEntity;

import java.util.Date;
//                                                                 (UPC)
public interface SaleRepository extends BaseRepository<SaleEntity, String> {
    Integer getSalesTotalCountByProductAndTimePeriod(int productId, Date timeStart, Date timeEnd);
}
