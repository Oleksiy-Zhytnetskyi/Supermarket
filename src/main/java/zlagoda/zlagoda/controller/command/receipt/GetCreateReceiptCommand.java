package zlagoda.zlagoda.controller.command.receipt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.CardEntity;
import zlagoda.zlagoda.service.CardService;
import zlagoda.zlagoda.service.ReceiptService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class GetCreateReceiptCommand implements Command {

    private final ReceiptService receiptService;
    private final CardService cardService;

    public GetCreateReceiptCommand(ReceiptService receiptService, CardService cardService) {
        this.receiptService = receiptService;
        this.cardService = cardService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        List<CardEntity> cards = cardService.getAllCards();
        req.setAttribute(Attribute.CARDS, cards);
        return Page.ADD_RECEIPT;
    }
}
