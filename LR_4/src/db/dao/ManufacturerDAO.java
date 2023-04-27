package db.dao;

import db.DatabaseConnection;
import manufacturer.Manufacturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ManufacturerDAO {
    private Connection connection;

    public ManufacturerDAO() throws SQLException, ClassNotFoundException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        connection = dbConnection.getConnection();
    }

    public List<Manufacturer> getManufacturersByPrice(double maxPrice) throws SQLException {
        String sql = "SELECT * FROM manufacturers WHERE id IN (SELECT manufacturer_id FROM souvenirs WHERE price < ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDouble(1, maxPrice);
        ResultSet rs = stmt.executeQuery();

        List<Manufacturer> manufacturers = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String country = rs.getString("country");
            manufacturers.add(new Manufacturer(id, name, country));
        }

        rs.close();
        stmt.close();
        connection.close();
        return manufacturers;
    }

    public List<Manufacturer> getManufacturersBySouvenirAndYear(String souvenirName, String releaseYear) throws SQLException {
        String sql = "SELECT m.* FROM manufacturers m JOIN souvenirs s ON m.id = s.manufacturer_id WHERE s.name = ? AND s.release_date LIKE ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, souvenirName);
        stmt.setString(2, releaseYear + "%");
        ResultSet rs = stmt.executeQuery();

        List<Manufacturer> manufacturers = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String country = rs.getString("country");
            manufacturers.add(new Manufacturer(id, name, country));
        }

        rs.close();
        stmt.close();
        connection.close();
        return manufacturers;
    }


    public void createManufacturer(String name, String country) throws SQLException {
        String sql = "INSERT INTO manufacturers (name, country) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, country);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected == 0) {
            System.out.println("Ошибка: производитель " + name + " не был создан!");
        } else {
            System.out.println("Производитель: " + name +  " создан успешно!");
        }
        stmt.close();
        connection.close();
    }


    public void deleteManufacturerAndSouvenirs(String manufacturerName) throws SQLException {
        String selectSql = "SELECT id FROM manufacturers WHERE name = ?";
        String sql1 = "DELETE FROM souvenirs WHERE manufacturer_id IN (SELECT id FROM manufacturers WHERE name = ?)";
        String sql2 = "DELETE FROM manufacturers WHERE name = ?";
        connection.setAutoCommit(false);

        // Check if the manufacturer exists in the database
        PreparedStatement selectStmt = connection.prepareStatement(selectSql);
        selectStmt.setString(1, manufacturerName);
        ResultSet rs = selectStmt.executeQuery();

        if (!rs.next()) {
            throw new IllegalArgumentException("Manufacturer not found in the database");
        }

        // Delete the souvenirs for the manufacturer
        PreparedStatement stmt1 = connection.prepareStatement(sql1);
        stmt1.setString(1, manufacturerName);
        stmt1.executeUpdate();

        // Delete the manufacturer
        PreparedStatement stmt2 = connection.prepareStatement(sql2);
        stmt2.setString(1, manufacturerName);
        stmt2.executeUpdate();

        connection.commit();

        stmt1.close();
        stmt2.close();
        connection.close();
    }
}
