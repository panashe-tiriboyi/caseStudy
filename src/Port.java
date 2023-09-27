import java.util.ArrayList;

public class Port implements IPort {
    private String Id;
    private double latitude;
    private double longitude;
    private ArrayList<Container> containers;
    private ArrayList<Ship> history;
    private ArrayList<Ship> current;

    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static  final String BLUE = "\u001B[34m";

    public Port(String Id, double latitude, double longitude) {
        this.Id = Id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.containers = new ArrayList<>();
        this.history = new ArrayList<>();
        this.current = new ArrayList<>();
        Container.addValidPort(Id);
        Ship.addValidPort(Id);
        System.out.println(GREEN + Id + " was successfully built "+ RESET);
    }

    @Override
    public void incomingShip(Ship s) {
        current.add(s);
    }

    @Override
    public void outgoingShip(Ship s) {
        current.remove(s);
        history.add(s);
    }

    public String getId() {
        return Id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
