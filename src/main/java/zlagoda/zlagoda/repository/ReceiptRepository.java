package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.ReceiptEntity;

import java.util.Date;
import java.util.List;

public interface ReceiptRepository extends BaseRepository<ReceiptEntity, String> {
    List<ReceiptEntity> searchReceiptsByUserAndTimePeriod(int userId, Date timeStart, Date timeEnd);
    List<ReceiptEntity> searchReceiptsByTimePeriod(Date timeStart, Date timeEnd);
    Double getReceiptsTotalValueByUserAndTimePeriod(int userId, Date timeStart, Date timeEnd);
    Double getReceiptsTotalValueByTimePeriod(Date timeStart, Date timeEnd);
}
