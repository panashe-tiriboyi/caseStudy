import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Scanner;

public class PortManagementSystem {
    Scanner scanner = new Scanner(System.in);
    protected Port port0 =new Port("Port0",0,0);

    protected ArrayList<Ship> ships = new ArrayList<>();
    protected ArrayList<Container> containers = new ArrayList<>();
    protected ArrayList<Port> ports = new ArrayList<>();

    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";



    public boolean createContainer(){

        System.out.println("1. Create Basic Container (weight limit < 5000t)");
        System.out.println("2. Create Heavy Container ");
        System.out.println("3. Create Refrigerated Container ");
        System.out.println("4. Create Liquid Container ");
        int containerChoice = scanner.nextInt();
        System.out.print("Enter Container Serial Number : ");
        int serialNumber= scanner.nextInt();
        System.out.print("Enter Container weight  (if it is a basic container, weight < 5000t) : ");
        int weight = scanner.nextInt();
        System.out.print("Enter the  container's current port's id : ");
        String portId = scanner.next();
        switch (containerChoice){

            case 1:
                try {
                    if (weight < 5000) {
                        String dynamicContainerName = "container" + serialNumber;
                        BasicContainer container = new BasicContainer(serialNumber, weight, portId);
                        containers.add(container);
                    } else {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(RED +"IllegalArgumentException caught: Cannot instantiate a BasicContainer object, weight > 5000!" + RESET );

                }
                break;
            case  2:
                HeavyContainer heavyContainer = new HeavyContainer(serialNumber,weight,portId);
                containers.add(heavyContainer);
                break;
            case 3:
                RefrigeratedContainer refrigeratedContainer = new RefrigeratedContainer(serialNumber,weight,portId);
                containers.add(refrigeratedContainer);
                break;
            case 4:
                LiquidContainer liquidContainer = new LiquidContainer(serialNumber,weight,portId);
                containers.add(liquidContainer);
                break;
            default:
                System.out.println(RED + "Unexpected value: " + containerChoice + RESET);
                break;

        }
        return false;
    }

    public boolean createShip(){
        System.out.print("Enter Ship ID : ");
        int shipId = scanner.nextInt();
        System.out.print("Enter ship fuel volume : ");
        int fuel = scanner.nextInt();

        Ship newShip = new Ship(shipId, fuel, port0);
        ships.add(newShip);
        System.out.println(ships);
        System.out.println(containers);
        return false;
    }

    public boolean createPort(){
        System.out.print("Enter Port ID : ");
        String newPortId = scanner.next();
        System.out.print("Enter port latitude : ");
        double latitude = scanner.nextInt();
        System.out.print("Enter port longitude : ");
        double longitude = scanner.nextInt();

        Port port =new Port(newPortId, latitude, longitude);
        ports.add(port);
        return false;
    }

    public boolean editShip_Or_Container(){
        System.out.println("1. Edit Ship");
        System.out.println("2. Edit Container");
        int editChoice = scanner.nextInt();
        switch (editChoice) {
            case 1:
                System.out.print("Enter ship id ");
                int editShipId = scanner.nextInt();

                // Find the ship with the specified ID
                Ship editShip = null;


                for (Ship s : ships) {
                    if (s.getId() == editShipId) {
                        editShip = s;
                        System.out.println("1.Edit Ship's current Port");
                        System.out.println("2.Edit Ship's fuel volume");
                        int editShipChoice= scanner.nextInt();
                        switch (editShipChoice){
                            case 1:
                                System.out.print("Enter Port id : ");
                                String newPortId = scanner.next();
                                s.setPortId(newPortId);
                                for (Port p : ports){
                                    if (p.getId().equals(newPortId)){
                                        s.setCurrentPort(p);
                                        System.out.println(GREEN + "Ship "+ s.getId()+ " Current Port changed successfully." +RESET);
                                    }
                                }
                                break;
                            case 2:
                                System.out.println("Enter new Ship's fuel Volume : ");
                                double newFuelVolume = scanner.nextDouble();
                                s.setFuelVolume(newFuelVolume);
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                        break; // Found the ship, exit the loop
                    }else
                    {
                        System.out.println(RED+"Invalid Ship Id"+ RESET);
                    }
                }

                break;
            case 2:
                System.out.print("Enter container id ");
                int editContainerId = scanner.nextInt();
                Container editContainer = null;
                for (Container c : containers) {
                    if (c.getSerialNumber() == editContainerId) {
                        editContainer = c;
                        System.out.println("1.Edit portId");
                        System.out.println("2.Edit weight");
                        int editContainerChoice = scanner.nextInt();
                        switch (editContainerChoice){
                            case 1:
                                System.out.print("Enter new port id : ");
                                String newPortId = scanner.next();
                                c.setPortId(newPortId);
                                break;
                            case 2:
                                System.out.println("Enter new weight : ");
                                int newWeight = scanner.nextInt();
                                c.setWeight(newWeight);
                        }
                        break;
                    }else
                    {
                        System.out.println(RED+"Invalid Container Id"+ RESET);
                    }
                }


        }


        return  false;

    }

    public boolean loadContainerToShip(){
        System.out.print("Enter conatiner id : ");
        int containerId = scanner.nextInt();
        System.out.print("Enter ship id ");
        int loadingShipId = scanner.nextInt();

        // Find the ship with the specified ID
        Ship loadingShip = null;
        Container loadingContainer= null;

        for (Ship s : ships) {
            if (s.getId() == loadingShipId) {
                loadingShip = s;
                break; // Found the ship, exit the loop
            }
        }

        for (Container c : containers) {
            if (c.getSerialNumber() == containerId) {
                loadingContainer = c;
                break;
            }
        }
        if (loadingShip != null) {
            boolean loaded= false;
            if(loadingContainer != null){
                loaded = loadingShip.load(loadingContainer);
            }
            else {
                System.out.println(RED + "Container with ID " +containerId + " not found." + RESET);
            }


            if (loaded) {
                System.out.println(GREEN + "Container, serial number "+ containerId +" loaded successfully into Ship " + loadingShip.getId() + RESET);
            } else {
                System.out.println(RED + "Container loading failed. Check ship's capacity or other constraints." + RESET);
            }
        } else {
            System.out.println(RED + "Ship with ID " + loadingShipId + " not found." + RESET);
        }

        return false;

    }

    public boolean unloadContainerFromShip(){
        System.out.print("Enter conatiner id : ");
        int cId = scanner.nextInt();
        System.out.print("Enter ship id ");
        int unloadingSId = scanner.nextInt();

        // Find the ship with the specified ID
        Ship unloadingS = null;
        Container unloadingC= null;
        for (Ship s : ships) {
            if (s.getId() == unloadingSId) {
                unloadingS = s;
                break; // Found the ship, exit the loop
            }
        }

        for (Container c : containers) {
            if (c.getSerialNumber() == cId) {
                unloadingC = c;
                break;
            }
        }
        if (unloadingS != null) {
            boolean unloaded = false;
            if(unloadingC != null){
                unloaded = unloadingS.unload(unloadingC);
            }
            else {
                System.out.println("Ship with ID " + cId + " not found.");
            }


            if (unloaded) {
                System.out.println(GREEN + "Container, serial number "+ cId + " unloaded successfully from Ship " + unloadingS.getId() + RESET);
            } else {
                System.out.println(RED + "Container unloading failed." + RESET);
            }
        } else {
            System.out.println(RED + "Ship with ID " + unloadingSId + " not found." + RESET);
        }

        return false;
    }

    public boolean sailShipToPort(){
        System.out.print("Enter ship id ");
        int sailingShipId = scanner.nextInt();
        System.out.print("Enter destination port id ");
        String destinationPortId = scanner.next();
        Ship sailingShip = null;
        Port destinationPort = null;
        for (Ship s : ships) {
            if (s.getId() == sailingShipId) {
                System.out.println("s id : " + s.getId());
                sailingShip = s;
                break; // Found the ship, exit the loop
            }
        }

        for (Port p : ports){

            if(p.getId().equals(destinationPortId) ){
                System.out.println("p id : " + p.getId());
                destinationPort= p;
                break;
            }
        }


        if(sailingShip != null && destinationPort != null){
            sailingShip.sailTo(destinationPort);
        }else{
            System.out.println(RED + "Invalid Port or Ship" + RESET);
        }
        return false;
    }

    public boolean refuelShip(){
        System.out.print("Enter ship id ");
        int refuelShipId = scanner.nextInt();
        System.out.print("Enter Fuel amount");
        double refuelAmount = scanner.nextDouble();
        Ship refuelShip = null;

        for (Ship s : ships) {
            if (s.getId() == refuelShipId) {
                refuelShip = s;
                break; // Found the ship, exit the loop
            }
        }

        if(refuelShip != null){
            refuelShip.reFuel(refuelAmount);
        }
        return false;
    }
}
