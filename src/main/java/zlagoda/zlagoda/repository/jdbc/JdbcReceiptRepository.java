package zlagoda.zlagoda.repository.jdbc;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.ReceiptEntity;
import zlagoda.zlagoda.exception.ServerException;
import zlagoda.zlagoda.repository.ReceiptRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class JdbcReceiptRepository implements ReceiptRepository {

    private static final Logger LOGGER = LogManager.getLogger(JdbcReceiptRepository.class);

    private static final String GET_ALL = "SELECT * FROM receipt";
    private static final String GET_BY_ID = "SELECT * FROM receipt WHERE check_number=?";
    private static final String CREATE = "INSERT INTO receipt " +
            "(print_date, sum_total, vat, id_employee, card_number) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE receipt SET " +
            "print_date=?, sum_total=?, vat=?, id_employee=?, card_number=?" +
            "WHERE check_number=?";
    private static final String DELETE = "DELETE FROM receipt WHERE check_number=?";
    private static final String GET_BY_USER_AND_TIME_PERIOD = "SELECT * FROM receipt " +
            "WHERE id_employee=? AND print_date>=? AND print_date<=?";
    private static final String GET_BY_TIME_PERIOD = "SELECT * FROM receipt " +
            "WHERE print_date>=? AND print_date<=?";
    private static final String GET_SUM_TOTAL_BY_USER_AND_TIME_PERIOD = "SELECT SUM(sum_total) FROM receipt " +
            "WHERE id_employee=? AND print_date>=? AND print_date<=?";
    private static final String GET_SUM_TOTAL_BY_TIME_PERIOD = "SELECT SUM(sum_total) FROM receipt " +
            "WHERE print_date>=? AND print_date<=?";

    private static final String ID = "check_number";
    private static final String PRINT_DATE = "print_date";
    private static final String SUM_TOTAL = "sum_total";
    private static final String VAT = "vat";
    private static final String USER_ID = "id_employee";
    private static final String CARD_ID = "card_number";

    @Setter
    private Connection connection;
    private final boolean connectionShouldBeClosed;

    public JdbcReceiptRepository(Connection connection) {
        this.connection = connection;
        this.connectionShouldBeClosed = false;
    }

    @Override
    public List<ReceiptEntity> getAll() {
        List<ReceiptEntity> receipts = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                receipts.add(extractReceiptFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcReceiptRepository getAll SQL exception", e);
            throw new ServerException(e);
        }
        return receipts;
    }

    @Override
    public Optional<ReceiptEntity> getById(String id) {
        Optional<ReceiptEntity> receipt = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                receipt = Optional.of(extractReceiptFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcReceiptRepository getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return receipt;
    }

    @Override
    public void create(ReceiptEntity receipt) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            setAllFields(query, receipt);
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                receipt.setId(keys.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcReceiptRepository create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(ReceiptEntity receipt) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            final int counterIndex = setAllFields(query, receipt);
            query.setString(counterIndex + 1, receipt.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcReceiptRepository update SQL exception: " + receipt.getId(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcReceiptRepository delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcReceiptRepository Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
    }

    @Override
    public List<ReceiptEntity> searchReceiptsByUserAndTimePeriod(int userId, LocalDate timeStart, LocalDate timeEnd) {
        List<ReceiptEntity> receipts = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_USER_AND_TIME_PERIOD)) {
            query.setInt(1, userId);
            query.setDate(2, Date.valueOf(timeStart));
            query.setDate(3, Date.valueOf(timeEnd));
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                receipts.add(extractReceiptFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcReceiptRepository.searchReceiptsByUserAndTimePeriod SQL exception: ", e);
            throw new ServerException(e);
        }
        return receipts;
    }

    @Override
    public List<ReceiptEntity> searchReceiptsByTimePeriod(LocalDate timeStart, LocalDate timeEnd) {
        List<ReceiptEntity> receipts = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_TIME_PERIOD)) {
            query.setDate(1, Date.valueOf(timeStart));
            query.setDate(2, Date.valueOf(timeEnd));
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                receipts.add(extractReceiptFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcReceiptRepository.searchReceiptsByTimePeriod SQL exception: ", e);
            throw new ServerException(e);
        }
        return receipts;
    }

    @Override
    public Double getReceiptsTotalValueByUserAndTimePeriod(int userId, LocalDate timeStart, LocalDate timeEnd) {
        double result = 0;
        try (PreparedStatement query = connection.prepareStatement(GET_SUM_TOTAL_BY_USER_AND_TIME_PERIOD)) {
            query.setInt(1, userId);
            query.setDate(2, Date.valueOf(timeStart));
            query.setDate(3, Date.valueOf(timeEnd));
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                result = rs.getDouble(1);
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcReceiptRepository.getReceiptsTotalValueByUserAndTimePeriod SQL exception: ", e);
            throw new ServerException(e);
        }
        return result;
    }

    @Override
    public Double getReceiptsTotalValueByTimePeriod(LocalDate timeStart, LocalDate timeEnd) {
        double result = 0;
        try (PreparedStatement query = connection.prepareStatement(GET_SUM_TOTAL_BY_TIME_PERIOD)) {
            query.setDate(1, Date.valueOf(timeStart));
            query.setDate(2, Date.valueOf(timeEnd));
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                result = rs.getDouble(1);
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcReceiptRepository.getReceiptsTotalValueByTimePeriod SQL exception: ", e);
            throw new ServerException(e);
        }
        return result;
    }

    protected static ReceiptEntity extractReceiptFromResultSet(ResultSet resultSet) throws SQLException {
        return ReceiptEntity.builder()
                .id(resultSet.getString(ID))
                .printDate(resultSet.getDate(PRINT_DATE).toLocalDate())
                .sumTotal(resultSet.getDouble(SUM_TOTAL))
                .vat(resultSet.getDouble(VAT))
                .userId(resultSet.getInt(USER_ID))
                .cardId(resultSet.getInt(CARD_ID))
                .build();
    }

    private static int setAllFields(PreparedStatement query, ReceiptEntity receipt) throws SQLException {
        int index = 0;
        query.setDate(++index, Date.valueOf(receipt.getPrintDate()));
        query.setDouble(++index, receipt.getSumTotal());
        query.setDouble(++index, receipt.getVat());
        query.setInt(++index, receipt.getUserId());
        query.setInt(++index, receipt.getCardId());
        return index;
    }
}
