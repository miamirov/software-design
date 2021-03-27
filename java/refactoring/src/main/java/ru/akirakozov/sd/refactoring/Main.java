package ru.akirakozov.sd.refactoring;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;
import ru.akirakozov.sd.refactoring.servlet.QueryServlet;

/**
 * @author akirakozov
 */
public class Main {
    public static void main(String[] args) throws Exception {

        DBManager dbManager = new DBManager("jdbc:sqlite:test.db");

        dbManager.createDataBase();

        Server server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new AddProductServlet(new DBManager("jdbc:sqlite:test.db"))), "/add-product");
        context.addServlet(new ServletHolder(new GetProductsServlet(new DBManager("jdbc:sqlite:test.db"))),"/get-products");
        context.addServlet(new ServletHolder(new QueryServlet(new DBManager("jdbc:sqlite:test.db"))),"/query");

        server.start();
        server.join();
    }
}
