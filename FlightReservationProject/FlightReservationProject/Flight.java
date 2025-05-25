import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Flight {
    private Location origin;
    private Location destination;
    private Plane airplane;
    private LocalDate date;
    private LocalTime time;

    @JsonManagedReference
    private List<Reservation> reservations = new ArrayList<>();

    public Flight() {}

    public Flight(Location origin, Location destination, Plane airplane, LocalDate date, LocalTime time) {
        this.origin = origin;
        this.destination = destination;
        this.airplane = airplane;
        this.date = date;
        this.time = time;
    }

    public Location getOrigin() { return origin; }
    public Location getDestination() { return destination; }
    public Plane getAirplane() { return airplane; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public List<Reservation> getReservations() { return reservations; }

    public int getTotalPassengers() {
        return reservations.size();
    }

    public boolean addReservation(Reservation r) {
        if (reservations.size() < airplane.getCapacity()) {
            r.assignSeatNumber(reservations.size() + 1);
            reservations.add(r);
            return true;
        }
        return false;
    }

    public String toString() {
        return origin.getCity() + " → " + destination.getCity() + " | " + date + " " + time + " | " + getTotalPassengers() + "/" + airplane.getCapacity() + " booked";
    }
}
