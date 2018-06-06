package fr.pizzeria.model;

import java.util.ArrayList;

public interface IPizzaDao {
	public Pizza[] findAllPizzas();
	public void saveNewPizza(Pizza pizza);
	public void updatePizza(String codePizza, Pizza pizza);
	public void deletePizza(String codePizza);
	public Pizza findPizzaByCode(String codePizza);
	public boolean pizzaExists(String codePizza);
}
