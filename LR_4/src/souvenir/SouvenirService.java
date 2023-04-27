package souvenir;
import db.dao.SouvenirDAO;
import manufacturer.Manufacturer;

import java.sql.SQLException;
import java.util.List;

public class SouvenirService {
    private SouvenirDAO souvenirDAO;

    public SouvenirService() {
        try {
            souvenirDAO = new SouvenirDAO();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printServiceList(List<Souvenir> souvenirs) {
        System.out.println("Souvenirs satisfying the request:");
        for (Souvenir s : souvenirs) {
            System.out.println("ID: " + s.getId() + ", Name: " + s.getName() + ", ReleaseDate: " + s.getReleaseDate() + ", Price: " + s.getPrice());
        }
    }

    public void addSouvenir(String name, int manufacturerId, String releaseDate, double price) {
        try {
            souvenirDAO.addSouvenir(name, manufacturerId, releaseDate, price);
            System.out.println("Souvenir " + name +  " added successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to add souvenir: " + name);
            e.printStackTrace();
        }
    }

    public void printSouvenirsByManufacturer(String manufacturer) {
        try {
            List<Souvenir> souvenirs = souvenirDAO.getSouvenirsByManufacturer(manufacturer);
            this.printServiceList(souvenirs);
        } catch (SQLException e) {
            System.err.println("Failed to get souvenirs");
            e.printStackTrace();
        }
    }

    public void printSouvenirsByCountry(String country) {
        try {
            List<Souvenir> souvenirs = souvenirDAO.getSouvenirsByCountry(country);
            this.printServiceList(souvenirs);
        } catch (SQLException e) {
            System.err.println("Failed to get souvenirs");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            souvenirDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
