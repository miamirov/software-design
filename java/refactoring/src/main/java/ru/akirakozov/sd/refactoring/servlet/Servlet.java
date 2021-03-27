package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.DBManager;
import ru.akirakozov.sd.refactoring.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

class Servlet extends HttpServlet {

    final DBManager dbManager;

    Servlet(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    static void writeResponse(HttpServletResponse response, List<Product> products) throws IOException {
        for (Product item : products) {
            response.getWriter().println(item.name + "\t" + item.price + "</br>");
        }
        response.getWriter().println("</body></html>");
    }
}
