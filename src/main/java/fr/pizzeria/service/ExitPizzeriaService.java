package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.IPizzaDao;

public class ExitPizzeriaService extends MenuService {

	@Override
	public void executeUC(Scanner sc, IPizzaDao pizzaDao) throws StockageException {
		pizzaDao.exitPizzeria();
		
	}

}
