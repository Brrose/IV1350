package controller;

import dto.ItemDTO;
import dto.SaleDTO;
import model.Item;
import model.Register;
import model.Sale;
import integration.Inventory;

public class Controller {

    private Sale sale;
    private Inventory inventory;
    private Register register;

    public Controller() {
        this.inventory = new Inventory();
        this.register = new Register();
    }

    public void startSale() {
        this.sale = new Sale();
    }
    
    public ItemDTO scanItem(String itemId) {
        Item item;
        if (sale.isItemInSale(itemId)) {
            // öka kvantitet
            item = sale.getItemFromSale(itemId);
            item.increaseQuantity(1);
        }
        else {
            if (inventory.isValidItem(itemId)) {
                // lägg till i sale
                ItemDTO itemDTO = inventory.getItem(itemId);
                item = new Item(itemDTO);
                sale.addItemToSale(item);
            }
            else {
                // returnera error
                return null;
            }
        }
        // öka runningtotal
        sale.increaseTotalPrice(item.getPrice());
        sale.calculateTotalVat(item.getVat(), item.getPrice());
        // returnera data
        return item.generateDTO();
    }

    public String getSaleTotal() {
        // returnera totalprice incl VAT
        return String.format("%.2f", sale.getTotalPrice());
    }

    public void pay(float amountPaid) {
        sale.setCash(amountPaid);
        register.updateTotal(sale.getCash());
        // calculate change och returnera change
        sale.setChange(sale.getCash() - sale.getTotalPrice());
    }

    public String getReceipt() {
        return sale.createReceipt();
    }

    public SaleDTO getCurrentSale() {
        return this.sale.generateDTO();
    }
}
