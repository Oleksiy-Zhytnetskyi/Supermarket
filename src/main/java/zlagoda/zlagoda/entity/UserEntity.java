package zlagoda.zlagoda.entity;

import lombok.*;
import zlagoda.zlagoda.entity.enums.UserRole;

import java.util.Date;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserEntity {
    private String id;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private UserRole role;
    private Double salary;
    private Date dateOfBirth;
    private Date startDate;
    private String city;
    private String street;
    private String zipCode;
}
