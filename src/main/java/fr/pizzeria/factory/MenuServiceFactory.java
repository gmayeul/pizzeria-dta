package fr.pizzeria.factory;

import java.util.Scanner;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.IPizzaDao;
import fr.pizzeria.model.PizzaJdbcDao;
import fr.pizzeria.service.AjouterPizzaService;
import fr.pizzeria.service.ListerPizzasService;
import fr.pizzeria.service.ModifierPizzaService;
import fr.pizzeria.service.PeuplerPizzasService;
import fr.pizzeria.service.SupprimerPizzaService;

public class MenuServiceFactory {
	Scanner sc;
	IPizzaDao pizzaDao;
	ListerPizzasService listerPizzasService;
	AjouterPizzaService ajouterPizzaService;
	ModifierPizzaService modifierPizzaService;
	SupprimerPizzaService supprimerPizzaService;
	PeuplerPizzasService peuplerPizzasService;

	public MenuServiceFactory() {
		sc = new Scanner(System.in);
		// pizzaDao = new PizzaMemDao();
		pizzaDao = new PizzaJdbcDao();
		listerPizzasService = new ListerPizzasService();
		ajouterPizzaService = new AjouterPizzaService();
		modifierPizzaService = new ModifierPizzaService();
		supprimerPizzaService = new SupprimerPizzaService();
		peuplerPizzasService = new PeuplerPizzasService();
	}

	public void displayMenu() {
		System.out.println("***** Pizzeria Administration *****\n" + "1. Lister les pizzas\n"
				+ "2. Ajouter une nouvelle pizza\n" + "3. Mettre à jour une pizza\n" + "4. Supprimer une pizza\n"
				+ "5. Peupler la base (JDBC seulement)\n" + "99. Sortir");
	}

	public void launchMenu() {
		displayMenu();
		useService(sc.nextInt());
	}

	private void useService(int choice) {

		switch (choice) {
		case 1:
			listerPizzasService.executeUC(sc, pizzaDao);
			launchMenu();

			break;
		case 2:
			try {
				ajouterPizzaService.executeUC(sc, pizzaDao);
			} catch (StockageException e) {
				System.out.println(e.getMessage());
			} finally {
				launchMenu();
			}
			break;
		case 3:
			try {
				listerPizzasService.executeUC(sc, pizzaDao);
				modifierPizzaService.executeUC(sc, pizzaDao);
			} catch (StockageException e) {
				System.out.println(e.getMessage());
			} finally {
				launchMenu();
			}
			break;
		case 4:
			try {
				listerPizzasService.executeUC(sc, pizzaDao);
				supprimerPizzaService.executeUC(sc, pizzaDao);
				launchMenu();
			} catch (StockageException e) {
				System.out.println(e.getMessage());
			} finally {
				launchMenu();
			}
			break;
		case 5:
			try {
				peuplerPizzasService.executeUC(sc, pizzaDao);
				launchMenu();
			} catch (StockageException e) {
				System.out.println(e.getMessage());
			} finally {
				launchMenu();
			}
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
