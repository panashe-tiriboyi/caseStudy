import java.util.ArrayList;

public class Port implements IPort {
    private int ID;
    private double x;
    private double y;
    private ArrayList<Container> containers;
    private ArrayList<Ship> history;
    private ArrayList<Ship> current;

    public Port(int ID, double x, double y) {
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.containers = new ArrayList<>();
        this.history = new ArrayList<>();
        this.current = new ArrayList<>();
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
}
