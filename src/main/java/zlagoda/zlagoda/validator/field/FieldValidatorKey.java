package zlagoda.zlagoda.validator.field;

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
    ZIP_CODE("zipCode");

    private String value;

    FieldValidatorKey(String value) {}

    public String getValue() { return value; }
}
