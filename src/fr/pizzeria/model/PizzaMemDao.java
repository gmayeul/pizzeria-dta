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

	@Override
	public Pizza[] findAllPizzas() {
		return pizzas;
	}

	public void saveNewPizza(Pizza pizza) {
		pizzas[pizzas.length-1] = pizza;
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		boolean found = false;
		int compteur = 0;
		while (!found && compteur < pizzas.length) {
			if (pizzas[compteur].code.equals(codePizza)) {
				found = true;
				pizzas[compteur] = pizza;
				break;
			}
			compteur++;
		}
	}

	@Override
	public void deletePizza(String codePizza) {
		Pizza pizza = findPizzaByCode(codePizza);
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		boolean found = false;
		int compteur = 0;
		while (!found && compteur < pizzas.length) {
			if (pizzas[compteur].code.equals(codePizza)) {
				found = true;
				return pizzas[compteur];
			}
			compteur++;
		}
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		boolean found = false;
		int compteur = 0;
		while (!found && compteur < pizzas.length) {
			if (pizzas[compteur].code.equals(codePizza)) {
				found = true;
				break;
			}
			compteur++;
		}
		return found;
	}
	
	public void displayPizzaList() {
		for (int i = 0; i < pizzas.length; i++){
			Pizza myPizza = pizzas[i];
			System.out.println(myPizza.code 
					+ " -> " 
					+ myPizza.libelle 
					+ " (" 
					+ myPizza.prix 
					+ "€)"
					);
		}
	}

}
