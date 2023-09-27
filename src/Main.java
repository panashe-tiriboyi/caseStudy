import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

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
            PortManagementSystem newSystem = new PortManagementSystem();


            while (true) {
                System.out.println("1. Create Container");
                System.out.println("2. Create Ship");
                System.out.println("3. Create Port");
                System.out.println("4. Edit Container or Ship");
                System.out.println("5. Load Container to Ship");
                System.out.println("6. Unload Container from Ship");
                System.out.println("7. Sail Ship to Port");
                System.out.println("8. Refuel Ship");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        newSystem.createContainer();
                        break;
                    case 2:
                        newSystem.createShip();
                        break;
                    case 3:
                        newSystem.createPort();
                        break;
                    case 4:
                        newSystem.editShip_Or_Container();
                        break;
                    case 5:
                        newSystem.loadContainerToShip();
                        break;
                    case 6:
                        newSystem.unloadContainerFromShip();
                        break;
                    case 7:
                        newSystem.sailShipToPort();
                        break;
                    case 8:
                        newSystem.refuelShip();
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