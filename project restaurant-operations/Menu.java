import java.util.*;
import java.io.*;

public class Menu {
    private MenuItem[] items;

    public Menu(Scanner scanner) {
        List<MenuItem> itemList = new ArrayList<>();

        // Read the first line to get the number of menu items
        String line = scanner.nextLine();
        String[] parts = line.trim().split(" ");
        int numMenuItems = Integer.parseInt(parts[0]);

        // Skip "Menu:" line if present
        if (parts.length > 1 && parts[1].contains("Menu:")) {
            // Do nothing, we're already on the correct line
        } else if (scanner.hasNextLine()) {
            // Read the next line if "Menu:" was not on the same line
            line = scanner.nextLine();
        }

        // Read menu items
        for (int i = 0; i < numMenuItems; i++) {
            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
                parts = line.trim().split("\\s+");

                String itemCode = parts[0];
                double price = Double.parseDouble(parts[parts.length - 1]);

                StringBuilder nameBuilder = new StringBuilder();
                for (int j = 1; j < parts.length - 1; j++) {
                    if (j > 1) {
                        nameBuilder.append(" ");
                    }
                    nameBuilder.append(parts[j]);
                }
                String name = nameBuilder.toString().replace('_', ' ');

                itemList.add(new MenuItem(itemCode, name, price));
            } else {
                break;
            }
        }

        items = itemList.toArray(new MenuItem[itemList.size()]);
    }

    public MenuItem getItemByCode(String code) {
        for (MenuItem item : items) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }
}
