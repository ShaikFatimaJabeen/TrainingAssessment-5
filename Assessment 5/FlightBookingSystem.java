
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Flight {
    String flightNumber;
    LocalDate departureDate;
    double price;

    public Flight(String flightNumber, LocalDate departureDate, double price) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.price = price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public double getPrice() {
        return price;
    }
}

public class FlightBookingSystem {
    public static void main(String[] args) {
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("AI101", LocalDate.of(2025, 4, 10), 500));
        flights.add(new Flight("AI102", LocalDate.of(2025, 4, 12), 450));
        flights.add(new Flight("AI103", LocalDate.of(2025, 4, 5), 400));

        
        Optional<Flight> bestFlight = flights.stream()
                .filter(flight -> flight.getDepartureDate().isAfter(LocalDate.now()))
                .min(Comparator.comparingDouble(Flight::getPrice));

        bestFlight.ifPresent(flight -> 
            System.out.println("Best Flight: " + flight.getFlightNumber() + " on " + flight.getDepartureDate() + " for $" + flight.getPrice()));
    }
}
