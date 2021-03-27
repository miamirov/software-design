package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.DBManager;
import ru.akirakozov.sd.refactoring.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author akirakozov
 */
public class QueryServlet extends Servlet {
    public QueryServlet(DBManager dbManager) {
        super(dbManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            try {
                    List<Product> products = dbManager.selectProducts("max");
                    response.getWriter().println("<html><body>");
                    response.getWriter().println("<h1>Product with max price: </h1>");
                    GetProductsServlet.writeResponse(response, products);


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("min".equals(command)) {
            try {
                List<Product> products = dbManager.selectProducts("min");
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>Product with min price: </h1>");
                writeResponse(response, products);


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("sum".equals(command)) {
            try {
                Long result = dbManager.agregateProducts("sum");
                response.getWriter().println("<html><body>");
                response.getWriter().println("Summary price: ");
                if (result != null) {
                    response.getWriter().println(result);
                }

                response.getWriter().println("</body></html>");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("count".equals(command)) {
            try {
                Long result = dbManager.agregateProducts("count");
                response.getWriter().println("<html><body>");
                response.getWriter().println("Number of products: ");
                if (result != null) {
                    response.getWriter().println(result);
                }

                response.getWriter().println("</body></html>");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
