package zlagoda.zlagoda.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StoreProductView {
    private String id;
    private Double sellingPrice;
    private Integer productQuantity;
    private Boolean isPromotional;
    private StoreProductView promotionalView;
    private Integer productId;
}
