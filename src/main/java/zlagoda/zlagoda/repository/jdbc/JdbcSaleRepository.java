package zlagoda.zlagoda.repository.jdbc;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.SaleEntity;
import zlagoda.zlagoda.entity.keys.SaleEntityComplexKey;
import zlagoda.zlagoda.exception.ServerException;
import zlagoda.zlagoda.repository.SaleRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class JdbcSaleRepository implements SaleRepository {

    private static final Logger LOGGER = LogManager.getLogger(JdbcSaleRepository.class);

    private static final String GET_ALL = "SELECT * FROM sale";
    private static final String GET_BY_ID = "SELECT * FROM sale WHERE upc=? AND check_number=?";
    private static final String CREATE = "INSERT INTO sale " +
            "(product_number, selling_price, upc, check_number) " +
            "VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE sale SET " +
            "product_number=?, selling_price=? " +
            "WHERE upc=? AND check_number=?";
    private static final String DELETE = "DELETE FROM sale WHERE upc=? AND check_number=?";
    private static final String GET_SOLD_PRODUCT_QUANTITY_BY_PRODUCT_AND_TIME_PERIOD = "SELECT SUM(s.product_number) " +
            "FROM sale s " +
            "INNER JOIN receipt r ON s.check_number = r.check_number " +
            "INNER JOIN store_product sp ON s.upc = sp.upc " +
            "WHERE sp.id_product=? AND r.print_date BETWEEN ? AND ?";

    private static final String ID_UPC = "upc";
    private static final String ID_RECEIPT_ID = "check_number";
    private static final String PRODUCT_QUANTITY = "product_number";
    private static final String SELLING_PRICE = "selling_price";

    @Setter
    private Connection connection;
    private final boolean connectionShouldBeClosed;

    public JdbcSaleRepository(Connection connection) {
        this.connection = connection;
        this.connectionShouldBeClosed = false;
    }

    @Override
    public List<SaleEntity> getAll() {
        List<SaleEntity> sales = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                sales.add(extractSaleFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcSaleRepository getAll SQL exception", e);
            throw new ServerException(e);
        }
        return sales;
    }

    @Override
    public Optional<SaleEntity> getById(SaleEntityComplexKey id) {
        Optional<SaleEntity> sale = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id.getUPC());
            query.setInt(2, id.getReceiptId());
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                sale = Optional.of(extractSaleFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcSaleRepository getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return sale;
    }

    @Override
    public void create(SaleEntity sale) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            final int counterIndex = setAllFields(query, sale);
            query.setInt(counterIndex + 1, sale.getPk().getUPC());
            query.setInt(counterIndex + 2, sale.getPk().getReceiptId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcSaleRepository create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(SaleEntity sale) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            final int counterIndex = setAllFields(query, sale);
            query.setInt(counterIndex + 1, sale.getPk().getUPC());
            query.setInt(counterIndex + 2, sale.getPk().getReceiptId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcSaleRepository update SQL exception: " + sale.getPk(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(SaleEntityComplexKey id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setInt(1, id.getUPC());
            query.setInt(2, id.getReceiptId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcSaleRepository delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcSaleRepository Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
    }

    @Override
    public Integer getSoldProductQuantityByProductAndTimePeriod(int productId, LocalDate timeStart, LocalDate timeEnd) {
        int result = 0;
        try (PreparedStatement query = connection.prepareStatement(GET_SOLD_PRODUCT_QUANTITY_BY_PRODUCT_AND_TIME_PERIOD)) {
            query.setInt(1, productId);
            query.setDate(2, Date.valueOf(timeStart));
            query.setDate(3, Date.valueOf(timeEnd));
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcSaleRepository.getSoldProductQuantityByProductAndTimePeriod SQL exception: ", e);
            throw new ServerException(e);
        }
        return result;
    }

    protected static SaleEntity extractSaleFromResultSet(ResultSet resultSet) throws SQLException {
        return SaleEntity.builder()
                .pk(new SaleEntityComplexKey(resultSet.getInt(ID_UPC), resultSet.getInt(ID_RECEIPT_ID)))
                .productQuantity(resultSet.getInt(PRODUCT_QUANTITY))
                .sellingPrice(resultSet.getDouble(SELLING_PRICE))
                .build();
    }

    private static int setAllFields(PreparedStatement query, SaleEntity sale) throws SQLException {
        int index = 0;
        query.setInt(++index, sale.getProductQuantity());
        query.setDouble(++index, sale.getSellingPrice());
        return index;
    }
}
