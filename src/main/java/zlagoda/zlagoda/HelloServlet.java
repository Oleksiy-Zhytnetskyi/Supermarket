package zlagoda.zlagoda;

import java.io.*;
import java.util.Map;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import zlagoda.zlagoda.repositories.jdbc.DatabaseTest;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private DatabaseTest databaseTest;
    public void init() {
        databaseTest = new DatabaseTest();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        Map<String, String> map = databaseTest.getConnect();
        for(String name: map.keySet()) {
            out.println("<h1>" + "FirstName --> " + name + "   LastName --> " + map.get(name) + "</h1>");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}