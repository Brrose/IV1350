package MVC.startup;

import MVC.integration.Printer;
import MVC.integration.SystemCreator;
import MVC.view.View;
import MVC.controller.Controller;
import MVC.java.lang.String;
/**
 * Starts the program
 */

public class Main {

	private Printer printer;

	private SystemCreator systemCreator;

	private View view;

	private Controller controller;

	/**
	 * The main method that starts the entire application
	 * @param args The application does not take any command line parameters.
	 */

	public static void main(java.lang.String args) {
		SystemCreator creator = new SystemCreator();
		Printer printer = new Printer();
		Controller contr = new Controller(creator);
		View view = new View(contr);
	}


}
