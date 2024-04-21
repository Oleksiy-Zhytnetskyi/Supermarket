package zlagoda.zlagoda.entity;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StoreProductEntity {
    private String id;
    private Double sellingPrice;
    private Integer productQuantity;
    private Boolean isPromotional;
    private StoreProductEntity promotionalEntity;
    private Integer productId;
}
