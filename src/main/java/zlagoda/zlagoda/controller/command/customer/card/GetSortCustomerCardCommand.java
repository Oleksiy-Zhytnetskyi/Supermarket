package zlagoda.zlagoda.controller.command.customer.card;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.CardEntity;
import zlagoda.zlagoda.service.CardService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetSortCustomerCardCommand implements Command {

    private static final CardService cardService = CardService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CardEntity> cards = new ArrayList<>();

        String surname = req.getParameter(Attribute.CARDS_SURNAME);
        Integer percent = Integer.valueOf(req.getParameter(Attribute.CARDS_PERCENT));

        cardService.getAllCards().stream()
                        .forEach(
                                cardEntity -> {
                                    if(cardEntity.getCustomerSurname().toLowerCase().contains(surname.toLowerCase())) {
                                        if (percent != 0) {
                                            if (cardEntity.getPercent() == percent) {
                                                cards.add(cardEntity);
                                            }
                                        } else {
                                            cards.add(cardEntity);
                                        }
                                    }
                                }
                        );

        req.setAttribute(Attribute.CARDS, cards);
        req.setAttribute(Attribute.CARDS_SURNAME, surname);
        req.setAttribute(Attribute.CARDS_PERCENT, percent);
        return Page.ALL_CUSTOMER_CARDS;
    }
}
