package zlagoda.zlagoda.repository.jdbc;

import zlagoda.zlagoda.repository.BaseRepositoryConnection;
import zlagoda.zlagoda.repository.BaseRepositoryFactory;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.SQLException;

public class JdbcRepositoryFactory extends BaseRepositoryFactory {

    // TODO: Add a logger

    private DataSource dataSource;

    public JdbcRepositoryFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/supermarket");
            // TODO: Add web.xml (WEB-INF) and context.xml (META-INF)

        }
        catch (Exception e) {
            //LOGGER.error("Can't load pool connection from Initial Context", e);
            //throw new ServerException(e);
        }
    }

    @Override
    public BaseRepositoryConnection getConnection() {
        try {
            return new JdbcRepositoryConnection(dataSource.getConnection());
        }
        catch (SQLException e) {
            //LOGGER.error("Can't get DB connection to the data source", e);
            //throw new ServerException(e);
            return null;
        }
    }
}
