package zlagoda.zlagoda.repository.jdbc;

import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.exception.ServerException;
import zlagoda.zlagoda.repository.StoreProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcStoreProductRepository implements StoreProductRepository {

    private static final Logger LOGGER = LogManager.getLogger(JdbcStoreProductRepository.class);

    private static final String GET_ALL = "SELECT * FROM store_product ORDER BY products_number";
    private static final String GET_ALL_SORTED_BY_NAME = "SELECT * " +
            "FROM store_product sp " +
            "INNER JOIN product p ON sp.id_product = p.id_product " +
            "ORDER BY p.product_name";
    private static final String GET_BY_ID = "SELECT * FROM store_product WHERE upc=?";
    private static final String CREATE = "INSERT INTO store_product " +
            "(upc_prom, id_product, selling_price, products_number, promotional_product) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE store_product SET " +
            "upc_prom=?, id_product=?, selling_price=?, products_number=?, promotional_product=? " +
            "WHERE upc=?";
    private static final String DELETE = "DELETE FROM store_product WHERE upc=?";
    private static final String GET_DISCOUNTED = "SELECT * FROM store_product WHERE promotional_product=true";
    private static final String GET_NON_DISCOUNTED = "SELECT * FROM store_product WHERE promotional_product=false";

    private static final String ID = "upc";
    private static final String SELLING_PRICE = "selling_price";
    private static final String PRODUCT_QUANTITY = "products_number";
    private static final String IS_PROMOTIONAL = "promotional_product";
    private static final String PROMOTIONAL_ID = "upc_prom";
    private static final String PRODUCT_ID = "id_product";

    @Setter
    private Connection connection;
    private final boolean connectionShouldBeClosed;

    public JdbcStoreProductRepository(Connection connection) {
        this.connection = connection;
        this.connectionShouldBeClosed = false;
    }

    public JdbcStoreProductRepository(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    @Override
    public List<StoreProductEntity> getAll() {
        List<StoreProductEntity> storeProducts = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                storeProducts.add(extractStoreProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStoreProductRepository getAll SQL exception", e);
            throw new ServerException(e);
        }
        return storeProducts;
    }

    @Override
    public List<StoreProductEntity> getAllSortedByName() {
        List<StoreProductEntity> storeProducts = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL_SORTED_BY_NAME)) {
            while (resultSet.next()) {
                storeProducts.add(extractStoreProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStoreProductRepository getAllSortedByName SQL exception", e);
            throw new ServerException(e);
        }
        return storeProducts;
    }

    @Override
    public Optional<StoreProductEntity> getById(Integer id) {
        Optional<StoreProductEntity> storeProduct = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                storeProduct = Optional.of(extractStoreProductFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcStoreProductRepository getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return storeProduct;
    }

    @Override
    public void create(StoreProductEntity storeProduct) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            setAllFields(query, storeProduct);
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                storeProduct.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStoreProductRepository create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(StoreProductEntity storeProduct) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            final int counterIndex = setAllFields(query, storeProduct);
            query.setInt(counterIndex + 1, storeProduct.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcStoreProductRepository update SQL exception: " + storeProduct.getId(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcStoreProductRepository delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcStoreProductRepository Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
    }

    @Override
    public List<StoreProductEntity> getDiscountedProducts() {
        List<StoreProductEntity> storeProducts = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_DISCOUNTED)) {
            while (resultSet.next()) {
                storeProducts.add(extractStoreProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStoreProductRepository.getDiscountedProducts SQL exception", e);
            throw new ServerException(e);
        }
        return storeProducts;
    }

    @Override
    public List<StoreProductEntity> getNonDiscountedProducts() {
        List<StoreProductEntity> storeProducts = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_NON_DISCOUNTED)) {
            while (resultSet.next()) {
                storeProducts.add(extractStoreProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcStoreProductRepository.getNonDiscountedProducts SQL exception", e);
            throw new ServerException(e);
        }
        return storeProducts;
    }

    protected StoreProductEntity extractStoreProductFromResultSet(ResultSet resultSet) throws SQLException {
        return StoreProductEntity.builder()
                .id(resultSet.getInt(ID))
                .sellingPrice(resultSet.getDouble(SELLING_PRICE))
                .productQuantity(resultSet.getInt(PRODUCT_QUANTITY))
                .isPromotional(resultSet.getBoolean(IS_PROMOTIONAL))
                .productId(resultSet.getInt(PRODUCT_ID))
                .promotionalId(resultSet.getInt(PROMOTIONAL_ID))
                .build();
    }

    private static int setAllFields(PreparedStatement query, StoreProductEntity storeProduct) throws SQLException {
        int index = 0;
        if(storeProduct.getPromotionalId() != null)
            query.setInt(++index, storeProduct.getPromotionalId());
        else
            query.setNull(++index, Types.INTEGER);
        query.setInt(++index, storeProduct.getProductId());
        query.setDouble(++index, storeProduct.getSellingPrice());
        query.setInt(++index, storeProduct.getProductQuantity());
        query.setBoolean(++index, storeProduct.getIsPromotional());
        return index;
    }
}
