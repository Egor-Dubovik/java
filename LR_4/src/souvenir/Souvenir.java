package souvenir;

public class Souvenir {
    private int id;
    private String name;
    private String manufacturerDetails;
    private String releaseDate;
    private double price;

    public Souvenir(int id, String name, String manufacturerDetails, String releaseDate, double price) {
        this.id = id;
        this.name = name;
        this.manufacturerDetails = manufacturerDetails;
        this.releaseDate = releaseDate;
        this.price = price;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getManufacturerDetails() {
        return manufacturerDetails;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public double getPrice() {
        return price;
    }
}
