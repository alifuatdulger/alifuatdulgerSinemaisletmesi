import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Reservation {
    private static int idCounter = 1000;

    private final int reservationId;
    @JsonBackReference
    private Flight flight;
    private String name;
    private String surname;
    private int age;
    private String gender;
    private LocalDate dateOfIssue;
    private int seatNumber;

    public Reservation() {
        this.reservationId = idCounter++;
    }

    public Reservation(Flight flight, String name, String surname, int age, String gender, LocalDate dateOfIssue) {
        this.reservationId = idCounter++;
        this.flight = flight;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.dateOfIssue = dateOfIssue;
    }

    public void assignSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    // Getters for serialization
    public int getReservationId() { return reservationId; }
    public Flight getFlight() { return flight; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public LocalDate getDateOfIssue() { return dateOfIssue; }
    public int getSeatNumber() { return seatNumber; }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId + " | " + name + " " + surname +
                ", Seat: " + seatNumber + ", " + flight.getOrigin().getCity() +
                " → " + flight.getDestination().getCity();
    }
}
