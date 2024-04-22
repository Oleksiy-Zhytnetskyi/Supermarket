package zlagoda.zlagoda.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import zlagoda.zlagoda.entity.keys.SaleEntityComplexKey;

@Builder
@Getter
@Setter
public class SaleView {
    private SaleEntityComplexKey pk;
    private Integer productQuantity;
    private Double sellingPrice;
}
