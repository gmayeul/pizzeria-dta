package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PizzaMemDao implements IPizzaDao {
	
	/* Initialisation liste pizzas */
	public List<Pizza> pizzas;
	
	public PizzaMemDao() {
		pizzas = new ArrayList<Pizza>();
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L'orientale", 13.50));
		pizzas.add(new Pizza("IND", "L'indienne", 14.00));
	}
	
	@Override
	public List<Pizza> findAllPizzas() {
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		pizzas.add(pizza);
		displayPizzaList();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		if (pizzaExists(codePizza)) {
			boolean found = false;
			int compteur = 0;
			while (!found && compteur < pizzas.size()) {
				if (pizzas.get(compteur) == findPizzaByCode(codePizza)) {
					found = true;
					pizzas.set(compteur, pizza);
					break;
				}
				compteur++;
			}
			displayPizzaList();
		}
		else
			System.out.println("Pizza introuvable !");
	}

	@Override
	public void deletePizza(String codePizza) {
		if (pizzaExists(codePizza)) {
			for (int compteur = 0 ; compteur < pizzas.size() ; compteur++) {
				if (pizzas.get(compteur) == findPizzaByCode(codePizza)) {
					pizzas.remove(compteur);
				}
			}
		}
		else
			System.out.println("Pizza introuvable !");
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		if (pizzaExists(codePizza)) {
			int compteur = 0;
			while (compteur < pizzas.size()) {
				if (pizzas.get(compteur).getCode().equals(codePizza))
					return pizzas.get(compteur);
				compteur++;
			}
		}
		else {
			System.out.println("Pizza introuvable !");
		}
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		boolean found = false;
		int compteur = 0;
		while (!found && compteur < pizzas.size()) {
			if (pizzas.get(compteur).getCode().equals(codePizza)) {
				found = true;
				break;
			}
			compteur++;
		}
		return found;
	}
	
	public void displayPizzaList() {
		for (int i = 0; i < pizzas.size(); i++){
			Pizza myPizza = pizzas.get(i);
			System.out.println(myPizza.getCode() 
					+ " -> " 
					+ myPizza.getLibelle() 
					+ " (" 
					+ myPizza.getPrix() 
					+ "€)"
					);
		}
	}

}
