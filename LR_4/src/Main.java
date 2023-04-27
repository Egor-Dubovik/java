import db.dao.*;
import manufacturer.ManufacturerService;
import souvenir.SouvenirService;
import souvenir.Souvenir;
import manufacturer.Manufacturer;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, we are started work with souvenirs!");

        // create base 4 Manufacturers
//        ManufacturerService manufacturerService = new ManufacturerService();
//        manufacturerService.createBaseManufacturers();

        // work with services
        SouvenirService souvenirService = new SouvenirService();

        // add some services (check  manufacturerId in DB)
//        souvenirService.addSouvenir("Souvenir 1", 12, "2023", 10.50);
//        souvenirService.addSouvenir("car BMW", 13, "2018", 9.2);
//        souvenirService.addSouvenir("gift 5", 14, "2023", 3.8);
//        souvenirService.addSouvenir("fridge magnet", 15, "2020", 11.0);
//        souvenirService.addSouvenir("Souvenir 3", 13, "2022", 10.50);
//        souvenirService.close();

        // search and print souvenirs by manufacturer
//        souvenirService.printSouvenirsByManufacturer("Souvenir Co.");
//        souvenirService.close();

        // search and print souvenirs by country
//        souvenirService.printSouvenirsByCountry("France");
//        souvenirService.close();

//        ---------------------------------------------------------------------------------------

        // work with manufacturers
//        ManufacturerService manufacturerService = new ManufacturerService();
//
//         search manufacturer by souvenir max price (less than max price)
//         manufacturerService.printByPrice(10.00);
//
//         search manufacturer by souvenir and Year
//         manufacturerService.printBySouvenirAndYear("car BMW", "2018");
//
//         delete manufacture and them souvenirs (can check incorrect name)
//         manufacturerService.handleDeletionManufacturerAndSouvenirs("Surprise PL");
    }
}




