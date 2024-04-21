package zlagoda.zlagoda.controller;
import zlagoda.zlagoda.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.command.i18n.AppLocale;
import zlagoda.zlagoda.controller.utils.CommandKeyGenerator;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontController", urlPatterns = "/controller/*")
public class FrontController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(FrontController.class);
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
        getServletContext().setAttribute(Attribute.LOCALES, AppLocale.getAppLocales());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        String commandKey = CommandKeyGenerator.generateCommandKeyFromRequest(request);
        Command command = CommandFactory.getCommand(commandKey);
        try {
            String commandResultedResource = command.execute(request, response);
            forwardToCommandResultedPage(httpWrapper, commandResultedResource);
        } catch (ServiceException ex) {
            LOGGER.error("Error has occured while command execution with key: " + commandKey);
            redirectToHomePageWithErrorMessage(httpWrapper, ex);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void forwardToCommandResultedPage(HttpWrapper httpWrapper, String resultedRedirectResource)
            throws ServletException, IOException {
        if (!resultedRedirectResource.contains(RedirectionManager.REDIRECTION)) {
            httpWrapper.getRequest().getRequestDispatcher(resultedRedirectResource).forward(httpWrapper.getRequest(),
                    httpWrapper.getResponse());
        }
    }

    private void redirectToHomePageWithErrorMessage(HttpWrapper httpWrapper, ServiceException ex) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.ERROR, ex.getMessage());
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.HOME, urlParams);
    }
}
