package fr.pizzeria.service;

import java.util.List;
import java.util.Scanner;

import fr.pizzeria.model.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService {
	public ListerPizzasService() {
		super();
	}

	@Override
	public void executeUC(Scanner sc, IPizzaDao pizzaDao) {
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		if (pizzas.isEmpty())
			System.out.println("Aucune pizza dans la liste...");
		else {
			for (Pizza pizza : pizzas) {
				if (pizza != null)
					System.out.println(pizza.toString());
			}
		}
	}
}
