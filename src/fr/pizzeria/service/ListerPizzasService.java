package fr.pizzeria.service;

import java.util.List;
import java.util.Scanner;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDao;

public class ListerPizzasService extends MenuService {
	public ListerPizzasService() {
		super();
	}

	@Override
	public void executeUC(Scanner sc, PizzaMemDao pizzaMemDao) {
		List<Pizza> pizzas = pizzaMemDao.findAllPizzas();
		for (Pizza pizza : pizzas) {
			if (pizza != null)
				System.out.println(pizza.toString());
		}
	}
}
