package zlagoda.zlagoda.repository.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.CardEntity;
import zlagoda.zlagoda.exception.ServerException;
import zlagoda.zlagoda.repository.CardRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCardRepository implements CardRepository {

    private static final Logger LOGGER = LogManager.getLogger(JdbcCardRepository.class);

    private static final String GET_ALL = "SELECT * FROM customer_card";
    private static final String GET_BY_ID = "SELECT * FROM customer_card WHERE card_number=?";
    private static final String CREATE = "INSERT INTO customer_card " +
            "(cust_surname, cust_name, cust_patronymic, phone_number, city, street, zip_code, percent) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE customer_card SET " +
            "cust_surname=?, cust_name=?, cust_patronymic=?, phone_number=?, city=?, street=?, zip_code=?, percent=?" +
            "WHERE card_number=?";
    private static final String DELETE = "DELETE FROM customer_card WHERE card_number=?";
    private static final String GET_BY_PERCENT = "SELECT * FROM customer_card WHERE percent=?";
    private static final String GET_BY_CUSTOMER_SURNAME = "SELECT * FROM customer_card WHERE cust_surname=?";

    private static final String ID = "card_number";
    private static final String CUSTOMER_SURNAME = "cust_surname";
    private static final String CUSTOMER_NAME = "cust_name";
    private static final String CUSTOMER_PATRONYMIC = "cust_patronymic";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String ZIP_CODE = "zip_code";
    private static final String PERCENT = "percent";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcCardRepository(Connection connection) {
        this.connection = connection;
        this.connectionShouldBeClosed = false;
    }

    public JdbcCardRepository(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) { this.connection = connection; }

    @Override
    public List<CardEntity> getAll() {
        List<CardEntity> cards = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                cards.add(extractCardFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCardRepository getAll SQL exception", e);
            throw new ServerException(e);
        }
        return cards;
    }

    @Override
    public Optional<CardEntity> getById(String id) {
        Optional<CardEntity> card = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                card = Optional.of(extractCardFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcCardRepository getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return card;
    }

    @Override
    public void create(CardEntity card) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            setAllFields(query, card);
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                card.setId(keys.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCardRepository create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(CardEntity card) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            final int counterIndex = setAllFields(query, card);
            query.setString(counterIndex + 1, card.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCardRepository update SQL exception: " + card.getId(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCardRepository delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcCardRepository Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
    }

    @Override
    public List<CardEntity> searchCardsByPercent(int percent) {
        List<CardEntity> cards = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_PERCENT)) {
            query.setInt(1, percent);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                cards.add(extractCardFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCardRepository.searchCardsByPercent SQL exception: ", e);
            throw new ServerException(e);
        }
        return cards;
    }

    @Override
    public List<CardEntity> searchCardsByCustomerSurname(String surname) {
        List<CardEntity> cards = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_CUSTOMER_SURNAME)) {
            query.setString(1, surname);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                cards.add(extractCardFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCardRepository.searchCardsByCustomerSurname SQL exception: ", e);
            throw new ServerException(e);
        }
        return cards;
    }

    protected static CardEntity extractCardFromResultSet(ResultSet resultSet) throws SQLException {
        return CardEntity.builder()
                .id(resultSet.getString(ID))
                .customerSurname(resultSet.getString(CUSTOMER_SURNAME))
                .customerName(resultSet.getString(CUSTOMER_NAME))
                .customerPatronymic(resultSet.getString(CUSTOMER_PATRONYMIC))
                .phoneNumber(resultSet.getString(PHONE_NUMBER))
                .city(resultSet.getString(CITY))
                .street(resultSet.getString(STREET))
                .zipCode(resultSet.getString(ZIP_CODE))
                .percent(resultSet.getInt(PERCENT))
                .build();
    }

    private static int setAllFields(PreparedStatement query, CardEntity card) throws SQLException {
        int index = 0;
        query.setString(++index, card.getCustomerSurname());
        query.setString(++index, card.getCustomerName());
        query.setString(++index, card.getCustomerPatronymic());
        query.setString(++index, card.getPhoneNumber());
        query.setString(++index, card.getCity());
        query.setString(++index, card.getStreet());
        query.setString(++index, card.getZipCode());
        query.setInt(++index, card.getPercent());
        return index;
    }
}
