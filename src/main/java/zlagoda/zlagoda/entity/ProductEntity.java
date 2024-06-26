package zlagoda.zlagoda.entity;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductEntity {
    private Integer id;
    private String name;
    private String characteristics;
    private Integer categoryId;
}
