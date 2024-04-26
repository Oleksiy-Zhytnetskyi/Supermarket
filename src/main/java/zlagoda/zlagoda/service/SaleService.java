package zlagoda.zlagoda.service;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.ReceiptEntity;
import zlagoda.zlagoda.entity.SaleEntity;
import zlagoda.zlagoda.entity.StoreProductEntity;
import zlagoda.zlagoda.entity.keys.SaleEntityComplexKey;
import zlagoda.zlagoda.repository.BaseRepositoryFactory;
import zlagoda.zlagoda.repository.SaleRepository;
import zlagoda.zlagoda.view.ReceiptView;
import zlagoda.zlagoda.view.SaleView;
import zlagoda.zlagoda.view.StoreProductView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class SaleService {

    private static final ReceiptService receiptService = ReceiptService.getInstance();
    private static final StoreProductService storeProductService = StoreProductService.getInstance();

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
        executeCreateSale(saleView);
    }

    public void updateSale(SaleView saleView) {
        LOGGER.info(String.format(UPDATE_SALE, saleView.getPk()));
        SaleEntity sale = buildSaleFromView(saleView);
        try (SaleRepository repository = repositoryFactory.createSaleRepository()) {
            repository.update(sale);
        }
    }

    public void deleteSale(SaleEntityComplexKey id) {
        LOGGER.info(String.format(DELETE_SALE, id));
        executeDeleteSale(id);
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

    private void executeCreateSale(SaleView saleView) {
        Optional<SaleEntity> saleEntity = getSaleById(saleView.getPk());
        if (saleEntity.isEmpty()) {
            calculateSellingPrice(saleView, null);
            updateCreateReceipt(saleView, null);
            updateCreateStoreProduct(saleView, null);
            SaleEntity sale = buildSaleFromView(saleView);
            try (SaleRepository repository = repositoryFactory.createSaleRepository()) {
                repository.create(sale);
            }
        } else {
            SaleEntity entity = saleEntity.get();
            calculateSellingPrice(saleView, entity);
            updateCreateReceipt(saleView, entity);
            updateCreateStoreProduct(saleView, entity);
            updateSale(saleView);
        }
    }

    private void calculateSellingPrice(SaleView saleView, SaleEntity saleEntity) {
        StoreProductEntity storeProductEntity = storeProductService.getStoreProductById(saleView.getPk().getUPC()).get();
        saleView.setSellingPrice(storeProductEntity.getSellingPrice() * saleView.getProductQuantity());
        if(saleEntity != null) {
            saleView.setSellingPrice(saleEntity.getSellingPrice() + saleView.getSellingPrice());
            saleView.setProductQuantity(saleView.getProductQuantity() + saleEntity.getProductQuantity());
        }
    }

    private void updateCreateReceipt(SaleView saleView, SaleEntity saleEntity) {
        ReceiptEntity receiptEntity = receiptService.getReceiptById(saleView.getPk().getReceiptId()).get();
        if(saleEntity == null) {
            receiptEntity.setSumTotal(receiptEntity.getSumTotal() + saleView.getSellingPrice());
            receiptEntity.setVat(receiptEntity.getVat() + (saleView.getSellingPrice() / 10));
        } else {
            receiptEntity.setSumTotal((receiptEntity.getSumTotal() - saleEntity.getSellingPrice()) + saleView.getSellingPrice());
            receiptEntity.setVat((receiptEntity.getVat() - (saleEntity.getSellingPrice() / 10)) + (saleView.getSellingPrice() / 10));
        }
        receiptService.updateReceipt(buildReceiptView(receiptEntity));
    }

    private void updateCreateStoreProduct(SaleView saleView, SaleEntity saleEntity) {
        StoreProductEntity storeProductEntity = storeProductService.getStoreProductById(saleView.getPk().getUPC()).get();
        if(saleEntity == null) {
            storeProductEntity.setProductQuantity(storeProductEntity.getProductQuantity() - saleView.getProductQuantity());
        } else {
            storeProductEntity.setProductQuantity((storeProductEntity.getProductQuantity() + saleEntity.getProductQuantity()) - saleView.getProductQuantity());
        }
        storeProductService.updateStoreProduct(buildStoreProductView(storeProductEntity));
    }

    private ReceiptView buildReceiptView(ReceiptEntity receiptEntity) {
        ReceiptView.ReceiptViewBuilder builder = ReceiptView.builder()
                .id(receiptEntity.getId())
                .printDate(receiptEntity.getPrintDate())
                .sumTotal(receiptEntity.getSumTotal())
                .vat(receiptEntity.getVat())
                .userId(receiptEntity.getUserId())
                .cardId(receiptEntity.getCardId());

        return builder.build();
    }

    private StoreProductView buildStoreProductView(StoreProductEntity storeProductEntity) {
        StoreProductView.StoreProductViewBuilder builder = StoreProductView.builder()
                .id(storeProductEntity.getId())
                .sellingPrice(storeProductEntity.getSellingPrice())
                .productQuantity(storeProductEntity.getProductQuantity())
                .isPromotional(storeProductEntity.getIsPromotional())
                .productId(storeProductEntity.getProductId());

        if (storeProductEntity.getPromotionalId() != 0) {
            builder.promotionalId(storeProductEntity.getPromotionalId());
        }
        else builder.promotionalId(null);

        return builder.build();
    }

    private void executeDeleteSale(SaleEntityComplexKey id) {
        SaleEntity saleEntity = getSaleById(id).get();
        updateDeleteReceipt(saleEntity);
        updateDeleteStoreProduct(saleEntity);
        try (SaleRepository repository = repositoryFactory.createSaleRepository()) {
            repository.delete(id);
        }
    }

    private void updateDeleteReceipt(SaleEntity saleEntity) {
        ReceiptEntity receiptEntity = receiptService.getReceiptById(saleEntity.getPk().getReceiptId()).get();
        receiptEntity.setSumTotal(receiptEntity.getSumTotal() - saleEntity.getSellingPrice());
        receiptEntity.setVat(receiptEntity.getVat() - (saleEntity.getSellingPrice() / 10));
        receiptService.updateReceipt(buildReceiptView(receiptEntity));
    }

    private void updateDeleteStoreProduct(SaleEntity saleEntity) {
        StoreProductEntity storeProductEntity = storeProductService.getStoreProductById(saleEntity.getPk().getUPC()).get();
        storeProductEntity.setProductQuantity(storeProductEntity.getProductQuantity() + saleEntity.getProductQuantity());
        storeProductService.updateStoreProduct(buildStoreProductView(storeProductEntity));
    }
}
