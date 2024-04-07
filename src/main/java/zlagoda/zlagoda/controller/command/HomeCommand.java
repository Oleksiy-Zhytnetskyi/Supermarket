package zlagoda.zlagoda.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Page;

import java.io.IOException;

public class HomeCommand  implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return Page.HOME_VIEW;
    }
}
