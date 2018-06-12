package fr.pizzeria.service;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.PizzaMemDao;

public class AjouterPizzaServiceTest {

	private AjouterPizzaService myService;
	private PizzaMemDao pizzaMemDao;
	private Scanner scanner;

	@Rule
	public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

	@Before
	public void setUp() {
		myService = new AjouterPizzaService();
		pizzaMemDao = new PizzaMemDao();
		scanner = new Scanner(System.in);
	}

	@Test
	public void testExecuteUC() {
		systemInMock.provideLines("HAW", "La hawaïenne", "14.00", "1");
		try {
			myService.executeUC(scanner, pizzaMemDao);
			assertNotNull(pizzaMemDao.findPizzaByCode("HAW"));
		} catch (SavePizzaException e) {
			fail("Le test a échoué.");
		}
	}

	@Test(expected = SavePizzaException.class)
	public void testExecuteUCExceptionPrixInvalide() throws SavePizzaException {
		systemInMock.provideLines("HAW", "La hawaïenne", "wesh alors", "1");
		myService.executeUC(scanner, pizzaMemDao);
	}

	@Test(expected = SavePizzaException.class)
	public void testExecuteUCExceptionPrixNegatif() throws SavePizzaException {
		systemInMock.provideLines("HAW", "La hawaïenne", "-8", "1");
		myService.executeUC(scanner, pizzaMemDao);
	}
}
