package zlagoda.zlagoda.repository;

// Do we even need it?
public abstract class BaseRepositoryFactory {
    // TODO: Add a logger

    private static BaseRepositoryFactory repositoryFactory;

    public abstract BaseRepositoryConnection getConnection();

    public static BaseRepositoryFactory getRepositoryFactory() {
        // TODO: Implement
        return repositoryFactory;
    }
}
