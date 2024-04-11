package zlagoda.zlagoda.validator.entity;

import java.util.List;

public interface Validator<T> {
    List<String> validate(T view);
}
