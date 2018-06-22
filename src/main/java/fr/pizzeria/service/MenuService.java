package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.IPizzaDao;
import fr.pizzeria.model.PizzaMemDao;

public abstract class MenuService {
	public abstract void executeUC(Scanner sc, IPizzaDao pizzaMemDao) throws StockageException;
}
