//FREEZE CODE BEGIN
import java.io.*;
import java.util.*;

public class Restaurant {
    public static void main(String[] args) throws Exception {
            Scanner finput = new Scanner(new File("config.txt")); 
            // Load the menu
            Menu menu = new Menu(finput);

            finput.nextLine(); //empty line
            String parts[] = finput.nextLine().split(" ");
            int numTables = Integer.parseInt(parts[0]);
            Table[] tables = new Table[numTables];

            // Read table configurations (ID and capacity)
            for (int i = 0; i < numTables; i++) {
                String[] tableInfo = finput.nextLine().split(" ");
                int tableId = Integer.parseInt(tableInfo[0]);
                int capacity = Integer.parseInt(tableInfo[1]);
                tables[i] = new Table(tableId, capacity);
            }

            // Input stream for user commands
            Scanner scanner = new Scanner(System.in);
            String line;

            // Main loop to process commands
            while (true) {
                line = scanner.nextLine();
                if (line == null || line.trim().isEmpty()) {
                    break;
                }

                parts = line.split(" ");
                if (parts[0].charAt(0) == 'C')
                    return;

                int tableNumber = Integer.parseInt(parts[0]);
                char commandCode = parts[1].charAt(0);

                // Find the corresponding table by table number
                Table table = null;
                for (Table t : tables) {
                    if (t.getTableNumber() == tableNumber) {
                        table = t;
                        break;
                    }
                }

                // If the table is found, execute the appropriate command
                if (table != null) {
                    switch (commandCode) {
                        case 'P': // Seat party
                            int partySize = Integer.parseInt(parts[1].substring(1));
                            table.seatParty(partySize);
                            break;
                        case 'O': // Place order
                            StringBuilder orderBuilder = new StringBuilder();
                            for (int i = 2; i < parts.length; i++) {
                                orderBuilder.append(parts[i]).append(" ");
                            }
                            table.placeOrder(orderBuilder.toString().trim(), menu);
                            break;
                        case 'S': // Serve
                            table.serve();
                            break;
                        case 'C': // Cleanup
                            table.cleanup();
                            break;
                        default:
                            System.out.println("Invalid command.");
                    }
                } else {
                    System.out.println("Table not found.");
                }
            }
    }
}
//FREEZE CODE END