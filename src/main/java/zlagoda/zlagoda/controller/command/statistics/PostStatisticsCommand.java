package zlagoda.zlagoda.controller.command.statistics;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;

import java.io.IOException;
import java.text.ParseException;

public class PostStatisticsCommand implements Command {

    // ...

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        return Page.CALCULATE_STATISTICS;
    }
}
