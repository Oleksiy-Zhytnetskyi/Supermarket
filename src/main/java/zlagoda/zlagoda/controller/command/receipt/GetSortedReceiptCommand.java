package zlagoda.zlagoda.controller.command.receipt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.entity.ReceiptEntity;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.service.ReceiptService;
import zlagoda.zlagoda.service.UserService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class GetSortedReceiptCommand implements Command {

    private static final ReceiptService receiptService = ReceiptService.getInstance();
    private static final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ReceiptEntity> receipts = receiptService.getSortedReceipts(LocalDate.now().minusDays(30), LocalDate.now());
        List<UserEntity> users = userService.getAllUsers();
        req.setAttribute(Attribute.RECEIPTS, receipts);
        req.setAttribute(Attribute.USERS, users);
        return Page.SORTED_RECEIPTS;
    }
}
