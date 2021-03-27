package ru.akirakozov.sd.refactoring;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private final String database;

    public DBManager(String name) {
        database = name;
    }

    public void createDataBase() throws SQLException {
        try (Connection c = DriverManager.getConnection(database)) {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(SQL.createTable());
            stmt.close();
        }
    }



    public void insertProduct(String name, long price) throws SQLException {
        try (Connection c = DriverManager.getConnection(database)) {
            String sql = SQL.insertIntoProduct(name, price);
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }
    }

    public List<Product> selectProducts(String query) throws SQLException {
        try (Connection c = DriverManager.getConnection(database)) {
            Statement stmt = c.createStatement();
            ResultSet rs;
            switch (query) {
                case ("get"):
                    rs = stmt.executeQuery(SQL.selectAllFromProduct());
                    break;
                case ("min"):
                    rs = stmt.executeQuery(SQL.minPrice());
                    break;
                case ("max"):
                    rs = stmt.executeQuery(SQL.maxPrice());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + query);

            }

            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                long price = rs.getLong("price");
                result.add(new Product(name, price));
            }

            rs.close();
            stmt.close();

            return result;
        }
    }

    public Long agregateProducts(String query) throws SQLException {
        try (Connection c = DriverManager.getConnection(database)) {
            Statement stmt = c.createStatement();
            ResultSet rs;
            switch (query) {
                case ("sum"):
                    rs = stmt.executeQuery(SQL.sumPrice());
                    break;
                case ("count"):
                    rs = stmt.executeQuery(SQL.countProduct());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + query);
            }

            Long result = null;
            if (rs.next()) {
                result = rs.getLong(1);
            }
            rs.close();
            stmt.close();
            return result;
        }
    }

}