import java.util.ArrayList;
import java.util.Scanner;

public class Ship implements IShip{
    private int ID;
    private double fuelVolume;
    private Port currentPort;


    final private int fuelConsumption = 50;

    final private int maxTotalContainerWeight = 85000;

    final private int maxBasicContainerNumber = 20;
    final private int maxHeavyContainerNumber = 17;

    final private int maxRefrigeratedContainerNumber = 10;
    final private int maxLiquidContainerNumber = 12;




    private ArrayList<Container> containers;
    private int heavyContainerCount;
    private int refrigeratedContainerCount;
    private int liquidContainerCount;
    private int basicContainerCount;

    public Ship(int ID, double fuelVolume, Port currentPort) {
        // Initialize other variables...
        containers = new ArrayList<>();
        heavyContainerCount = 0;
        refrigeratedContainerCount = 0;
        liquidContainerCount = 0;
        basicContainerCount = 0;
    }

    // Other methods...

    public boolean load(Container cont) {
        // Check the type of the container and add it to the appropriate list
        if (cont instanceof HeavyContainer) {
            if (heavyContainerCount < maxHeavyContainerNumber) {
                containers.add(cont);
                heavyContainerCount++;
                return true;
            } else {
                System.out.println("Maximum heavy container limit reached.");
            }
        } else if (cont instanceof RefrigeratedContainer) {
            if (refrigeratedContainerCount < maxRefrigeratedContainerNumber) {
                containers.add(cont);
                refrigeratedContainerCount++;
                return true;
            } else {
                System.out.println("Maximum refrigerated container limit reached.");
            }
        } else if (cont instanceof LiquidContainer) {
            if (liquidContainerCount < maxLiquidContainerNumber) {
                containers.add(cont);
                liquidContainerCount++;
                return true;
            } else {
                System.out.println("Maximum liquid container limit reached.");
            }
        } else if (cont instanceof BasicContainer) {
            if (basicContainerCount < maxBasicContainerNumber) {
                containers.add(cont);
                basicContainerCount++;
                return true;
            } else {
                System.out.println("Maximum basic container limit reached.");
            }
        } else {
            System.out.println("Unsupported container type.");
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
        double distance = calculateDistance(500);
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

    private double calculateDistance(int distance) {
        // Calculate the distance between two ports based on their coordinates
        return distance;
    }

    private double calculateFuelRequired(double distance) {
        // Calculate the fuel consumption required to sail a certain distance
        double v = distance * fuelConsumption;
        return v;
    }



}
