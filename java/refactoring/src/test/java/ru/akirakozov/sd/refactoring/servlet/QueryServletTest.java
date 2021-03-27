package ru.akirakozov.sd.refactoring.servlet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryServletTest extends AbstractServletTest {
    QueryServletTest() {
        super(new QueryServlet());
    }

    @Test
    protected void testMax() throws ServletException, IOException, SQLException {
        dbInsert("1", 1);
        dbInsert("2", 2);
        dbInsert("3", 3);

        setRequestCommand("max");

        servletService();

        assertEquals("<html><body>\n" +
                "<h1>Product with max price: </h1>\n" +
                "3\t3</br>\n" +
                "</body></html>\n", getResponse());
    }

    @Test
    protected void testMin() throws ServletException, IOException, SQLException {
        dbInsert("1", 1);
        dbInsert("2", 2);
        dbInsert("3", 3);

        setRequestCommand("min");

        servletService();

        assertEquals("<html><body>\n" +
                "<h1>Product with min price: </h1>\n" +
                "1\t1</br>\n" +
                "</body></html>\n", getResponse());
    }

    @Test
    protected void testSum() throws ServletException, IOException, SQLException {
        dbInsert("1", 1);
        dbInsert("2", 2);
        dbInsert("3", 3);

        setRequestCommand("sum");

        servletService();

        assertEquals("<html><body>\n" +
                "Summary price: \n" +
                "6\n" +
                "</body></html>\n", getResponse());
    }

    @Test
    protected void testCount() throws ServletException, IOException, SQLException {
        dbInsert("1", 1);
        dbInsert("2", 2);
        dbInsert("3", 3);

        setRequestCommand("count");

        servletService();

        assertEquals("<html><body>\n" +
                "Number of products: \n" +
                "3\n" +
                "</body></html>\n", getResponse());
    }

}