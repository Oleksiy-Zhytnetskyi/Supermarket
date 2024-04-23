package zlagoda.zlagoda.view;

import lombok.*;
import zlagoda.zlagoda.entity.enums.UserRole;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserView {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private UserRole role;
    private String salary;
    private LocalDate dateOfBirth;
    private LocalDate startDate;
    private String city;
    private String street;
    private String zipCode;
    private String email;
    private String password;
}
