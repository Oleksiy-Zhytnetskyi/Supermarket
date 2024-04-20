package zlagoda.zlagoda.entity;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CategoryEntity {
    private Integer id;
    private String name;
}
