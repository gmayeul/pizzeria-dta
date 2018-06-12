package fr.pizzeria.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PizzaMemDaoTest {
	
	private PizzaMemDao myPizzaMemDao;
	
	@Before
	public void setUp() {
		myPizzaMemDao = new PizzaMemDao();
	}
	
	@Test
	public void testPizzaMemDao() {
		// la liste initiale de pizzas ne doit pas être vide
		assertFalse(myPizzaMemDao.findAllPizzas().isEmpty());
	}

	@Test
	public void testFindAllPizzas() {
		// initialement, on doit avoir 8 pizzas dans la liste
		assertEquals(myPizzaMemDao.findAllPizzas().size(), 8);
	}

	@Test
	public void testSaveNewPizza() {
		// avant, la liste contient 8 pizzas
		assertEquals(myPizzaMemDao.findAllPizzas().size(), 8);
		Pizza pizza = new Pizza("HAW", "La hawaïenne", 13.5, CategoriePizza.VIANDE);
		
		// après, la liste doit en contenir 9
		myPizzaMemDao.saveNewPizza(pizza);
		assertEquals(myPizzaMemDao.findAllPizzas().size(), 9);
	}

	@Test
	public void testUpdatePizza() {
		// avant, "La hawaïenne" n'est pas dans la liste
		assertFalse(myPizzaMemDao.pizzaExists("HAW"));
		
		// après, elle doit s'y trouver
		Pizza pizza = new Pizza("HAW", "La hawaïenne", 13.5, CategoriePizza.VIANDE);
		myPizzaMemDao.updatePizza("REIN", pizza);
		assertTrue(myPizzaMemDao.pizzaExists("HAW"));
	}

	@Test
	public void testDeletePizza() {
		// avant, la liste contient 8 pizzas
		assertEquals(myPizzaMemDao.findAllPizzas().size(), 8);
		
		// après, la liste doit en contenir 7
		myPizzaMemDao.deletePizza("REIN");
		assertEquals(myPizzaMemDao.findAllPizzas().size(), 7);
	}

	@Test
	public void testFindPizzaByCode() {
		assertNotNull(myPizzaMemDao.findPizzaByCode("PEP"));
		assertNull(myPizzaMemDao.findPizzaByCode("Yvette Horner"));
		assertNull(myPizzaMemDao.findPizzaByCode(""));
		assertNull(myPizzaMemDao.findPizzaByCode(null));
	}

	@Test
	public void testPizzaExists() {
		assertTrue(myPizzaMemDao.pizzaExists("PEP"));
		assertFalse(myPizzaMemDao.pizzaExists("Yvette Horner"));
		assertFalse(myPizzaMemDao.pizzaExists(""));
		assertFalse(myPizzaMemDao.pizzaExists(null));
	}

}
