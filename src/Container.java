import java.util.ArrayList;

public abstract class Container {
    protected int serialNumber;
    protected int weight;

    protected String type;

    protected String portId;



    // Port id where container is currently at
    private static ArrayList<String> validPortIds = new ArrayList<>();

    public Container(int serialNumber, int weight, String portId){
        this.serialNumber= serialNumber;
        this.weight=weight;
        this.portId = null; // Initialize the port ID to null when the container is created

        if (isValidPort(portId)) {
            this.portId = portId;
        } else {
            System.out.println("Invalid port ID provided: " + portId);
        }


    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getType() {
        return type;
    }

    public String getPortId() {
        return portId;
    }

    public static ArrayList<String> getValidPortIds() {
        return validPortIds;
    }

    public void setPortId(String portId) {
        if (isValidPort(portId)) {
            this.portId = portId;
        } else {
            System.out.println("Invalid port ID provided: " + portId);
        }
    }

    // Method to check if a port ID is valid
    public static boolean isValidPort(String portId) {
        // Implement your logic to validate port existence here
        // For example, you can check if the port ID exists in the list of valid port IDs
        return validPortIds.contains(portId);
    }

    // Method to add valid port IDs
    public static void addValidPort(String portId) {
        validPortIds.add(portId);
    }


}
