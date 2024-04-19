package zlagoda.zlagoda.repository.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.entity.enums.UserRole;
import zlagoda.zlagoda.exception.ServerException;
import zlagoda.zlagoda.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserRepository implements UserRepository {

    private static final Logger LOGGER = LogManager.getLogger(JdbcUserRepository.class);


    private static String GET_ALL = "SELECT * FROM employee";
    private static String GET_BY_ID = "SELECT * FROM employee WHERE id_employee=?";
    private static String CREATE = "INSERT INTO employee (" +
            "empl_surname, empl_name, empl_patronymic, empl_role, salary, date_of_birth, " +
            "date_of_start, phone_number, city, street, zip_code, email, password" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static String GET_BY_CREDENTIALS = "SELECT * FROM employee WHERE email=? AND password=?";
    private static String GET_BY_EMAIL = "SELECT * FROM employee WHERE email=?";
    private static String GET_BY_ROLE = "SELECT * FROM employee WHERE empl_role=?";
    private static String GET_BY_SURNAME = "SELECT * FROM employee WHERE empl_surname=?";

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
        List<UserEntity> users = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                users.add(extractUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserRepository getAll SQL exception", e);
            throw new ServerException(e);
        }
        return users;
    }

    @Override
    public Optional<UserEntity> getById(String id) {
        Optional<UserEntity> user = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                user = Optional.of(extractUserFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcUserRepository getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return user;
    }

    @Override
    public void create(UserEntity user) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, user.getSurname());
            query.setString(2, user.getName());
            query.setString(3, user.getPatronymic());
            query.setString(4, user.getRole().toString());
            query.setDouble(5, user.getSalary());
            query.setDate(6, (Date)user.getDateOfBirth());
            query.setDate(7, (Date)user.getStartDate());
            query.setString(8, user.getPhone());
            query.setString(9, user.getCity());
            query.setString(10, user.getStreet());
            query.setString(11, user.getZipCode());
            query.setString(12, user.getEmail());
            query.setString(13, user.getPassword());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserRepository create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(UserEntity e) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcUserRepository Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
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
    public Optional<UserEntity> getUserByEmail(String email) {
        Optional<UserEntity> user = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_EMAIL)) {
            query.setString(1, email);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                user = Optional.of(extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserRepository.getUserByEmail SQL exception: ", e);
            throw new ServerException(e);
        }
        return user;
    }

    @Override
    public List<UserEntity> searchUsersByRole(UserRole role) {
        List<UserEntity> users = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ROLE)) {
            query.setString(1, role.toString());
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserRepository.searchUsersByRole SQL exception: ", e);
            throw new ServerException(e);
        }
        return users;
    }

    @Override
    public List<UserEntity> searchUsersBySurname(String surname) {
        List<UserEntity> users = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_SURNAME)) {
            query.setString(1, surname);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserRepository.searchUsersBySurname SQL exception: ", e);
            throw new ServerException(e);
        }
        return users;
    }

    protected static UserEntity extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .id(resultSet.getString(ID))
                .name(resultSet.getString(NAME))
                .surname(resultSet.getString(SURNAME))
                .patronymic(resultSet.getString(PATRONYMIC))
                .phone(resultSet.getString(PHONE_NUMBER))
                .role(UserRole.valueOf(resultSet.getString(ROLE).toUpperCase()))
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
