package zlagoda.zlagoda.service;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.ReceiptEntity;
import zlagoda.zlagoda.entity.SaleEntity;
import zlagoda.zlagoda.entity.keys.SaleEntityComplexKey;
import zlagoda.zlagoda.repository.BaseRepositoryFactory;
import zlagoda.zlagoda.repository.ReceiptRepository;
import zlagoda.zlagoda.repository.SaleRepository;
import zlagoda.zlagoda.view.ReceiptView;
import zlagoda.zlagoda.view.SaleView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class SaleService {

    private static final Logger LOGGER = LogManager.getLogger(SaleService.class);

    private static final String GET_ALL_SALES = "Get all sales";
    private static final String GET_SALE_BY_ID = "Get receipt by id: %s";
    private static final String CREATE_SALE = "Create receipt: %s";
    private static final String UPDATE_SALE = "Update receipt: %s";
    private static final String DELETE_SALE = "Delete receipt: %s";
    private static final String GET_SOLD_PRODUCT_QUANTITY_BY_PRODUCT_AND_TIME_PERIOD = "Get sold product " +
            "quantity by productId: %d and time period (timeStart: %s, timeEnd: %s)";

    private final BaseRepositoryFactory repositoryFactory;

    private static class Holder {
        static final SaleService INSTANCE = new SaleService(BaseRepositoryFactory.getRepositoryFactory());
    }

    public static SaleService getInstance() { return Holder.INSTANCE; }

    public List<SaleEntity> getAllSales() {
        LOGGER.info(GET_ALL_SALES);
        try (SaleRepository repository = repositoryFactory.createSaleRepository()) {
            return repository.getAll();
        }
    }

    public Optional<SaleEntity> getSaleById(SaleEntityComplexKey id) {
        LOGGER.info(String.format(GET_SALE_BY_ID, id));
        try (SaleRepository repository = repositoryFactory.createSaleRepository()) {
            return repository.getById(id);
        }
    }

    public void createSale(SaleView saleView) {
        LOGGER.info(String.format(CREATE_SALE, saleView.getPk()));
        SaleEntity sale = buildSaleFromView(saleView);
        try (SaleRepository repository = repositoryFactory.createSaleRepository()) {
            repository.create(sale);
        }
    }

    public void updateReceipt(SaleView saleView) {
        LOGGER.info(String.format(UPDATE_SALE, saleView.getPk()));
        SaleEntity sale = buildSaleFromView(saleView);
        try (SaleRepository repository = repositoryFactory.createSaleRepository()) {
            repository.update(sale);
        }
    }

    public void deleteSale(SaleEntityComplexKey id) {
        LOGGER.info(String.format(DELETE_SALE, id));
        try (SaleRepository repository = repositoryFactory.createSaleRepository()) {
            repository.delete(id);
        }
    }

    public Integer getSoldProductQuantityByProductAndTimePeriod(int productId, LocalDate timeStart, LocalDate timeStop) {
        LOGGER.info(String.format(GET_SOLD_PRODUCT_QUANTITY_BY_PRODUCT_AND_TIME_PERIOD, productId, timeStop, timeStop));
        try (SaleRepository repository = repositoryFactory.createSaleRepository()) {
            return repository.getSoldProductQuantityByProductAndTimePeriod(productId, timeStart, timeStop);
        }
    }

    private static SaleEntity buildSaleFromView(SaleView view) {
        return SaleEntity.builder()
                .pk(view.getPk())
                .productQuantity(view.getProductQuantity())
                .sellingPrice(view.getSellingPrice())
                .build();
    }
}
