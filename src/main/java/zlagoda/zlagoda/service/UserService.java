package zlagoda.zlagoda.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.repository.BaseRepositoryFactory;
import zlagoda.zlagoda.repository.UserRepository;
import zlagoda.zlagoda.view.CredentialsView;

import java.util.Optional;

public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    static final String GET_USER_BY_CREDENTIALS = "Get user by credentials: %s";

    private final BaseRepositoryFactory repositoryFactory;

    UserService(BaseRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    private static class Holder {
        static final UserService INSTANCE = new UserService(BaseRepositoryFactory.getRepositoryFactory());
    }

    public static UserService getInstance() { return Holder.INSTANCE; }

    public Optional<UserEntity> getUserByCredentials(CredentialsView credentials) {
        LOGGER.info(String.format(GET_USER_BY_CREDENTIALS, credentials.getEmail()));
        try (UserRepository userDao = repositoryFactory.createUserRepository()) {
            return userDao.getUserByCredentials(credentials.getEmail(), credentials.getPassword());
        }
    }
}
