package zlagoda.zlagoda.entity;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CardEntity {
    private Integer id;
    private String customerSurname;
    private String customerName;
    private String customerPatronymic;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;
    private Integer percent;
}
