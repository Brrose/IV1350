package view;

import controller.Controller;
import dto.ItemDTO;
import dto.SaleDTO;

/**
 * The {@code View} class represents the user interface. It imitates interactions with the system,
 * like starting a sale, scanning items, paying and printing the receipt.
 */
public class View {
    
    private Controller controller;

    /**
     * Creates a new {@code View} instance witha  reference to the controller.
     * @param controller The {@link Controller} that handles the program's logic.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Simulates a complete purchase, including starting a sale, scanning items, printing the total cost, making a payment and printing the receipt.
     */
    public void samplePurchase() {
        controller.startSale();
        
        scanItem("abc123", 2);
        
        scanItem("def456", 1);
        
        System.out.println("End sale: \nTotal cost (incl VAT): " + controller.getSaleTotal() + "\n");
        
        controller.pay(100);
        
        String receipt = controller.getReceipt();
        System.out.println(receipt);
    }
    
    /**
     * Simulates scanning a specific quantity of an item.
     * @param itemID The id of the item being scanned.
     * @param quantity The number of times to scan the item.
     */
    private void scanItem (String itemID, int quantity) {
        for (int i = 0; i < quantity; i++) {
            ItemDTO item = controller.scanItem(itemID);
            printScannedItem(item);
        }
    }

    /**
     * Prints the details of a scanned item, or displays an error if the item is invalid.
     * @param item The {@link ItemDTO} to display.
     */
    private void printScannedItem(ItemDTO item) {
        if (item == null) {
            System.out.println("Item identifier is invalid!");
        } else {
            SaleDTO sale = controller.getCurrentSale();
            System.out.println("Add 1 item with item id " + item.id() + ":\n" + 
            "Item ID: " + item.id() + "\n" + "Item name: " + item.name() + 
            "\n" + "Item cost: " + String.format("%.2f",item.price()) + " SEK\n" + "VAT: " + 
            String.format("%.0f",item.vat()) + "% \r\n" + "Item description: " + item.description() + "\n" + 
            "Total cost (incl VAT): " + String.format("%.2f", sale.totalPrice()) + " SEK \n" + "Total VAT: " + 
            String.format("%.2f", sale.totalVAT()) + " SEK\n");
        }
    }
}