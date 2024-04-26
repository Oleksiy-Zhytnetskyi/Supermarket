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

public class GetCreateCustomerCardCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("create", true);
        return Page.VIEW_CUSTOMER_CARD;
    }
}
