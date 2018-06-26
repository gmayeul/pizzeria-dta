package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

public class PizzaMemDao implements IPizzaDao {
	
	/* Initialisation liste pizzas */
	private List<Pizza> pizzas;
	
	public PizzaMemDao() {
		pizzas = new ArrayList<Pizza>();
		pizzas.add(new Pizza(1, "PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza(2, "MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		pizzas.add(new Pizza(3, "REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza(4, "FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzas.add(new Pizza(5, "CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza(6 ,"SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		pizzas.add(new Pizza(7, "ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza(8, "IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
	}
	
	@Override
	public List<Pizza> findAllPizzas() {
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		pizzas.add(pizza);
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
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
	}

	@Override
	public void deletePizza(String codePizza) {
		for (int compteur = 0 ; compteur < pizzas.size() ; compteur++) {
			if (pizzas.get(compteur) == findPizzaByCode(codePizza)) {
				pizzas.remove(compteur);
			}
		}
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		int compteur = 0;
		while (compteur < pizzas.size()) {
			if (pizzas.get(compteur).getCode().equals(codePizza))
				return pizzas.get(compteur);
			compteur++;
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

	@Override
	public void populatePizzas() {
		throw new RuntimeException("Cette méthode n'est pas implémentée.");		
	}

	@Override
	public void exitPizzeria() {
		// ne fait rien
		
	}

}
