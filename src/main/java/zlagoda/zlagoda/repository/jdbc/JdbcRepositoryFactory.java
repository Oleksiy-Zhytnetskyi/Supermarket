package zlagoda.zlagoda.repository.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.exception.ServerException;
import zlagoda.zlagoda.repository.BaseRepositoryConnection;
import zlagoda.zlagoda.repository.BaseRepositoryFactory;
import zlagoda.zlagoda.repository.UserRepository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
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
            LOGGER.error("Can't get DB Connection for JdbcUserDao creation", e);
            throw new ServerException(e);
        }
    }

    @Override
    public UserRepository createUserRepository(BaseRepositoryConnection connection) {
        return null;
    }
}