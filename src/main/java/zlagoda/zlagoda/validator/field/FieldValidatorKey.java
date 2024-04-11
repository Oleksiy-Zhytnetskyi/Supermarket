package zlagoda.zlagoda.validator.field;

public enum FieldValidatorKey {

    EMAIL("email"),
    PASSWORD("password");

    private String value;

    FieldValidatorKey(String value) {}

    public String getValue() { return value; }
}
