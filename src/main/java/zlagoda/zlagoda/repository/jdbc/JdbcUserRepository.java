package zlagoda.zlagoda.repository.jdbc;

import lombok.AllArgsConstructor;
import lombok.Setter;
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

@AllArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private static final Logger LOGGER = LogManager.getLogger(JdbcUserRepository.class);

    private static final String GET_ALL = "SELECT * FROM employee ORDER BY empl_surname";
    private static final String GET_BY_ID = "SELECT * FROM employee WHERE id_employee=?";
    private static final String CREATE = "INSERT INTO employee (" +
            "empl_surname, empl_name, empl_patronymic, empl_role, salary, date_of_birth, " +
            "date_of_start, phone_number, city, street, zip_code, email, password" +
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE employee "
            + " SET empl_surname=?, empl_name=?, empl_patronymic=?, empl_role=?, salary=?, " +
            "date_of_birth=?, date_of_start=?, phone_number=?, city=?, street=?, zip_code=?, " +
            "email=?, password=? WHERE id_employee=?";
    private static final String DELETE = "DELETE FROM employee WHERE id_employee=?";
    private static final String GET_BY_CREDENTIALS = "SELECT * FROM employee WHERE email=? AND password=?";
    private static final String GET_BY_EMAIL = "SELECT * FROM employee WHERE email=?";
    private static final String GET_BY_ROLE = "SELECT * FROM employee WHERE empl_role=?";
    private static final String GET_BY_SURNAME = "SELECT * FROM employee WHERE empl_surname=?";
    private static final String GET_CASHIER_SORTED = "SELECT * " +
            "FROM employee " +
            "WHERE empl_role = 'CASHIER' AND NOT EXISTS ( " +
                "SELECT * " +
                "FROM receipt JOIN sale ON receipt.check_number = sale.check_number " +
                "WHERE receipt.id_employee = employee.id_employee AND NOT EXISTS (" +
                    "SELECT * FROM store_product " +
                    "WHERE store_product.upc = sale.upc AND store_product.selling_price > 30000" +
                    ")" +
            ")";

    private static final String ID = "id_employee";
    private static final String SURNAME = "empl_surname";
    private static final String NAME = "empl_name";
    private static final String PATRONYMIC = "empl_patronymic";
    private static final String ROLE = "empl_role";
    private static final String SALARY = "salary";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String DATE_OF_START = "date_of_start";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String ZIP_CODE = "zip_code";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @Setter
    private Connection connection;
    private final boolean connectionShouldBeClosed;

    public JdbcUserRepository(Connection connection) {
        this.connection = connection;
        this.connectionShouldBeClosed = false;
    }

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
    public List<UserEntity> getCashierSorted() {
        List<UserEntity> users = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_CASHIER_SORTED)) {
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
    public Optional<UserEntity> getById(Integer id) {
        Optional<UserEntity> user = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
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
            setAllFields(query, user);
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcUserRepository create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(UserEntity user) {
        System.out.println(user);
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            final int counterIndex = setAllFields(query, user);
            query.setInt(counterIndex + 1, user.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserRepository update SQL exception: " + user.getId(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcUserRepository delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
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
                .id(Integer.valueOf(resultSet.getString(ID)))
                .name(resultSet.getString(NAME))
                .surname(resultSet.getString(SURNAME))
                .patronymic(resultSet.getString(PATRONYMIC))
                .phone(resultSet.getString(PHONE_NUMBER))
                .role(UserRole.valueOf(resultSet.getString(ROLE).toUpperCase()))
                .salary(resultSet.getDouble(SALARY))
                .dateOfBirth(resultSet.getDate(DATE_OF_BIRTH).toLocalDate())
                .startDate(resultSet.getDate(DATE_OF_START).toLocalDate())
                .city(resultSet.getString(CITY))
                .street(resultSet.getString(STREET))
                .zipCode(resultSet.getString(ZIP_CODE))
                .email(resultSet.getString(EMAIL))
                .password(resultSet.getString(PASSWORD))
                .build();
    }

    private static int setAllFields(PreparedStatement query, UserEntity user) throws SQLException {
        int index = 0;
        query.setString(++index, user.getSurname());
        query.setString(++index, user.getName());
        query.setString(++index, user.getPatronymic());
        query.setString(++index, user.getRole().toString());
        query.setDouble(++index, user.getSalary());
        query.setDate(++index, Date.valueOf(user.getDateOfBirth()));
        query.setDate(++index, Date.valueOf(user.getStartDate()));
        query.setString(++index, user.getPhone());
        query.setString(++index, user.getCity());
        query.setString(++index, user.getStreet());
        query.setString(++index, user.getZipCode());
        query.setString(++index, user.getEmail());
        query.setString(++index, user.getPassword());
        return index;
    }
}
