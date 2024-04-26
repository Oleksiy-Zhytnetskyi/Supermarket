package zlagoda.zlagoda.controller.command.receipt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.entity.CardEntity;
import zlagoda.zlagoda.entity.ReceiptEntity;
import zlagoda.zlagoda.entity.UserEntity;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.CardService;
import zlagoda.zlagoda.service.ReceiptService;
import zlagoda.zlagoda.view.ReceiptView;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostCreateReceiptCommand implements Command {

    private final ReceiptService receiptService;
    private final CardService cardService;

    public PostCreateReceiptCommand(ReceiptService receiptService, CardService cardService) {
        this.receiptService = receiptService;
        this.cardService = cardService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        ReceiptView receiptView = getUserInput(req);
        List<String> errors = validateUserInput(receiptView);

        if (errors.isEmpty()) {
            receiptService.createReceipt(receiptView);
            redirectToAllProductsPageWithSuccessMessage(req, resp);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(req, receiptView, errors);
        return Page.VIEW_PRODUCT;
    }

    private ReceiptView getUserInput(HttpServletRequest req) throws ParseException {
        UserEntity user = (UserEntity)req.getSession().getAttribute(Attribute.USER);
        ReceiptView.ReceiptViewBuilder builder = ReceiptView.builder()
               .printDate(LocalDate.now())
               .sumTotal(0.0)
               .vat(0.0)
               .userId(user.getId());
        if(!req.getParameter(Attribute.CARD).equals("null"))
            builder
            .cardId(Integer.valueOf(req.getParameter(Attribute.CARD)));
        return builder.build();
    }

    private List<String> validateUserInput(ReceiptView receiptView) {
//        To do: validator
        return new ArrayList<>();
    }

    private void redirectToAllProductsPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ReceiptEntity> receipts = receiptService.getAllReceipts();
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_RECEIPT_ADDITION + receipts.get(receipts.size() - 1).getId());
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_RECEIPTS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, ReceiptView receiptView, List<String> errors) {
        List<CardEntity> cards = cardService.getAllCards();
        request.setAttribute(Attribute.RECEIPT_VIEW, receiptView);
        request.setAttribute(Attribute.CARDS, cards);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
