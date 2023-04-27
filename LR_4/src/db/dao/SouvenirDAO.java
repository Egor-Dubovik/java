package db.dao;

import db.*;
import souvenir.Souvenir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SouvenirDAO {
    private Connection connection;
    private PreparedStatement getSouvenirsByManufacturerStmt;
    private PreparedStatement getSouvenirsByCountryStmt;
    private PreparedStatement addSouvenirStmt;

    public SouvenirDAO() throws SQLException, ClassNotFoundException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        connection = dbConnection.getConnection();
        getSouvenirsByManufacturerStmt = connection.prepareStatement(
                "SELECT s.id, s.name, s.release_date, s.price " +
                        "FROM souvenirs s " +
                        "JOIN manufacturers m ON s.manufacturer_id = m.id " +
                        "WHERE m.name = ?");
        getSouvenirsByCountryStmt = connection.prepareStatement(
                "SELECT s.id, s.name, m.name AS manufacturer, s.release_date, s.price " +
                        "FROM souvenirs s " +
                        "JOIN manufacturers m ON s.manufacturer_id = m.id " +
                        "WHERE m.country = ?");
        addSouvenirStmt = connection.prepareStatement(
                "INSERT INTO souvenirs (name, manufacturer_id, release_date, price) " +
                        "VALUES (?, ?, ?, ?)");
    }

    public List<Souvenir> getSouvenirsByManufacturer(String manufacturer) throws SQLException {
        getSouvenirsByManufacturerStmt.setString(1, manufacturer);
        ResultSet rs = getSouvenirsByManufacturerStmt.executeQuery();
        List<Souvenir> souvenirs = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String releaseDate = rs.getString("release_date");
            double price = rs.getDouble("price");
            souvenirs.add(new Souvenir(id, name, manufacturer, releaseDate, price));
        }
        return souvenirs;
    }

    public List<Souvenir> getSouvenirsByCountry(String country) throws SQLException {
        getSouvenirsByCountryStmt.setString(1, country);
        ResultSet rs = getSouvenirsByCountryStmt.executeQuery();
        List<Souvenir> souvenirs = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String manufacturer = rs.getString("manufacturer");
            String releaseDate = rs.getString("release_date");
            double price = rs.getDouble("price");
            souvenirs.add(new Souvenir(id, name, manufacturer, releaseDate, price));
        }
        return souvenirs;
    }

    public void addSouvenir(String name, int manufacturerId, String releaseDate, double price) throws SQLException {
        addSouvenirStmt.setString(1, name);
        addSouvenirStmt.setInt(2, manufacturerId);
        addSouvenirStmt.setString(3, releaseDate);
        addSouvenirStmt.setDouble(4, price);
        addSouvenirStmt.executeUpdate();
    }

    public void close() throws SQLException {
        getSouvenirsByManufacturerStmt.close();
        getSouvenirsByCountryStmt.close();
        addSouvenirStmt.close();
        connection.close();
    }
}
