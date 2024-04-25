package zlagoda.zlagoda.validator.field;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldValidatorKey {

    EMAIL("email"),
    PASSWORD("password"),

    NAME("name"),
    SURNAME("surname"),
    PATRONYMIC("patronymic"),
    PHONE("phone"),
    ROLE("role"),
    SALARY("salary"),
    DATE_OF_BIRTH("dateOfBirth"),
    START_DATE("startDate"),
    CITY("city"),
    STREET("street"),
    ZIP_CODE("zipCode"),
    CHARACTERISTICS("characteristics"),
    PRICE("price");

    private final String value;
}
