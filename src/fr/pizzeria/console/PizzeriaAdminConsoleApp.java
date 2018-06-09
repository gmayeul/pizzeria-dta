package fr.pizzeria.console;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.factory.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		MenuServiceFactory menuServiceFactory = new MenuServiceFactory();
		try {
			menuServiceFactory.launchMenu();
		} catch (StockageException e) {
			e.printStackTrace();
			System.out.println("Euh ouais y a une erreur là !");
		}
	}
}
