package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Servlet extends HttpServlet {
    static void writeResponse(HttpServletResponse response, Statement stmt, ResultSet rs) throws SQLException, IOException {
        while (rs.next()) {
            String name = rs.getString("name");
            int price = rs.getInt("price");
            response.getWriter().println(name + "\t" + price + "</br>");
        }
        response.getWriter().println("</body></html>");

        rs.close();
        stmt.close();
    }
}