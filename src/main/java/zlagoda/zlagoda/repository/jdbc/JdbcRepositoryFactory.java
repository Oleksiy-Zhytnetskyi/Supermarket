package zlagoda.zlagoda.repository.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.exception.ServerException;
import zlagoda.zlagoda.repository.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.SQLException;

public class JdbcRepositoryFactory extends BaseRepositoryFactory {

    private static final Logger LOGGER = LogManager.getLogger(JdbcRepositoryFactory.class);

    private DataSource dataSource;

    public JdbcRepositoryFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/zlagoda");
            // TODO: Add web.xml (WEB-INF) and context.xml (META-INF)

        }
        catch (Exception e) {
            LOGGER.error("Can't load pool connection from Initial Context", e);
            throw new ServerException(e);
        }
    }

    @Override
    public BaseRepositoryConnection getConnection() {
        try {
            return new JdbcRepositoryConnection(dataSource.getConnection());
        }
        catch (SQLException e) {
            LOGGER.error("Can't get DB connection to the data source", e);
            throw new ServerException(e);
        }
    }

    @Override
    public UserRepository createUserRepository() {
        try {
            return new JdbcUserRepository(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcUserRepository creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public UserRepository createUserRepository(BaseRepositoryConnection connection) {
        JdbcRepositoryConnection jdbcConnection = (JdbcRepositoryConnection)connection;
        return new JdbcUserRepository(jdbcConnection.getConnection());
    }

    @Override
    public CategoryRepository createCategoryRepository() {
        try {
            return new JdbcCategoryRepository(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcCategoryRepository creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public CategoryRepository createCategoryRepository(BaseRepositoryConnection connection) {
        JdbcRepositoryConnection jdbcConnection = (JdbcRepositoryConnection)connection;
        return new JdbcCategoryRepository(jdbcConnection.getConnection());
    }

    @Override
    public CardRepository createCardRepository() {
        try {
            return new JdbcCardRepository(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcCardRepository creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public CardRepository createCardRepository(BaseRepositoryConnection connection) {
        JdbcRepositoryConnection jdbcConnection = (JdbcRepositoryConnection)connection;
        return new JdbcCardRepository(jdbcConnection.getConnection());
    }

    @Override
    public ProductRepository createProductRepository() {
        try {
            return new JdbcProductRepository(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcProductRepository creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public ProductRepository createProductRepository(BaseRepositoryConnection connection) {
        JdbcRepositoryConnection jdbcConnection = (JdbcRepositoryConnection)connection;
        return new JdbcProductRepository(jdbcConnection.getConnection());
    }

    @Override
    public ReceiptRepository createReceiptRepository() {
        try {
            return new JdbcReceiptRepository(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcReceiptRepository creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public ReceiptRepository createReceiptRepository(BaseRepositoryConnection connection) {
        JdbcRepositoryConnection jdbcConnection = (JdbcRepositoryConnection)connection;
        return new JdbcReceiptRepository(jdbcConnection.getConnection());
    }

    @Override
    public StoreProductRepository createStoreProductRepository() {
        try {
            return new JdbcStoreProductRepository(dataSource.getConnection(), true);
        } catch (SQLException e) {
            LOGGER.error("Can't get DB Connection for JdbcStoreProductRepository creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public StoreProductRepository createStoreProductRepository(BaseRepositoryConnection connection) {
        JdbcRepositoryConnection jdbcConnection = (JdbcRepositoryConnection)connection;
        return new JdbcStoreProductRepository(jdbcConnection.getConnection());
    }
}
