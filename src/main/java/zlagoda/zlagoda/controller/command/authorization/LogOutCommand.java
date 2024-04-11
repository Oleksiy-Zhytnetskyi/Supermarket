package zlagoda.zlagoda.controller.command.authorization;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.ServletPath;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.controller.utils.HttpWrapper;
import zlagoda.zlagoda.controller.utils.RedirectionManager;
import zlagoda.zlagoda.controller.utils.SessionManager;

import java.io.IOException;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionManager.getInstance().invalidateSession(req.getSession());
        RedirectionManager.getInstance().redirect(new HttpWrapper(req, resp), ServletPath.HOME);
        return RedirectionManager.REDIRECTION;
    }
}
