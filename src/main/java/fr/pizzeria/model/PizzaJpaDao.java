package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.utils.EmfProvider;

public class PizzaJpaDao implements IPizzaDao {

	public PizzaJpaDao() {
	}

	@Override
	public List<Pizza> findAllPizzas() {
		EntityManager em = EmfProvider.getInstance().createEntityManager();
		List<Pizza> result = new ArrayList<>();
		try {
			TypedQuery<Pizza> findAllPizzasQuery = em.createQuery("SELECT p FROM Pizza p", Pizza.class);
			result = findAllPizzasQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return result;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		EntityManager em = EmfProvider.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Pizza newPizza = new Pizza();
			newPizza.setCode(pizza.getCode());
			newPizza.setLibelle(pizza.getLibelle());
			newPizza.setPrix(pizza.getPrix());
			newPizza.setCategorie(pizza.getCategorie());
			em.persist(newPizza);
			et.commit();
		} catch (PersistenceException e) {
			et.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		if (pizzaExists(codePizza)) {
			EntityManager em = EmfProvider.getInstance().createEntityManager();
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();
				Pizza pizzaToUpdate = em.find(Pizza.class, findPizzaByCode(codePizza).getId());
				pizzaToUpdate.setCode(pizza.getCode());
				pizzaToUpdate.setLibelle(pizza.getLibelle());
				pizzaToUpdate.setPrix(pizza.getPrix());
				pizzaToUpdate.setCategorie(pizza.getCategorie());
				em.persist(pizzaToUpdate);
				et.commit();
			} catch (PersistenceException e) {
				et.rollback();
				e.printStackTrace();
			} finally {
				em.close();
			}
		} else
			throw new UpdatePizzaException("Pizza introuvable !");

	}

	@Override
	public void deletePizza(String codePizza) {
		EntityManager em = EmfProvider.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Pizza pizzaToDelete = em.find(Pizza.class, findPizzaByCode(codePizza).getId());
			em.remove(pizzaToDelete);
			et.commit();
		} catch (PersistenceException e) {
			et.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		EntityManager em = EmfProvider.getInstance().createEntityManager();
		Pizza foundPizza = null;
		try {
			TypedQuery<Pizza> findPizzaByCodeQuery = em.createQuery("SELECT p FROM Pizza p WHERE p.code = :code",
					Pizza.class);
			findPizzaByCodeQuery.setParameter("code", codePizza);
			foundPizza = findPizzaByCodeQuery.getResultList().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return foundPizza;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		return findPizzaByCode(codePizza) != null;
	}

	@Override
	public void populatePizzas() {
		EntityManager em = EmfProvider.getInstance().createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			et.begin();
			Pizza testPizza = new Pizza();
			testPizza.setCode("TEST");
			testPizza.setLibelle("La test");
			testPizza.setPrix(1.54);
			testPizza.setCategorie(CategoriePizza.SANS_VIANDE);
			em.persist(testPizza);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	@Override
	public void exitPizzeria() {
		EmfProvider.getInstance().close();
	}
}
