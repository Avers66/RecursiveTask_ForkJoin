import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    StationIndex stationIndex;
    RouteCalculator routeCalculator;
    @Override
    protected void setUp() throws Exception {
      route = new ArrayList<>();
      stationIndex = new StationIndex();
      routeCalculator = new RouteCalculator(stationIndex);

        Line line1 = new Line(1, "line1");
        Line line2 = new Line(2, "line2");
        Line line3 = new Line(3, "line3");
        Station s1l1 = new Station("S1.1", line1);
        Station s2l1 = new Station("S2.1", line1);
        Station s3l1 = new Station("S3.1", line1);
        Station s1l2 = new Station("S1.2", line2);
        Station s2l2 = new Station("S2.2", line2);
        Station s3l2 = new Station("S3.2", line2);
        Station s1l3 = new Station("S1.3", line3);
        Station s2l3 = new Station("S2.3", line3);
        Station s3l3 = new Station("S3.3", line3);
        route.add(s1l1);
        route.add(s2l1);
        route.add(s3l1);
        route.add(s1l2);
        line1.addStation(s1l1);
        line1.addStation(s2l1);
        line1.addStation(s3l1);
        line2.addStation(s1l2);
        line2.addStation(s2l2);
        line2.addStation(s3l2);
        line3.addStation(s1l3);
        line3.addStation(s2l3);
        line3.addStation(s3l3);
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addStation(s1l1);
        stationIndex.addStation(s2l1);
        stationIndex.addStation(s3l1);
        stationIndex.addStation(s1l2);
        stationIndex.addStation(s2l2);
        stationIndex.addStation(s3l2);
        stationIndex.addStation(s1l3);
        stationIndex.addStation(s2l3);
        stationIndex.addStation(s3l3);

        List<Station> connection12 = new ArrayList<>();
        connection12.add(s3l1);
        connection12.add(s1l2);
        stationIndex.addConnection(connection12);
        List<Station> connection23 = new ArrayList<>();
        connection23.add(s3l2);
        connection23.add(s1l3);
        stationIndex.addConnection(connection23);

    }

    public void testCalculateDuration() {
        double expected = 8.5;
        double actual = RouteCalculator.calculateDuration(route);
        assertEquals(expected, actual);

    }

    public void testGetShortestRoute(){
        List<Station> expected = List.of(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S2.1"),stationIndex.getStation("S3.1"));
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S3.1"));
        assertEquals(expected, actual);
    }

    public void testGetRouteOnTheLine(){
        List<Station> expected = List.of(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S2.1"),stationIndex.getStation("S3.1"));
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S3.1"));
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnection(){
        List<Station> expected = List.of(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S2.1"),stationIndex.getStation("S3.1"),
                stationIndex.getStation("S1.2"),stationIndex.getStation("S2.2"));
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S2.2"));
        assertEquals(expected, actual);
    }

    public void testIsConnected(){
        List<Station> expected = List.of(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S2.1"),stationIndex.getStation("S3.1"),
                stationIndex.getStation("S1.2"),stationIndex.getStation("S2.2"));
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S2.2"));
        assertEquals(expected, actual);
    }

    public void testGetRouteWithTwoConnections(){
        List<Station> expected = List.of(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S2.1"),stationIndex.getStation("S3.1"),
                stationIndex.getStation("S1.2"),stationIndex.getStation("S2.2"),
                stationIndex.getStation("S3.2"),stationIndex.getStation("S1.3"),
                stationIndex.getStation("S2.3"));
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S2.3"));
        assertEquals(expected, actual);
    }

    public void testGetRouteViaConnectedLine(){
        List<Station> expected = List.of(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S2.1"),stationIndex.getStation("S3.1"),
                stationIndex.getStation("S1.2"),stationIndex.getStation("S2.2"),
                stationIndex.getStation("S3.2"),stationIndex.getStation("S1.3"),
                stationIndex.getStation("S2.3"));
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("S1.1"),
                stationIndex.getStation("S2.3"));
        assertEquals(expected, actual);
    }




    @Override
    protected void tearDown() throws Exception {

    }
}
