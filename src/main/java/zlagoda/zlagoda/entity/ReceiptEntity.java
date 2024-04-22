package zlagoda.zlagoda.entity;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReceiptEntity {
    Integer id;
    LocalDate printDate;
    Double sumTotal;
    Double vat;
    Integer userId;
    Integer cardId;
}
