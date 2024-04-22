package zlagoda.zlagoda.entity;

import lombok.*;
import zlagoda.zlagoda.entity.keys.SaleEntityComplexKey;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SaleEntity {
    private SaleEntityComplexKey pk;
    private Integer productQuantity;
    private Double sellingPrice;
}