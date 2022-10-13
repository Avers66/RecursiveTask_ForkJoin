import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;
import org.checkerframework.checker.units.qual.A;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Flight> allFlight = new ArrayList<>();
        for (Terminal t : airport.getTerminals()){
            allFlight.addAll(t.getFlights());
        }
        Long current = new Date().getTime();
        Long current2 = current + 7200000;
        List<Flight> out = new ArrayList<>();
        out = allFlight.stream()
                .filter((f) -> (f.getDate().getTime() - current > 0) && (current2 - f.getDate().getTime() > 0))
                .filter((f) -> f.getType() == Flight.Type.DEPARTURE)
                .sorted((f1, f2) -> f1.getDate().compareTo(f2.getDate()))
                .collect(Collectors.toList());
                out.forEach(System.out::println);






    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        List<Flight> allFlight = new ArrayList<>();
        for (Terminal t : airport.getTerminals()){
            allFlight.addAll(t.getFlights());
        }
        Long current = new Date().getTime();
        Long current2 = current + 7200000;
        List<Flight> out = new ArrayList<>();
        out = allFlight.stream()
                .filter((f) -> (f.getDate().getTime() - current > 0) && (current2 - f.getDate().getTime() > 0))
                .filter((f) -> f.getType() == Flight.Type.DEPARTURE)
                //.sorted((f1, f2) -> f1.getDate().compareTo(f2.getDate()))
                .collect(Collectors.toList());
        return out;
    }

}