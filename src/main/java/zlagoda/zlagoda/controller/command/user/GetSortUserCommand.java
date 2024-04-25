package zlagoda.zlagoda.controller.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.entity.enums.UserRole;
import zlagoda.zlagoda.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetSortUserCommand implements Command {

    private final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserEntity> users = new ArrayList<>();
        String surname = req.getParameter(Attribute.USERS_SURNAME);
        UserRole role = UserRole.valueOf(req.getParameter(Attribute.USERS_ROLE));

        userService.getAllUsers().stream()
                .forEach( userEntity -> {
                    if(userEntity.getSurname().toLowerCase().contains(surname.toLowerCase())) {
                        if(role != UserRole.ALL) {
                            if(userEntity.getRole() == role) {
                                users.add(userEntity);
                            }
                        } else {
                            users.add(userEntity);
                        }
                    }

                });
        req.setAttribute(Attribute.USERS, users);
        req.setAttribute(Attribute.USERS_ROLE, role);
        req.setAttribute(Attribute.USERS_SURNAME, surname);
        return Page.ALL_USERS;
    }
}
