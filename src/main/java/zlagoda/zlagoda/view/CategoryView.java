package zlagoda.zlagoda.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryView {
    private int id;
    private String name;
}
