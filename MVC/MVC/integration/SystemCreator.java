package MVC.integration;

public class SystemCreator {

	private AccountingSystem accountingSystem;

	private InventorySystem inventorySystem;

	private DiscountSystem discountSystem;

	public SystemCreator SystemCreator() {
		AccountingSystem accountingSystem = new AccountingSystem();
		InventorySystem inventorySystem = new InventorySystem();
		DiscountSystem discountSystem = new DiscountSystem();
		return null;
	}

	public AccountingSystem getAccountingSystem() {
		return null;
	}

	public InventorySystem getInventorySystem() {
		return null;
	}

	public DiscountSystem getDiscountSystem() {
		return null;
	}

}
