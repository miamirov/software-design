package ru.akirakozov.sd.refactoring.servlet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.DBManager;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class addProductsServletTest extends AbstractServletTest {
    protected addProductsServletTest() {
        super(new AddProductServlet(new DBManager("jdbc:sqlite:test.db"
        )));
    }

    @Test
    protected void testAdd() throws ServletException, IOException, SQLException {
        dbInsertByRequest("1", 0);

        servletService();

        assertEquals("OK\n", getResponse());
        assertEquals(getExpected(), dbSelectAll());
    }

}