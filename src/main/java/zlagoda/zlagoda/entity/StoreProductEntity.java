package zlagoda.zlagoda.entity;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StoreProductEntity {
    private Integer id;
    private Double sellingPrice;
    private Integer productQuantity;
    private Boolean isPromotional;
    private Integer promotionalId;
    private Integer productId;
}
