package zlagoda.zlagoda.repository.jdbc;

import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.CategoryEntity;
import zlagoda.zlagoda.exception.ServerException;
import zlagoda.zlagoda.repository.CategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCategoryRepository implements CategoryRepository {

    private static final Logger LOGGER = LogManager.getLogger(JdbcCategoryRepository.class);

    private static final String GET_ALL = "SELECT * FROM category";
    private static final String GET_BY_ID = "SELECT * FROM category WHERE category_number=?";
    private static final String CREATE = "INSERT INTO category (category_name) VALUES (?)";
    private static final String UPDATE = "UPDATE category SET category_name=? WHERE category_number=?";
    private static final String DELETE = "DELETE FROM category WHERE category_number=?";

    private static final String ID = "category_number";
    private static final String NAME = "category_name";

    @Setter
    private Connection connection;
    private final boolean connectionShouldBeClosed;

    public JdbcCategoryRepository(Connection connection) {
        this.connection = connection;
        this.connectionShouldBeClosed = false;
    }

    public JdbcCategoryRepository(Connection connection, boolean connectionShouldBeClosed) {
        this.connection = connection;
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }

    @Override
    public List<CategoryEntity> getAll() {
        List<CategoryEntity> categories = new ArrayList<>();
        try (Statement query = connection.createStatement(); ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                categories.add(extractCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryRepository getAll SQL exception", e);
            throw new ServerException(e);
        }
        return categories;
    }

    @Override
    public Optional<CategoryEntity> getById(Integer id) {
        Optional<CategoryEntity> category = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                category = Optional.of(extractCategoryFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryRepository getById SQL exception: " + id, e);
            throw new ServerException(e);
        }
        return category;
    }

    @Override
    public void create(CategoryEntity category) {
        try (PreparedStatement query = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, category.getName());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                category.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryRepository create SQL exception", e);
            throw new ServerException(e);
        }
    }

    @Override
    public void update(CategoryEntity category) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, category.getName());
            query.setInt(2, category.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryRepository update SQL exception: " + category.getId(), e);
            throw new ServerException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("JdbcCategoryRepository delete SQL exception: " + id, e);
            throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        if (connectionShouldBeClosed) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("JdbcCategoryRepository Connection can't be closed", e);
                throw new ServerException(e);
            }
        }
    }

    protected static CategoryEntity extractCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        return CategoryEntity.builder()
                .id(resultSet.getInt(ID))
                .name(resultSet.getString(NAME))
                .build();
    }
}
