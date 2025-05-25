public class Location {
    private String country;
    private String city;
    private String airportName;

    public Location() {}

    public Location(String country, String city, String airportName) {
        this.country = country;
        this.city = city;
        this.airportName = airportName;
    }

    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getAirportName() { return airportName; }

    public String toString() {
        return airportName + " (" + city + ", " + country + ")";
    }
}
