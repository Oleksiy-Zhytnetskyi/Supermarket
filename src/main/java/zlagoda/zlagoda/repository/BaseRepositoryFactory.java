package zlagoda.zlagoda.repository;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.exception.ServerException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Do we even need it?
public abstract class BaseRepositoryFactory {
    private static final Logger LOGGER = LogManager.getLogger(BaseRepositoryFactory.class);
    private static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";
    private static BaseRepositoryFactory repositoryFactory;
    public abstract BaseRepositoryConnection getConnection();
    public abstract UserRepository createUserRepository();
    public abstract UserRepository createUserRepository(BaseRepositoryConnection connection);
    public abstract CategoryRepository createCategoryRepository();
    public abstract CategoryRepository createCategoryRepository(BaseRepositoryConnection connection);
    public abstract CardRepository createCardRepository();
    public abstract CardRepository createCardRepository(BaseRepositoryConnection connection);
    public abstract ProductRepository createProductRepository();
    public abstract ProductRepository createProductRepository(BaseRepositoryConnection connection);
    public abstract ReceiptRepository createReceiptRepository();
    public abstract ReceiptRepository createReceiptRepository(BaseRepositoryConnection connection);
    public abstract StoreProductRepository createStoreProductRepository();
    public abstract StoreProductRepository createStoreProductRepository(BaseRepositoryConnection connection);

    public static BaseRepositoryFactory getRepositoryFactory() {
        if(repositoryFactory == null) {
            try {
                InputStream inputStream = BaseRepositoryFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProperties = new Properties();
                dbProperties.load(inputStream);
                String factoryClass = dbProperties.getProperty(DB_FACTORY_CLASS);
                repositoryFactory = (BaseRepositoryFactory) Class.forName(factoryClass).newInstance();
            } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                LOGGER.error("Can't load inputStream db config file to properties object", e);
                throw new ServerException(e);
            }
        }
        return repositoryFactory;
    }
}
