package zlagoda.zlagoda.entity.keys;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SaleEntityComplexKey {
    private String UPC;
    private String receiptId;
}
