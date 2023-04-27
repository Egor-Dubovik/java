package manufacturer;

import db.dao.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerService {
    private final Manufacturer[] baseManufacturers = new Manufacturer[]{
            new Manufacturer("ООО Сувениры из Беларуси", "Belarus"),
            new Manufacturer("Souvenir Co.", "France"),
            new Manufacturer("Surprise PL", "Poland"),
            new Manufacturer("Gifts Ltd.", "UK")
    };

    private void printManufacturerList(List<Manufacturer> manufacturers) {
        System.out.println("Manufacturers satisfying the request:");
        for (Manufacturer m : manufacturers) {
            System.out.println("ID: " + m.getId() + ", Name: " + m.getName() + ", Country: " + m.getCountry());
        }
    }


    public void createBaseManufacturers() {
        try {
            for (Manufacturer manufacturer : baseManufacturers) {
                ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
                manufacturerDAO.createManufacturer(manufacturer.getName(), manufacturer.getCountry());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printBySouvenirAndYear(String souvenirName, String releaseYear) {
        try {
            ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
            List<Manufacturer> manufacturers = manufacturerDAO.getManufacturersBySouvenirAndYear(souvenirName, releaseYear);
            if (manufacturers.isEmpty()) {
                System.out.println("Manufacturers not found");
            } else {
                this.printManufacturerList(manufacturers);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printByPrice(double maxPrice) {
        try {
            ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
            List<Manufacturer> manufacturers = manufacturerDAO.getManufacturersByPrice(maxPrice);
            if (manufacturers.isEmpty()) {
                System.out.println("Manufacturers not found");
            } else {
                this.printManufacturerList(manufacturers);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void handleDeletionManufacturerAndSouvenirs(String manufacturerName) {
        try {
            ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
            manufacturerDAO.deleteManufacturerAndSouvenirs(manufacturerName);
            System.out.println("Manufacturer and souvenirs deleted successfully");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error deleting manufacturer and souvenirs: " + e.getMessage());
        }
    }

}
