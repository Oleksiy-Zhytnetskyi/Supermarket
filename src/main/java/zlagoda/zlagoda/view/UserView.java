package zlagoda.zlagoda.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import zlagoda.zlagoda.entity.enums.UserRole;

import java.util.Date;

@Builder
@Getter
@Setter
public class UserView {
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
    private String email;
    private String password;
}
