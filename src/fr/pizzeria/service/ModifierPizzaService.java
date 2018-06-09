package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDao;

public class ModifierPizzaService extends MenuService {
	public ModifierPizzaService() {
		super();
	}
	
	@Override
	public void executeUC(Scanner sc, PizzaMemDao pizzaMemDao) throws UpdatePizzaException {
		System.out.println("Veuillez choisir le code de la pizza à modifier :");
		String oldCode = sc.next();
		if (!pizzaMemDao.pizzaExists(oldCode))
			throw new UpdatePizzaException("Erreur : Pizza introuvable.");
		System.out.println("Veuillez saisir le nouveau code :");
		String newCode = sc.next();
		if (pizzaMemDao.pizzaExists(newCode))
			throw new UpdatePizzaException("Erreur : Cette pizza existe déjà.");
		System.out.println("Veuillez saisir le nouveau nom (sans espace) :");
		String newLibelle = sc.next();
		System.out.println("Veuillez saisir le nouveau prix :");
		double newPrix = sc.nextDouble();
		if (newPrix < 0)
			throw new UpdatePizzaException("Erreur : Le prix saisi est inférieur à 0.");
		pizzaMemDao.updatePizza(oldCode, new Pizza(newCode, newLibelle, newPrix));
	}
}
