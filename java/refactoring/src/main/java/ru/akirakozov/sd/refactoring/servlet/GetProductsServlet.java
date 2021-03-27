package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.DBManager;
import ru.akirakozov.sd.refactoring.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends Servlet {

    public GetProductsServlet(DBManager dbManager) {
        super(dbManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {

            List<Product> products = dbManager.selectProducts("get");
            response.getWriter().println("<html><body>");
            writeResponse(response, products);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
