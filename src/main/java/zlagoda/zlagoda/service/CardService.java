package zlagoda.zlagoda.service;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import zlagoda.zlagoda.entity.CardEntity;
import zlagoda.zlagoda.repository.BaseRepositoryFactory;
import zlagoda.zlagoda.repository.CardRepository;
import zlagoda.zlagoda.view.CardView;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CardService {

    private static final Logger LOGGER = LogManager.getLogger(CardService.class);

    private static final String GET_ALL_CARDS = "Get all cards";
    private static final String GET_CARD_BY_ID = "Get card by id: %s";
    private static final String CREATE_CARD = "Create card: %s";
    private static final String UPDATE_CARD = "Update card: %s";
    private static final String DELETE_CARD = "Delete card: %s";
    private static final String SEARCH_CARDS_BY_PERCENT = "Search cards by percent: %d";
    private static final String SEARCH_CARDS_BY_CUSTOMER_SURNAME = "Search cards by customer surname: %s";

    private final BaseRepositoryFactory repositoryFactory;

    private static class Holder {
        static final CardService INSTANCE = new CardService(BaseRepositoryFactory.getRepositoryFactory());
    }

    public static CardService getInstance() { return Holder.INSTANCE; }

    public List<CardEntity> getAllCards() {
        LOGGER.info(GET_ALL_CARDS);
        try (CardRepository repository = repositoryFactory.createCardRepository()) {
            return repository.getAll();
        }
    }

    public Optional<CardEntity> getCardById(String id) {
        LOGGER.info(String.format(GET_CARD_BY_ID, id));
        try (CardRepository repository = repositoryFactory.createCardRepository()) {
            return repository.getById(id);
        }
    }

    public void createCard(CardView cardView) {
        LOGGER.info(String.format(CREATE_CARD, cardView.getPhoneNumber()));
        CardEntity card = buildCardFromView(cardView);
        try (CardRepository repository = repositoryFactory.createCardRepository()) {
            repository.create(card);
        }
    }

    public void updateCard(CardView cardView) {
        LOGGER.info(String.format(UPDATE_CARD, cardView.getId()));
        CardEntity card = buildCardFromView(cardView);
        try (CardRepository repository = repositoryFactory.createCardRepository()) {
            repository.update(card);
        }
    }

    public void deleteCard(String id) {
        LOGGER.info(String.format(DELETE_CARD, id));
        try (CardRepository repository = repositoryFactory.createCardRepository()) {
            repository.delete(id);
        }
    }

    public List<CardEntity> searchCardsByPercent(int percent) {
        LOGGER.info(String.format(SEARCH_CARDS_BY_PERCENT, percent));
        try (CardRepository repository = repositoryFactory.createCardRepository()) {
            return repository.searchCardsByPercent(percent);
        }
    }

    public List<CardEntity> searchCardsByCustomerSurname(String surname) {
        LOGGER.info(String.format(SEARCH_CARDS_BY_CUSTOMER_SURNAME, surname));
        try (CardRepository repository = repositoryFactory.createCardRepository()) {
            return repository.searchCardsByCustomerSurname(surname);
        }
    }

    private static CardEntity buildCardFromView(CardView view) {
        return CardEntity.builder()
                .id(view.getId())
                .customerSurname(view.getCustomerSurname())
                .customerName(view.getCustomerName())
                .customerPatronymic(view.getCustomerPatronymic())
                .phoneNumber(view.getPhoneNumber())
                .city(view.getCity())
                .street(view.getStreet())
                .zipCode(view.getZipCode())
                .percent(view.getPercent())
                .build();
    }
}
