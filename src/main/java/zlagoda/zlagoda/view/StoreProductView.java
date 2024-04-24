package zlagoda.zlagoda.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StoreProductView {
    private Integer id;
    private Double sellingPrice;
    private Integer productQuantity;
    private Boolean isPromotional;
    private Integer promotionalId;
    private Integer productId;
}
