import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ConsoleApp {

    private static final String PLANES_FILE = "data/planes.json";
    private static final String LOCATIONS_FILE = "data/locations.json";
    private static final String FLIGHTS_FILE = "data/flights.json";
    private static final String RESERVATIONS_FILE = "data/reservations.json";

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        List<Plane> planes = JsonStorage.loadList(PLANES_FILE, Plane.class);
        List<Location> locations = JsonStorage.loadList(LOCATIONS_FILE, Location.class);
        List<Flight> flights = JsonStorage.loadList(FLIGHTS_FILE, Flight.class);
        List<Reservation> reservations = JsonStorage.loadList(RESERVATIONS_FILE, Reservation.class);

        while (true) {
            System.out.println("\n==== Flight Reservation Console ====");
            System.out.println("1. Make a new reservation");
            System.out.println("2. Add a new plane");
            System.out.println("3. Add a new location");
            System.out.println("4. Add a new flight");
            System.out.println("5. List planes");
            System.out.println("6. List locations");
            System.out.println("7. List flights");
            System.out.println("8. List reservations");
            System.out.println("9. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> makeReservation(flights, reservations);
                case "2" -> addPlane(planes);
                case "3" -> addLocation(locations);
                case "4" -> addFlight(planes, locations, flights);
                case "5" -> planes.forEach(System.out::println);
                case "6" -> locations.forEach(System.out::println);
                case "7" -> flights.forEach(System.out::println);
                case "8" -> reservations.forEach(System.out::println);
                case "9" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }

            JsonStorage.saveList(PLANES_FILE, planes, Plane.class);
            JsonStorage.saveList(LOCATIONS_FILE, locations, Location.class);
            JsonStorage.saveList(FLIGHTS_FILE, flights, Flight.class);
            JsonStorage.saveList(RESERVATIONS_FILE, reservations, Reservation.class);
        }
    }

    private static void makeReservation(List<Flight> flights, List<Reservation> reservations) {
        if (flights.isEmpty()) {
            System.out.println("!!! No available flights.");
            return;
        }

        System.out.println("\n *** Available Flights:");
        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            int seatsLeft = f.getAirplane().getCapacity() - f.getTotalPassengers();
            System.out.printf("[%d] %s → %s | %s %s | Seats left: %d\n",
                    i + 1,
                    f.getOrigin().getCity(), f.getDestination().getCity(),
                    f.getDate(), f.getTime(), seatsLeft);
        }

        System.out.print("Choose flight number: ");
        int flightChoice = Integer.parseInt(scanner.nextLine());
        if (flightChoice < 1 || flightChoice > flights.size()) {
            System.out.println("!!! Invalid selection.");
            return;
        }

        Flight selected = flights.get(flightChoice - 1);
        if (selected.getTotalPassengers() >= selected.getAirplane().getCapacity()) {
            System.out.println("!!! Flight full.");
            return;
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Gender (Male/Female): ");
        String gender = scanner.nextLine();

        Reservation res = new Reservation(selected, name, surname, age, gender, LocalDate.now());
        selected.addReservation(res);
        reservations.add(res);
        System.out.println("[**OK**] Reservation confirmed:\n" + res);
    }

    private static void addPlane(List<Plane> planes) {
        System.out.println("\n*** Add New Plane:");
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Serial Number: ");
        String serialNo = scanner.nextLine();
        System.out.print("Capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());
        System.out.print("Year Built: ");
        int yearBuilt = Integer.parseInt(scanner.nextLine());

        planes.add(new Plane(model, brand, serialNo, capacity, yearBuilt));
        System.out.println("[**OK**] Plane added.");
    }

    private static void addLocation(List<Location> locations) {
        System.out.println("\n*** Add New Location:");
        System.out.print("Country: ");
        String country = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("Airport Name: ");
        String airport = scanner.nextLine();

        locations.add(new Location(country, city, airport));
        System.out.println("[**OK**] Location added.");
    }

    private static void addFlight(List<Plane> planes, List<Location> locations, List<Flight> flights) {
        if (planes.isEmpty() || locations.size() < 2) {
            System.out.println("!!! Need at least 1 plane and 2 locations.");
            return;
        }

        System.out.println("\n Select Origin:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.printf("[%d] %s\n", i + 1, locations.get(i));
        }
        int originIndex = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.println(" Select Destination:");
        for (int i = 0; i < locations.size(); i++) {
            if (i == originIndex) continue;
            System.out.printf("[%d] %s\n", i + 1, locations.get(i));
        }
        int destinationIndex = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.println(" Select Plane:");
        for (int i = 0; i < planes.size(); i++) {
            System.out.printf("[%d] %s\n", i + 1, planes.get(i));
        }
        int planeIndex = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Time (HH:MM): ");
        LocalTime time = LocalTime.parse(scanner.nextLine());

        flights.add(new Flight(locations.get(originIndex), locations.get(destinationIndex), planes.get(planeIndex), date, time));
        System.out.println("[**OK**] Flight added.");
    }
}
