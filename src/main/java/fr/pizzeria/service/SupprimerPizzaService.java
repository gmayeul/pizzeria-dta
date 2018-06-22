package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.model.IPizzaDao;
import fr.pizzeria.model.PizzaMemDao;

public class SupprimerPizzaService extends MenuService {
	public SupprimerPizzaService() {
		super();
	}
	
	@Override
	public void executeUC(Scanner sc, IPizzaDao pizzaDao) throws DeletePizzaException {
		System.out.println("Veuillez choisir le code de la pizza à supprimer :");
		String codeDeletedPizza = sc.next();
		if (!pizzaDao.pizzaExists(codeDeletedPizza))
			throw new DeletePizzaException("Erreur : La pizza est introuvable.");
		pizzaDao.deletePizza(codeDeletedPizza);
	}
}
