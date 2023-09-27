import java.text.DecimalFormat;
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


    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    public static  final String BLUE = "\u001B[34m";

    public Ship(int Id, double fuelVolume, Port currentPort) {
        // Initialize other variables...
        containers = new ArrayList<>();
        heavyContainerCount = 0;
        refrigeratedContainerCount = 0;
        liquidContainerCount = 0;
        basicContainerCount = 0;
        this.currentPort = currentPort;
        portId = null;
        this.Id = Id;
        this.fuelVolume = fuelVolume;
        if (isValidPort(currentPort.getId())) {
            this.portId = currentPort.getId();
            System.out.println( GREEN + " Ship " + Id + " was successfully built at " + portId + RESET );
        } else {
            System.out.println(RED + "Invalid port ID provided: " + portId + RESET);
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
                System.out.println(RED + "Container is not on any existing port." + RESET);
            }
        }else{
            System.out.println(RED + "Cannot load container weight limit reached" +RESET);
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
        System.out.println(RED + "Container is not on the ship." + RESET);
    }

    return false;
    }

    @Override
    public boolean sailTo(Port p) {
        double distance = calculateDistance(p, currentPort);
        double fuelRequired = calculateFuelRequired(distance);

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedDistance = df.format(distance);
        String formattedFuelRequired = df.format(fuelRequired);

        if (fuelRequired <= fuelVolume) {
            currentPort.outgoingShip(this);
            currentPort = p;
            currentPort.incomingShip(this);
            fuelVolume -= fuelRequired;
            String formattedFuelVolume = df.format(fuelVolume);

            for(Container c : containers){
                c.setPortId(p.getId());
            }

            System.out.println(GREEN + "Ship " + Id +" has traveled " + formattedDistance +"km from "+currentPort.getId() + " to "+ p.getId() + RESET);
            System.out.println(BLUE + "Fuel used : "+ formattedFuelRequired + RESET);
            System.out.println(BLUE +"Fuel left : " + formattedFuelVolume + RESET);
            return true;
        } else {
            System.out.println(RED + "Ship does not have enough fuel to sail to the destination." + RESET);
            System.out.println(BLUE + "Fuel required is : "+ formattedFuelRequired + RESET);
            return false;
        }
    }
    @Override
    public void reFuel(double newFuel){
        fuelVolume+= newFuel;
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedFuelVolume = df.format(fuelVolume);
        System.out.println(BLUE + "Refuel successfully done, new fuel Volume  : "+ formattedFuelVolume+"liters" + RESET);
    }
    // Add additional methods as per requirements
    // Method to check if a port ID is valid


    public void setPortId(String portId) {

        if(isValidPort(portId)){
            this.portId = portId;
            System.out.println(GREEN+ "Port "+portId+" successfully edited" + RESET);
        }else {
            System.out.println(RED+ "Edit Port failed, Port does not exist" + RESET);
        }

    }

    public void setFuelVolume(double newFuelVolume){
        fuelVolume = newFuelVolume;
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedFuelVolume = df.format(fuelVolume);
        System.out.println(GREEN + "New fuel Volume : " + formattedFuelVolume+"liters");
    }

    public void setCurrentPort(Port p){
        currentPort= p;

    }

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
