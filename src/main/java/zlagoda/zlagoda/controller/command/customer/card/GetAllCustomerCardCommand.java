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
import java.util.List;

public class GetAllCustomerCardCommand implements Command {

    private final CardService cardService;

    public GetAllCustomerCardCommand(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CardEntity> cards = cardService.getAllCards();

        req.setAttribute(Attribute.CARDS, cards);
        return Page.ALL_CUSTOMER_CARDS;
    }
}
