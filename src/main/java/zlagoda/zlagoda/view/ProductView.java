package zlagoda.zlagoda.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductView {
    private Integer id;
    private String name;
    private String characteristics;
    private Integer categoryId;
}
