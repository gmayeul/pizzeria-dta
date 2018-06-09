package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDao;

public class AjouterPizzaService extends MenuService {
	public AjouterPizzaService() {
		super();
	}
	
	@Override
	public void executeUC(Scanner sc, PizzaMemDao pizzaMemDao) throws SavePizzaException {
		System.out.println("Veuillez saisir le code : ");
		String code = sc.next();
		System.out.println("Veuillez saisir le nom (sans espace) : ");
		String libelle = sc.next();
		System.out.println("Veuillez saisir le prix : ");
		double prix = sc.nextDouble();
		if (prix < 0)
			throw new SavePizzaException("Erreur : Le prix saisi est inférieur à 0.");
		Pizza newPizza = new Pizza(code, libelle, prix);
		pizzaMemDao.saveNewPizza(newPizza);
	}
}
