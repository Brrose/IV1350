package controller;

import integration.Inventory;
import model.Item;
import model.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private Controller controller;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        controller = new Controller();
        inventory = new Inventory();
        controller.startSale();
    }

    @Test
    public void testScanItemAddNewItemToSale() {
        Item item = inventory.getItem("abc123");
        assertNotNull(item, "Item should exist in inventory");

        controller.scanItem("abc123"); // Scanna varan

        assertEquals(1, controller.getCurrentSale().items().size(), "Sale should contain one item");
    }

    @Test
    public void testScanItemIncreaseQuantityIfAlreadyInSale() {
        controller.scanItem("abc123");
        controller.scanItem("abc123");

        Item item = controller.getCurrentSale().items().stream()
                .filter(i -> i.id().equals("abc123"))
                .findFirst().orElse(null);

        assertNotNull(item, "Item should exist in sale");
        assertEquals(2, item.quantity(), "Item quantity should increase");
    }

    @Test
    public void testGetSaleTotal() {
        controller.scanItem("abc123");
        controller.scanItem("def456");

        String total = controller.getSaleTotal();
        assertNotNull(total);
        assertEquals("44.80", total, "Total price including VAT should be correctly calculated");
    }

    @Test
    public void testPay() {
        controller.scanItem("abc123");
        controller.pay(50f);

        assertEquals(50f, controller.getCurrentSale().cash(), "Cash should be updated correctly after payment");
        assertEquals(5.20f, controller.getCurrentSale().change(), "Change should be correctly calculated");
    }

    @Test
    public void testGetReceipt() {
        controller.scanItem("abc123");
        controller.pay(50f);

        String receipt = controller.getReceipt();
        assertTrue(receipt.contains("Total: 29.90"), "Receipt should contain correct total price");
        assertTrue(receipt.contains("Change: 20.10"), "Receipt should show correct change");
    }
}

