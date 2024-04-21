package zlagoda.zlagoda.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;

public interface Command {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException;
}
