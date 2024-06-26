package zlagoda.zlagoda.controller.command.customer.card;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.CardService;
import zlagoda.zlagoda.validator.entity.CardViewValidator;
import zlagoda.zlagoda.view.CardView;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostCreateCustomerCardCommand implements Command {

    private static final CardService cardService = CardService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        CardView cardView = getUserInput(req);
        List<String> errors = validateUserInput(cardView);

        if (errors.isEmpty()) {
            cardService.createCard(cardView);
            redirectToAllCategoryPageWithSuccessMessage(req, resp);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(req, cardView, errors);
        return Page.VIEW_CUSTOMER_CARD;
    }

    private CardView getUserInput(HttpServletRequest req) throws ParseException {
        CardView.CardViewBuilder result = CardView.builder()
                .customerName(req.getParameter(Attribute.NAME))
                .customerSurname(req.getParameter(Attribute.SURNAME))
                .customerPatronymic(req.getParameter(Attribute.PATRONYMIC))
                .phoneNumber(req.getParameter(Attribute.PHONE))
                .city(req.getParameter(Attribute.CITY))
                .street(req.getParameter(Attribute.STREET))
                .zipCode(req.getParameter(Attribute.ZIP_CODE));
        try {
            result.percent(Integer.valueOf(req.getParameter(Attribute.PERCENT)));
        }
        catch (NumberFormatException e) {
            result.percent(0);
        }
        return result.build();
    }

    private List<String> validateUserInput(CardView cardView) {
        return CardViewValidator.getInstance().validate(cardView);
    }

    private void redirectToAllCategoryPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_CARD_ADDITION);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CARDS , urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, CardView cardView, List<String> errors) {
        request.setAttribute(Attribute.CARD_VIEW, cardView);
        request.setAttribute(Attribute.ERRORS, errors);
        request.setAttribute("create", true);
    }
}
