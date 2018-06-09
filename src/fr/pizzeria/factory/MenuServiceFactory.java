package fr.pizzeria.factory;

import java.util.Scanner;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.PizzaMemDao;
import fr.pizzeria.service.AjouterPizzaService;
import fr.pizzeria.service.ListerPizzasService;
import fr.pizzeria.service.ModifierPizzaService;
import fr.pizzeria.service.SupprimerPizzaService;

public class MenuServiceFactory {
	Scanner sc;
	PizzaMemDao pizzaMemDao;
	ListerPizzasService listerPizzasService;
	AjouterPizzaService ajouterPizzaService;
	ModifierPizzaService modifierPizzaService;
	SupprimerPizzaService supprimerPizzaService;
	
	public MenuServiceFactory() {
		sc = new Scanner(System.in);
		pizzaMemDao = new PizzaMemDao();
		listerPizzasService = new ListerPizzasService();
		ajouterPizzaService = new AjouterPizzaService();
		modifierPizzaService = new ModifierPizzaService();
		supprimerPizzaService = new SupprimerPizzaService();
	}
	
	public void displayMenu() {
		System.out.println(
			"***** Pizzeria Administration *****\n"
			+ "1. Lister les pizzas\n"
			+ "2. Ajouter une nouvelle pizza\n"
			+ "3. Mettre à jour une pizza\n"
			+ "4. Supprimer une pizza\n"
			+ "99. Sortir"
		);
	}
	
	public void launchMenu() throws StockageException {
		displayMenu();
		useService(sc.nextInt());
	}

	private void useService(int choice) throws StockageException {
		switch (choice) {
		case 1:
			listerPizzasService.executeUC(sc, pizzaMemDao);
			launchMenu();
			break;
		case 2:
			ajouterPizzaService.executeUC(sc, pizzaMemDao);
			launchMenu();
			break;
		case 3:
			listerPizzasService.executeUC(sc, pizzaMemDao);
			modifierPizzaService.executeUC(sc, pizzaMemDao);
			launchMenu();
			break;
		case 4:
			listerPizzasService.executeUC(sc, pizzaMemDao);
			supprimerPizzaService.executeUC(sc, pizzaMemDao);
			launchMenu();
			break;
		case 99:
			System.exit(0);
			break;
		default:
			System.out.println("Veuillez saisir un choix valide.");
			launchMenu();
			break;
		}
	}
	
}
