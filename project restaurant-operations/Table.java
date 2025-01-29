public class Table {
    enum TableStatus { IDLE, SEATED, ORDERED, SERVED }

    private int tableId;
    private int capacity;
    private int partySize;
    private TableStatus status;
    private Order order;

    public Table(int tableId, int capacity) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.status = TableStatus.IDLE;
        this.partySize = 0;
        this.order = null;
    }

    public int getTableNumber() {
        return tableId;
    }

    public boolean seatParty(int partySize) {
        if (status != TableStatus.IDLE) {
            System.out.println("Table " + tableId + " already occupied!");
            return false;
        }
        if (partySize > capacity) {
            System.out.println("Sorry, max " + capacity + " seats in Table " + tableId + "!");
            return false;
        }
        this.partySize = partySize;
        this.status = TableStatus.SEATED;
        System.out.println("Party of " + partySize + " assigned to Table " + tableId);
        return true;
    }

    public boolean placeOrder(String orderBuilder, Menu menu) {
        if (status == TableStatus.IDLE) {
            System.out.println("Do not allow orders from table with no party assigned to it.");
            return false;
        }

        if (order == null) {
            order = new Order();
        }

        String[] itemCodes = orderBuilder.split(" ");
        int validItemCount = 0;
        for (String code : itemCodes) {
            MenuItem item = menu.getItemByCode(code);
            if (item != null) {
                order.addItem(item);
                validItemCount++;
            } else {
                System.out.println("No item with code " + code);
            }
        }

        if (validItemCount > 0) {
            if (status == TableStatus.SEATED || status == TableStatus.ORDERED) {
                status = TableStatus.ORDERED;
                System.out.println(validItemCount + " items ordered for Table " + tableId);
            } else if (status == TableStatus.SERVED) {
                status = TableStatus.ORDERED;
                System.out.println(validItemCount + " additional items ordered for Table " + tableId);
            }
        }
        return true;
    }

    public boolean serve() {
        if (status == TableStatus.IDLE) {
            System.out.println("Cannot serve food to an empty table!");
            return false;
        }
        if (status != TableStatus.ORDERED) {
            System.out.println("Order not placed at Table " + tableId + " yet!");
            return false;
        }
        status = TableStatus.SERVED;
        System.out.println("Food served in table " + tableId);
        return true;
    }

    public void cleanup() {
        if (status == TableStatus.IDLE) {
            System.out.println("Cannot checkout an empty table!");
            return;
        } else if (status == TableStatus.SEATED || status == TableStatus.ORDERED) {
            System.out.println("Food not served for Table " + tableId + " yet!");
            return;
        } else if (status == TableStatus.SERVED) {
            System.out.println("Table " + tableId + " is closed. Here is the bill.\n");
            printReceipt();
            // Reset the table
            status = TableStatus.IDLE;
            partySize = 0;
            order = null;
        }
    }

    private void printReceipt() {
        System.out.println("Receipt Table# " + tableId + " Party " + partySize);
        double total = 0.0;
        for (MenuItem item : order.getItems()) {
            System.out.printf("%-10s %-20s %6.2f%n", item.getCode(), item.getName().replace('_', ' '), item.getPrice());
            total += item.getPrice();
        }
        System.out.printf("%32s %6.2f%n", "Total", total);
    }

    public boolean isOccupied() {
        return status != TableStatus.IDLE;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPartySize() {
        return partySize;
    }

    public TableStatus getStatus() {
        return status;
    }

    public Order getOrder() {
        return order;
    }
}
