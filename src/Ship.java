import java.util.ArrayList;
import java.util.Scanner;

public class Ship implements IShip{
    private int Id;
    private double fuelVolume;
    private Port currentPort;
    private String portId;


    final private int fuelConsumptionPerKM = 50;

    final private int maxTotalContainerWeight = 85000;

    final private int maxNumberOfAllContainers= 20;
    final private int maxNumberOfBasicContainer = 20;
    final private int maxNumberOfHeavyContainer = 17;

    final private int maxNumberOfRefrigeratedContainer = 10;
    final private int maxNumberOfLiquidContainer = 12;




    private ArrayList<Container> containers;
    private int heavyContainerCount;
    private int refrigeratedContainerCount;
    private int liquidContainerCount;
    private int basicContainerCount;

    private int weightCount;
    private static ArrayList<String> validPortIds = new ArrayList<>();


    public Ship(int ID, double fuelVolume, Port currentPort) {
        // Initialize other variables...
        containers = new ArrayList<>();
        heavyContainerCount = 0;
        refrigeratedContainerCount = 0;
        liquidContainerCount = 0;
        basicContainerCount = 0;
        this.currentPort = currentPort;
        portId = null;
        if (isValidPort(currentPort.getId())) {
            this.portId = currentPort.getId();
        } else {
            System.out.println("2.Invalid port ID provided: " + portId);
        }

    }

    // Other methods...



    public boolean load(Container cont) {

        if(weightCount < maxTotalContainerWeight && cont.getWeight() < maxTotalContainerWeight){
            weightCount += cont.getWeight();
            if (cont.getPortId()!= null ){
                if (cont.getPortId().equals(portId)){
                    System.out.println("same port");
                    int totalNumberOfContainer =
                            heavyContainerCount + basicContainerCount + refrigeratedContainerCount + liquidContainerCount;
                    if(totalNumberOfContainer < maxNumberOfAllContainers){
                        // Check the type of the container and add it to the appropriate list
                        if (cont instanceof HeavyContainer) {
                            if (heavyContainerCount < maxNumberOfHeavyContainer) {
                                containers.add(cont);
                                heavyContainerCount++;
                                return true;
                            } else {
                                System.out.println("Maximum heavy container limit reached.");
                            }
                        } else if (cont instanceof RefrigeratedContainer) {
                            if (refrigeratedContainerCount < maxNumberOfRefrigeratedContainer) {
                                containers.add(cont);
                                refrigeratedContainerCount++;
                                return true;
                            } else {
                                System.out.println("Maximum refrigerated container limit reached.");
                            }
                        } else if (cont instanceof LiquidContainer) {
                            if (liquidContainerCount < maxNumberOfLiquidContainer) {
                                containers.add(cont);
                                liquidContainerCount++;
                                return true;
                            } else {
                                System.out.println("Maximum liquid container limit reached.");
                            }
                        } else if (cont instanceof BasicContainer) {
                            if (basicContainerCount < maxNumberOfBasicContainer) {
                                containers.add(cont);
                                basicContainerCount++;
                                return true;
                            } else {
                                System.out.println("Maximum basic container limit reached.");
                            }
                        } else {
                            System.out.println("Unsupported container type.");
                        }
                    }
                    else{
                        System.out.println("Maximum Number of all container limit reached.");
                    }

                }else {
                    System.out.println("port not same");
                }

            }else
            {
                System.out.println("Container is not on any existing port.");
            }
        }else{
            System.out.println("Cannot load container weight limit reached");
        }



        return false;
    }

    // Other methods...

    // Additional methods to get container counts for each type
    public int getHeavyContainerCount() {
        return heavyContainerCount;
    }

    public int getRefrigeratedContainerCount() {
        return refrigeratedContainerCount;
    }

    public int getLiquidContainerCount() {
        return liquidContainerCount;
    }

    public int getBasicContainerCount() {
        return basicContainerCount;
    }



    @Override
    public boolean unload(Container cont) {
    // Check if the container is on the ship
    if (containers.contains(cont)) {
        // Check the type of the container and remove it from the appropriate list
        if (cont instanceof HeavyContainer) {
            containers.remove(cont);
            heavyContainerCount--;
            return true;
        } else if (cont instanceof RefrigeratedContainer) {
            containers.remove(cont);
            refrigeratedContainerCount--;
            return true;
        } else if (cont instanceof LiquidContainer) {
            containers.remove(cont);
            liquidContainerCount--;
            return true;
        } else if (cont instanceof BasicContainer) {
            containers.remove(cont);
            basicContainerCount--;
            return true;
        }
    } else {
        System.out.println("Container is not on the ship.");
    }

    return false;
    }

    @Override
    public boolean sailTo(Port p) {
        double distance = calculateDistance(p, currentPort);
        double fuelRequired = calculateFuelRequired(distance);

        if (fuelRequired <= fuelVolume) {
            currentPort.outgoingShip(this);
            currentPort = p;
            currentPort.incomingShip(this);
            fuelVolume -= fuelRequired;

            return true;
        } else {
            System.out.println("Ship does not have enough fuel to sail to the destination.");
            return false;
        }
    }
    @Override
    public void reFuel(double newFuel){
        fuelVolume+= newFuel;
    }
    // Add additional methods as per requirements
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

    private double calculateDistance(Port p, Port currentPort) {
        // Calculate the distance between two ports based on their coordinates
        double xDiff = p.getLatitude() - currentPort.getLatitude();
        double yDiff = p.getLatitude() - currentPort.getLatitude();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);

    }

    private double calculateFuelRequired(double distance) {
        // Calculate the fuel consumption required to sail a certain distance
        double v = distance * fuelConsumptionPerKM;
        return v;
    }

    public int getId() {
        return Id;
    }
}
