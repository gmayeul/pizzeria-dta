package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class PizzaMemDao implements IPizzaDao {
	
	/* Initialisation liste pizzas */
	public static Pizza[] pizzas = new Pizza[] {
		new Pizza("PEP", "Pépéroni", 12.50),
		new Pizza("MAR", "Margherita", 14.00),
		new Pizza("REIN", "La Reine", 11.50),
		new Pizza("FRO", "La 4 fromages", 12.00),
		new Pizza("CAN", "La cannibale", 12.50),
		new Pizza("SAV", "La savoyarde", 13.00),
		new Pizza("ORI", "L'orientale", 13.50),
		new Pizza("IND", "L'indienne", 14.00)
	};
	ArrayList<Pizza> pizzasList = new ArrayList<Pizza>(Arrays.asList(pizzas));

	@Override
	public ArrayList<Pizza> findAllPizzas() {
		return pizzasList;
	}

	public void saveNewPizza(Pizza pizza) {
		pizzasList.add(pizza);
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		Pizza oldPizza = findPizzaByCode(codePizza);
		oldPizza.code = pizza.code;
		oldPizza.libelle = pizza.libelle;
		oldPizza.prix = pizza.prix;
	}

	@Override
	public void deletePizza(String codePizza) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		Iterator it = pizzasList.iterator();
		while (it.hasNext()) {
			Pizza currentPizza = (Pizza) it.next();
			if (currentPizza.code == codePizza) {
				return currentPizza;
			}
		}
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		return false;
	}

}
