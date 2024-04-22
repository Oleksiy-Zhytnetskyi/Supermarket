package zlagoda.zlagoda.entity.keys;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class SaleEntityComplexKey {
    private String UPC;
    private String receiptId;
}
