package zlagoda.zlagoda.repository.jdbc;

import lombok.Getter;
import zlagoda.zlagoda.repository.BaseRepositoryConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcRepositoryConnection implements BaseRepositoryConnection {
    // TODO: Add a logger

    @Getter
    private final Connection connection;
    private boolean inTransaction = false;

    public JdbcRepositoryConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void begin() {
        try {
            connection.setAutoCommit(false);
            inTransaction = true;
            //LOGGER.info("Transaction has begun");
        } catch (SQLException e) {
            // LOGGER.error("JdbcDaoConnection begin error", e);

            // TODO: Implement ServerException
            //throw new ServerException(e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            inTransaction = false;
            //LOGGER.info("Transaction has been committed");
        } catch (SQLException e) {
            //LOGGER.error("JdbcDaoConnection commit error", e);
            // throw new ServerException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            inTransaction = false;
            //LOGGER.info("Transaction has been rolled back");
        } catch (SQLException e) {
            //LOGGER.error("JdbcDaoConnection rollback error", e);
            //throw new ServerException(e);
        }
    }

    @Override
    public void close() {
        if (inTransaction) {
            rollback();
        }
        try {
            connection.close();
            //LOGGER.info("Transaction has been closed");
        } catch (SQLException e) {
            //LOGGER.error("JdbcDaoConnection close error", e);
            //throw new ServerException(e);
        }
    }
}
