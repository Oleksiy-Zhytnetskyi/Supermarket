package zlagoda.zlagoda.repository.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.entity.enums.UserRole;
import zlagoda.zlagoda.exception.ServerException;
import zlagoda.zlagoda.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcUserRepository implements UserRepository {

    private static final Logger LOGGER = LogManager.getLogger(JdbcUserRepository.class);


    private static String GET_BY_CREDENTIALS = "SELECT * FROM 'employee' WHERE email=? AND password=?";


    private static String ID = "id_employee";
    private static String SURNAME = "empl_surname";
    private static String NAME = "empl_name";
    private static String PATRONYMIC = "empl_patronymic";
    private static String ROLE = "empl_role";
    private static String SALARY = "salary";
    private static String DATE_OF_BIRTH = "date_of_birth";
    private static String DATE_OF_START = "date_of_start";
    private static String PHONE_NUMBER = "phone_number";
    private static String CITY = "city";
    private static String STREET = "street";
    private static String ZIP_CODE = "zip_code";
    private static String EMAIL = "email";
    private static String PASSWORD = "password";


    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcUserRepository(Connection connection) {
        this.connection = connection;
        this.connectionShouldBeClosed = false;
    }

    public JdbcUserRepository(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) { this.connection = connection; }

    @Override
    public List<UserEntity> getAll() {
        return null;
    }

    @Override
    public Optional<UserEntity> getById(String id) {
        return Optional.empty();
    }

    @Override
    public void create(UserEntity e) {

    }

    @Override
    public void update(UserEntity e) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void close() {

    }

    @Override
    public Optional<UserEntity> getUserByCredentials(String email, String password) {
        Optional<UserEntity> user = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_CREDENTIALS)) {
            query.setString(1, email);
            query.setString(2, password);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                user = Optional.of(extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserRepository.getByCredentials SQL exception: ", e);
            throw new ServerException(e);
        }
        return user;
    }

    @Override
    public List<UserEntity> searchUsersByRole(UserRole role) {
        return null;
    }

    @Override
    public List<UserEntity> searchUsersBySurname(String surname) {
        return null;
    }

    protected static UserEntity extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .id(resultSet.getString(ID))
                .name(resultSet.getString(NAME))
                .surname(resultSet.getString(SURNAME))
                .patronymic(resultSet.getString(PATRONYMIC))
                .phone(resultSet.getString(PHONE_NUMBER))
                .role(resultSet.getObject(ROLE, UserRole.class))
                .salary(resultSet.getDouble(SALARY))
                .dateOfBirth(resultSet.getDate(DATE_OF_BIRTH))
                .startDate(resultSet.getDate(DATE_OF_START))
                .city(resultSet.getString(CITY))
                .street(resultSet.getString(STREET))
                .zipCode(resultSet.getString(ZIP_CODE))
                .email(resultSet.getString(EMAIL))
                .password(resultSet.getString(PASSWORD))
                .build();
    }
}
