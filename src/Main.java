import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("HELLO");

        Port port0 =new Port("Port0",0,0);
        Port port3 =new Port("Port3",12,13);
        Port port4 =new Port("Port4",12,13);
        ArrayList<Ship> ships= new ArrayList<>();
        ArrayList<Container> containers = new ArrayList<>();
        ArrayList<Port> ports = new ArrayList<>();
//        Ship ship = new Ship(1, 1000.0,port); // Replace "Port A" with an actual Port instance


//        Container container = new HeavyContainer(4, 5000, "Port1");

//        boolean loaded = ship.load(container);
        // Create a container
        //Container container = new HeavyContainer(1, 500); // Replace HeavyContainer with the appropriate container type

        // Call the load method
//        boolean loaded = ship.load(container);
        /*for (int i = 0; i<20 ;i++){
            Container container = new HeavyContainer(i, 500, "Port"+i);

            boolean loaded = ship.load(container);
            if (loaded) {
                System.out.println("Container loaded successfully Port"+i );
            } else {
                System.out.println("Failed to load container.");
            }
        }*/


            Scanner scanner = new Scanner(System.in);


            while (true) {
                System.out.println("1. Create Container");
                System.out.println("2. Create Ship");
                System.out.println("3. Create Port");
                System.out.println("4. Load Container to Ship");
                System.out.println("5. Unload Container from Ship");
                System.out.println("6. Sail Ship to Port");
                System.out.println("7. Refuel Ship");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("1. Create Basic Container (weight limit < 5000t)");
                        System.out.println("2. Create Heavy Container ");
                        System.out.println("3. Create Refrigerated Container ");
                        System.out.println("4. Create Liquid Container ");
                        int containerChoice = scanner.nextInt();
                        System.out.print("Enter Container Serial Number : ");
                        int serialNumber= scanner.nextInt();
                        System.out.print("Enter Container weight  (basic container weight <5000t) : ");
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
                                    System.out.println("IllegalArgumentException caught: Cannot instantiate a BasicContainer object, weight > 5000!" );

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
                                System.out.println("Unexpected value: " + containerChoice);
                                break;

                        }
                        break;
                    case 2:
                        System.out.println("Enter Ship ID : ");
                        int shipId = scanner.nextInt();
                        System.out.println("Enter ship fuel volume : ");
                        int fuel = scanner.nextInt();

                        Ship ship =new Ship(shipId, fuel, port0);
                        ships.add(ship);
                        break;
                    case 3:
                        System.out.println("Enter Port ID : ");
                        String newPortId = scanner.next();
                        System.out.println("Enter ship latitude : ");
                        double latidute = scanner.nextInt();
                        System.out.println("Enter ship longitude : ");
                        double longitude = scanner.nextInt();

                        Port port =new Port(newPortId, latidute, latidute);
                        ports.add(port);
                        break;
                    case 4:
                        System.out.println("Enter conatiner id : ");
                        int containerId = scanner.nextInt();
                        System.out.println("Enter ship id ");
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
                            System.out.println("Ship with ID " +containerId + " not found.");
                           }


                            if (loaded) {
                                System.out.println("Container loaded successfully into Ship " + loadingShip.getId());
                            } else {
                                System.out.println("Container loading failed. Check ship's capacity or other constraints.");
                            }
                        } else {
                            System.out.println("Ship with ID " + loadingShipId + " not found.");
                        }


                        break;
                    case 5:
                        System.out.println("Enter conatiner id : ");
                        int cId = scanner.nextInt();
                        System.out.println("Enter ship id ");
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
                                System.out.println("Ship with ID " +cId + " not found.");
                            }


                            if (unloaded) {
                                System.out.println("Container uhnloaded successfully into Ship " + unloadingS.getId());
                            } else {
                                System.out.println("Container unloading failed.");
                            }
                        } else {
                            System.out.println("Ship with ID " + unloadingSId + " not found.");
                        }
                        break;
                    case 6:

                        System.out.println("Enter ship id ");
                        int sailingShipId = scanner.nextInt();
                        System.out.println("Enter destination port id");
                        String destinationPortId = scanner.next();
                        Ship sailingShip = null;
                        Port destinationPort = null;
                        for (Ship s : ships) {
                            if (s.getId() == sailingShipId) {
                                sailingShip = s;
                                break; // Found the ship, exit the loop
                            }
                        }

                        for (Port p : ports){
                            if(p.getId() == destinationPortId){
                                destinationPort= p;
                                break;
                            }
                        }

                        if(sailingShip != null && destinationPort != null){
                            sailingShip.sailTo(destinationPort);
                        }else{
                            System.out.println("Invalid POrt or Ship");
                        }



                        break;
                    case 7:
                        System.out.println("Enter ship id ");
                        int refuelShipId = scanner.nextInt();
                        System.out.println("Enter Fuel amount");
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
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }


    }
}