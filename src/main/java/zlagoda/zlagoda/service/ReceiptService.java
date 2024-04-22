package zlagoda.zlagoda.service;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.ReceiptEntity;
import zlagoda.zlagoda.repository.BaseRepositoryFactory;
import zlagoda.zlagoda.repository.ReceiptRepository;
import zlagoda.zlagoda.view.ReceiptView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ReceiptService {

    private static final Logger LOGGER = LogManager.getLogger(ReceiptService.class);

    private static final String GET_ALL_RECEIPTS = "Get all receipts";
    private static final String GET_RECEIPT_BY_ID = "Get receipt by id: %d";
    private static final String CREATE_RECEIPT = "Create receipt: %s";
    private static final String UPDATE_RECEIPT = "Update receipt: %s";
    private static final String DELETE_RECEIPT = "Delete receipt: %d";
    private static final String SEARCH_RECEIPTS_BY_USER_AND_TIME_PERIOD = "Search receipts by " +
            "userId: %d and time period (timeStart: %s, timeEnd: %s)";
    private static final String SEARCH_RECEIPTS_BY_TIME_PERIOD = "Search receipts by " +
            "time period (timeStart: %s, timeEnd: %s)";
    private static final String GET_RECEIPTS_TOTAL_VALUE_BY_USER_AND_TIME_PERIOD = "Get sum total of receipts by " +
            "userId: %d and time period (timeStart: %s, timeEnd: %s)";
    private static final String GET_RECEIPTS_TOTAL_VALUE_BY_TIME_PERIOD = "Get sum total of receipts by " +
            "time period (timeStart: %s, timeEnd: %s)";

    private final BaseRepositoryFactory repositoryFactory;

    private static class Holder {
        static final ReceiptService INSTANCE = new ReceiptService(BaseRepositoryFactory.getRepositoryFactory());
    }

    public static ReceiptService getInstance() { return Holder.INSTANCE; }

    public List<ReceiptEntity> getAllReceipts() {
        LOGGER.info(GET_ALL_RECEIPTS);
        try (ReceiptRepository repository = repositoryFactory.createReceiptRepository()) {
            return repository.getAll();
        }
    }

    public Optional<ReceiptEntity> getReceiptById(Integer id) {
        LOGGER.info(String.format(GET_RECEIPT_BY_ID, id));
        try (ReceiptRepository repository = repositoryFactory.createReceiptRepository()) {
            return repository.getById(id);
        }
    }

    public void createReceipt(ReceiptView receiptView) {
        LOGGER.info(String.format(CREATE_RECEIPT, receiptView.getId()));
        ReceiptEntity receipt = buildReceiptFromView(receiptView);
        try (ReceiptRepository repository = repositoryFactory.createReceiptRepository()) {
            repository.create(receipt);
        }
    }

    public void updateReceipt(ReceiptView receiptView) {
        LOGGER.info(String.format(UPDATE_RECEIPT, receiptView.getId()));
        ReceiptEntity receipt = buildReceiptFromView(receiptView);
        try (ReceiptRepository repository = repositoryFactory.createReceiptRepository()) {
            repository.update(receipt);
        }
    }

    public void deleteReceipt(Integer id) {
        LOGGER.info(String.format(DELETE_RECEIPT, id));
        try (ReceiptRepository repository = repositoryFactory.createReceiptRepository()) {
            repository.delete(id);
        }
    }

    public List<ReceiptEntity> searchReceiptsByUserAndTimePeriod(int userId, LocalDate timeStart, LocalDate timeStop) {
        LOGGER.info(String.format(SEARCH_RECEIPTS_BY_USER_AND_TIME_PERIOD, userId, timeStop, timeStop));
        try (ReceiptRepository repository = repositoryFactory.createReceiptRepository()) {
            return repository.searchReceiptsByUserAndTimePeriod(userId, timeStart, timeStop);
        }
    }

    public List<ReceiptEntity> searchReceiptsByTimePeriod(LocalDate timeStart, LocalDate timeStop) {
        LOGGER.info(String.format(SEARCH_RECEIPTS_BY_TIME_PERIOD, timeStop, timeStop));
        try (ReceiptRepository repository = repositoryFactory.createReceiptRepository()) {
            return repository.searchReceiptsByTimePeriod(timeStart, timeStop);
        }
    }

    public Double getReceiptsTotalValueByUserAndTimePeriod(int userId, LocalDate timeStart, LocalDate timeStop) {
        LOGGER.info(String.format(GET_RECEIPTS_TOTAL_VALUE_BY_USER_AND_TIME_PERIOD, userId, timeStop, timeStop));
        try (ReceiptRepository repository = repositoryFactory.createReceiptRepository()) {
            return repository.getReceiptsTotalValueByUserAndTimePeriod(userId, timeStart, timeStop);
        }
    }

    public Double getReceiptsTotalValueByTimePeriod(LocalDate timeStart, LocalDate timeStop) {
        LOGGER.info(String.format(GET_RECEIPTS_TOTAL_VALUE_BY_TIME_PERIOD, timeStop, timeStop));
        try (ReceiptRepository repository = repositoryFactory.createReceiptRepository()) {
            return repository.getReceiptsTotalValueByTimePeriod(timeStart, timeStop);
        }
    }

    private static ReceiptEntity buildReceiptFromView(ReceiptView view) {
        return ReceiptEntity.builder()
                .id(view.getId())
                .printDate(view.getPrintDate())
                .sumTotal(view.getSumTotal())
                .vat(view.getVat())
                .userId(view.getUserId())
                .cardId(view.getCardId())
                .build();
    }
}
