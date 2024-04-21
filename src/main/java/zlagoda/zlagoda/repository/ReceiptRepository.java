package zlagoda.zlagoda.repository;

import zlagoda.zlagoda.entity.ReceiptEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReceiptRepository extends BaseRepository<ReceiptEntity, String> {
    List<ReceiptEntity> searchReceiptsByUserAndTimePeriod(int userId, LocalDate timeStart, LocalDate timeEnd);
    List<ReceiptEntity> searchReceiptsByTimePeriod(LocalDate timeStart, LocalDate timeEnd);
    Double getReceiptsTotalValueByUserAndTimePeriod(int userId, LocalDate timeStart, LocalDate timeEnd);
    Double getReceiptsTotalValueByTimePeriod(LocalDate timeStart, LocalDate timeEnd);
}
