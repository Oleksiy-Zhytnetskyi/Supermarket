package zlagoda.zlagoda.repository;

public interface BaseRepositoryConnection extends AutoCloseable {
    void begin();

    void commit();

    void rollback();

    @Override
    void close();
}
