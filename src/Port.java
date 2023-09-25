import java.util.ArrayList;

public class Port implements IPort {
    private String Id;
    private double latitude;
    private double longitude;
    private ArrayList<Container> containers;
    private ArrayList<Ship> history;
    private ArrayList<Ship> current;

    public Port(String Id, double latitude, double longitude) {
        this.Id = Id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.containers = new ArrayList<>();
        this.history = new ArrayList<>();
        this.current = new ArrayList<>();
        Container.addValidPort(Id);
        Ship.addValidPort(Id);
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
