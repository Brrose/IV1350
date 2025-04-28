package MVC.controller;

import MVC.model.Sale;
import MVC.integration.InventorySystem;
import MVC.integration.DiscountSystem;
import MVC.integration.Printer;
import MVC.model.Register;
import MVC.integration.SystemCreator;
import MVC.DTO.ItemDetailsDTO;
import MVC.java.lang.String;

public class Controller {

	private Sale sale;

	private InventorySystem inventorySystem;

	private DiscountSystem discountSystem;

	private Printer printer;

	private Register register;

	public Controller Controller(SystemCreator creator) {
		return null;
	}

	public void startSale() {

	}

	public ItemDetailsDTO enterItem(int itemID, int quantity) {
		return null;
	}

	public java.lang.String displayError() {
		return null;
	}

	public ItemDetailsDTO increaseItemQuantity() {
		return null;
	}

	public void endSale() {

	}

	public void enterPayment() {

	}

	public int enterCustomerID(int customerID) {
		return 0;
	}

}
