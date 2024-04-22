package zlagoda.zlagoda.service;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.entity.enums.UserRole;
import zlagoda.zlagoda.repository.BaseRepositoryFactory;
import zlagoda.zlagoda.repository.UserRepository;
import zlagoda.zlagoda.view.CredentialsView;
import zlagoda.zlagoda.view.UserView;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private static final String GET_ALL_USERS = "Get all users";
    private static final String GET_USER_BY_ID = "Get user by id: %s";
    private static final String CREATE_USER = "Create user: %s";
    private static final String UPDATE_USER = "Update user: %s";
    private static final String DELETE_USER = "Delete user: %s";
    private static final String GET_USER_BY_CREDENTIALS = "Get user by credentials: %s";
    private static final String GET_USER_BY_EMAIL = "Get user by email: %s";
    private static final String SEARCH_USERS_BY_ROLE = "Search users by role: %s";
    private static final String SEARCH_USERS_BY_SURNAME = "Search users by surname: %s";

    private final BaseRepositoryFactory repositoryFactory;

    private static class Holder {
        static final UserService INSTANCE = new UserService(BaseRepositoryFactory.getRepositoryFactory());
    }

    public static UserService getInstance() { return Holder.INSTANCE; }

    public List<UserEntity> getAllUsers() {
        LOGGER.info(GET_ALL_USERS);
        try (UserRepository repository = repositoryFactory.createUserRepository()) {
            return repository.getAll();
        }
    }

    public Optional<UserEntity> getUserById(Integer userId) {
        LOGGER.info(String.format(GET_USER_BY_ID, userId));
        try (UserRepository repository = repositoryFactory.createUserRepository()) {
            return repository.getById(userId);
        }
    }

    public void createUser(UserView userView) {
        LOGGER.info(String.format(CREATE_USER, userView.getEmail()));
        UserEntity user = buildUserFromView(userView);
        try (UserRepository repository = repositoryFactory.createUserRepository()) {
            repository.create(user);
        }
    }

    public void updateUser(UserView userView) {
        LOGGER.info(String.format(UPDATE_USER, userView.getId()));
        UserEntity user = buildUserFromView(userView);
        try (UserRepository repository = repositoryFactory.createUserRepository()) {
            UserEntity prevUser = repository.getUserByEmail(user.getEmail()).get();
            user.setId(prevUser.getId());
            repository.update(user);
        }
    }

    public void deleteUser(Integer userId) {
        LOGGER.info(String.format(DELETE_USER, userId));
        try (UserRepository repository = repositoryFactory.createUserRepository()) {
            repository.delete(userId);
        }
    }

    public Optional<UserEntity> getUserByCredentials(CredentialsView credentials) {
        LOGGER.info(String.format(GET_USER_BY_CREDENTIALS, credentials.getEmail()));
        try (UserRepository repository = repositoryFactory.createUserRepository()) {
            return repository.getUserByCredentials(credentials.getEmail(), credentials.getPassword());
        }
    }

    public Optional<UserEntity> getUserByEmail(String email) {
        LOGGER.info(String.format(GET_USER_BY_EMAIL, email));
        try (UserRepository repository = repositoryFactory.createUserRepository()) {
            return repository.getUserByEmail(email);
        }
    }

    public List<UserEntity> searchUsersByRole(UserRole role) {
        LOGGER.info(String.format(SEARCH_USERS_BY_ROLE, role));
        try (UserRepository repository = repositoryFactory.createUserRepository()) {
            return repository.searchUsersByRole(role);
        }
    }

    public List<UserEntity> searchUsersBySurname(String surname) {
        LOGGER.info(String.format(SEARCH_USERS_BY_SURNAME, surname));
        try (UserRepository repository = repositoryFactory.createUserRepository()) {
            return repository.searchUsersBySurname(surname);
        }
    }

    private static UserEntity buildUserFromView(UserView view) {
        return UserEntity.builder()
                .id(view.getId())
                .name(view.getName())
                .surname(view.getSurname())
                .patronymic(view.getPatronymic())
                .phone(view.getPhone())
                .role(view.getRole())
                .salary(view.getSalary())
                .dateOfBirth(view.getDateOfBirth())
                .startDate(view.getStartDate())
                .city(view.getCity())
                .street(view.getStreet())
                .zipCode(view.getZipCode())
                .email(view.getEmail())
                .password(view.getPassword())
                .build();
    }
}
