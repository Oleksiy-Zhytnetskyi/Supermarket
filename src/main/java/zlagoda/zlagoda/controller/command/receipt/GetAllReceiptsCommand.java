package zlagoda.zlagoda.controller.command.receipt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.CardEntity;
import zlagoda.zlagoda.entity.ReceiptEntity;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.service.CardService;
import zlagoda.zlagoda.service.ReceiptService;
import zlagoda.zlagoda.service.UserService;

import java.io.IOException;
import java.util.List;

public class GetAllReceiptsCommand implements Command {

    private final ReceiptService receiptService;
    private final UserService userService;
    private final CardService cardService;

    public GetAllReceiptsCommand(ReceiptService receiptService, UserService userService, CardService cardService) {
        this.receiptService = receiptService;
        this.userService = userService;
        this.cardService = cardService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ReceiptEntity> receipts = receiptService.getAllReceipts();
        List<UserEntity> users = userService.getAllUsers();
        List<CardEntity> cards = cardService.getAllCards();
        req.setAttribute(Attribute.RECEIPTS, receipts);
        req.setAttribute(Attribute.USERS, users);
        req.setAttribute(Attribute.CARDS, cards);
        return Page.ALL_RECEIPTS;
    }
}
