package fr.pizzeria.console;

import fr.pizzeria.factory.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		MenuServiceFactory menuServiceFactory = new MenuServiceFactory();
		menuServiceFactory.launchMenu();
	}
}
