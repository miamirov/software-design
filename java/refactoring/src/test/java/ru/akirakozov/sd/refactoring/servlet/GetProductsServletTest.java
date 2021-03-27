package ru.akirakozov.sd.refactoring.servlet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetProductsServletTest extends AbstractServletTest {
    protected GetProductsServletTest() {
        super(new GetProductsServlet());
    }

    @Test
    protected void testEmptyDB() throws ServletException, IOException {
        servletService();

        assertEquals(getExpected(), getResponse());
    }

    @Test
    protected void testNonEmptyDB() throws ServletException, IOException, SQLException {
        dbInsert("1", 1);
        dbInsert("2", 2);
        dbInsert("3", 3);

        servletService();

        assertEquals(getExpected(), getResponse());
    }
}
