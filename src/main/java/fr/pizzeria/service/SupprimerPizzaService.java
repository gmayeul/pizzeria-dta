package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.model.PizzaMemDao;

public class SupprimerPizzaService extends MenuService {
	public SupprimerPizzaService() {
		super();
	}
	
	@Override
	public void executeUC(Scanner sc, PizzaMemDao pizzaMemDao) throws DeletePizzaException {
		System.out.println("Veuillez choisir le code de la pizza à supprimer :");
		String codeDeletedPizza = sc.next();
		if (!pizzaMemDao.pizzaExists(codeDeletedPizza))
			throw new DeletePizzaException("Erreur : La pizza est introuvable.");
		pizzaMemDao.deletePizza(codeDeletedPizza);
	}
}
