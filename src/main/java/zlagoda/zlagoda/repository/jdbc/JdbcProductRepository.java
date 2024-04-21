package zlagoda.zlagoda.repository.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.ProductEntity;
import zlagoda.zlagoda.exception.ServerException;
import zlagoda.zlagoda.repository.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcProductRepository implements ProductRepository {

    private static final Logger LOGGER = LogManager.getLogger(JdbcProductRepository.class);

    private static final String GET_ALL = "SELECT * FROM product";
    private static final String GET_BY_ID = "SELECT * FROM product WHERE id_product=?";
    private static final String CREATE = "INSERT INTO product " +
            "(product_name, characteristics, category_number) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE product SET " +
            "product_name=?, characteristics=?, category_number=?" +
            "WHERE id_product=?";
    private static final String DELETE = "DELETE FROM product WHERE id_product=?";
    private static final String GET_BY_CATEGORY_ID = "SELECT * FROM product where category_number=?";
    private static final String GET_BY_PRODUCT_NAME = "SELECT * FROM product where product_name=?";

    private static final String ID = "id_product";
    private static final String PRODUCT_NAME = "product_name";
    private static final String CHARACTERISTICS = "characteristics";
    private static final String CATEGORY_ID = "category_number";

    private Connection connection;
    private boolean connectionShouldBeClosed;

    public JdbcProductRepository(Connection connection) {
        this.connection = connection;
        this.connectionShouldBeClosed = false;
    }

    public JdbcProductRepository(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    public void setConnection(Connection connection) { this.connection = connection; }

    @Override
    public List<ProductEntity> getAll() {
        List<ProductEntity> products = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcProductRepository getAll SQL exception", e);
            throw new ServerException(e);
        }
        return products;
    }

    @Override
    public Optional<ProductEntity> getById(Integer id) {
        Optional<ProductEntity> product = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                product = Optional.of(extractProductFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcProductRepository getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return product;
    }

    @Override
    public void create(ProductEntity product) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            setAllFields(query, product);
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                product.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcProductRepository create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(ProductEntity product) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            final int counterIndex = setAllFields(query, product);
            query.setInt(counterIndex + 1, product.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcProductRepository update SQL exception: " + product.getId(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcProductRepository delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcProductRepository Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
    }

    @Override
    public List<ProductEntity> searchProductsByCategory(int categoryId) {
        List<ProductEntity> products = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_CATEGORY_ID)) {
            query.setInt(1, categoryId);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcProductRepository.searchProductsByCategory SQL exception: ", e);
            throw new ServerException(e);
        }
        return products;
    }

    @Override
    public List<ProductEntity> searchProductsByName(String name) {
        List<ProductEntity> products = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_PRODUCT_NAME)) {
            query.setString(1, name);
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcProductRepository.searchProductsByName SQL exception: ", e);
            throw new ServerException(e);
        }
        return products;
    }

    protected static ProductEntity extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        return ProductEntity.builder()
                .id(resultSet.getInt(ID))
                .name(resultSet.getString(PRODUCT_NAME))
                .characteristics(resultSet.getString(CHARACTERISTICS))
                .categoryId(resultSet.getInt(CATEGORY_ID))
                .build();
    }

    private static int setAllFields(PreparedStatement query, ProductEntity product) throws SQLException {
        int index = 0;
        query.setString(++index, product.getName());
        query.setString(++index, product.getCharacteristics());
        query.setInt(++index, product.getCategoryId());
        return index;
    }
}
