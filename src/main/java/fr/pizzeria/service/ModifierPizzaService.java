package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {
	public ModifierPizzaService() {
		super();
	}

	@Override
	public void executeUC(Scanner sc, IPizzaDao pizzaDao) throws UpdatePizzaException {
		System.out.println("Veuillez choisir le code de la pizza � modifier :");
		String oldCode = sc.next();
		if (!pizzaDao.pizzaExists(oldCode))
			throw new UpdatePizzaException("Erreur : Pizza introuvable.");
		
		System.out.println("Veuillez saisir le nouveau nom (sans espace) :");
		String newLibelle = sc.next();
		
		System.out.println("Veuillez saisir le nouveau prix :");
		double newPrix = sc.nextDouble();
		if (newPrix < 0)
			throw new UpdatePizzaException("Erreur : Le prix saisi est inf�rieur � 0.");
		
		System.out.println("Veuillez saisir la cat�gorie :");
		int categorieNumber = 1;
		for (CategoriePizza categorieChoice : CategoriePizza.values()) {
			System.out.println(categorieNumber + ": " + categorieChoice.getNom());
			categorieNumber++;
		}
		categorieNumber = sc.nextInt();
		CategoriePizza newCategorie = null;
		switch (categorieNumber) {
		case 1:
			newCategorie = CategoriePizza.VIANDE;
			break;
		case 2:
			newCategorie = CategoriePizza.POISSON;
			break;
		case 3:
			newCategorie = CategoriePizza.SANS_VIANDE;
			break;
		default:
			newCategorie = CategoriePizza.SANS_VIANDE;
		}
		pizzaDao.updatePizza(oldCode, new Pizza(oldCode, newLibelle, newPrix, newCategorie));
	}
}
