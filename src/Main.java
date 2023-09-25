import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("HELLO");
        Port port =new Port(1,12,13);
        Ship ship = new Ship(1, 1000.0, new Port(1,12,13)); // Replace "Port A" with an actual Port instance

        // Create a container
        //Container container = new HeavyContainer(1, 500); // Replace HeavyContainer with the appropriate container type

        // Call the load method
//        boolean loaded = ship.load(container);
        for (int i = 0; i<20 ;i++){
            Container container = new HeavyContainer(i, 500);
            boolean loaded = ship.load(container);
            if (loaded) {
                System.out.println("Container loaded successfully.");
            } else {
                System.out.println("Failed to load container.");
            }
        }


            /*Scanner scanner = new Scanner(System.in);
            PortManagementSystem system = new PortManagementSystem();

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
                        system.createContainer();
                        break;
                    case 2:
                        system.createShip();
                        break;
                    case 3:
                        system.createPort();
                        break;
                    case 4:
                        system.loadContainerToShip();
                        break;
                    case 5:
                        system.unloadContainerFromShip();
                        break;
                    case 6:
                        system.sailShipToPort();
                        break;
                    case 7:
                        system.refuelShip();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }*/


    }
}