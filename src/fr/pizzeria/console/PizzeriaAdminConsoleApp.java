package fr.pizzeria.console;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.factory.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		MenuServiceFactory menuServiceFactory = new MenuServiceFactory();
		try {
			menuServiceFactory.launchMenu();
		} catch (StockageException e) {
			System.out.println(e.getMessage());
		}
	}
}
