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

public class GetViewCustomerCardCommand implements Command {

    private final CardService cardService = CardService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CardEntity cardEntity = cardService.getCardById(Integer.parseInt(req.getParameter(Attribute.ID))).get();
        req.setAttribute(Attribute.CARD_VIEW, cardEntity);
        req.setAttribute("view", true);
        return Page.VIEW_CUSTOMER_CARD;
    }
}
