package zlagoda.zlagoda.controller.command.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.locale.Message;
import zlagoda.zlagoda.service.ProductService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteProductCommand implements Command {

    private static final ProductService productService = ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer productId = Integer.parseInt(req.getParameter(Attribute.ID));

        productService.deleteProduct(productId);

        redirectToAllUsersPageWithSuccessMessage(req, resp);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllUsersPageWithSuccessMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(req, resp);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_PRODUCT_DELETE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_PRODUCTS, urlParams);
    }
}
