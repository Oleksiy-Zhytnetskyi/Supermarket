package zlagoda.zlagoda.controller.command.sale;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.entity.keys.SaleEntityComplexKey;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.ReceiptService;
import zlagoda.zlagoda.service.SaleService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteSaleCommand implements Command {

    private final SaleService saleService;

    public DeleteSaleCommand(SaleService saleService) {
        this.saleService = saleService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SaleEntityComplexKey key = new SaleEntityComplexKey(Integer.valueOf(req.getParameter(Attribute.STORE_PRODUCT_ID)), Integer.valueOf(req.getParameter(Attribute.RECEIPT)));
        saleService.deleteSale(key);

        redirectToAllUsersPageWithSuccessMessage(req, resp);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllUsersPageWithSuccessMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(req, resp);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_SALE_DELETE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_SALES, urlParams);
    }
}
