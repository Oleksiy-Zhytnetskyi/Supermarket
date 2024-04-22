package zlagoda.zlagoda.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ReceiptView {
    Integer id;
    LocalDate printDate;
    Double sumTotal;
    Double vat;
    Integer userId;
    Integer cardId;
}
