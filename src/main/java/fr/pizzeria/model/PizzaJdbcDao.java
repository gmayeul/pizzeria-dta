package fr.pizzeria.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.utils.ConnectionProvider;

public class PizzaJdbcDao implements IPizzaDao {

	// code erreur MariaDB en cas d'erreur d'unicité
	final int ER_DUP_ERROR = 1062;

	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> pizzas = new ArrayList<>();
		PreparedStatement findAllPizzasSt = null;
		ResultSet myResultSet = null;
		try {
			findAllPizzasSt = ConnectionProvider.openConnection().prepareStatement("SELECT * FROM PIZZA;");
			myResultSet = findAllPizzasSt.executeQuery();
			while (myResultSet.next()) {
				pizzas.add(new Pizza(myResultSet.getString("CODE"), myResultSet.getString("LIBELLE"),
						myResultSet.getDouble("PRIX"), CategoriePizza.valueOf(myResultSet.getString("CATEGORIE"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (myResultSet != null)
				try {
					myResultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (findAllPizzasSt != null)
				try {
					findAllPizzasSt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			ConnectionProvider.closeConnection();
		}
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		PreparedStatement saveNewPizzaSt = null;
		try {
			saveNewPizzaSt = ConnectionProvider.openConnection()
					.prepareStatement("INSERT INTO PIZZA(CODE, LIBELLE, PRIX, CATEGORIE) VALUES (?, ?, ?, ?);");
			saveNewPizzaSt.setString(1, pizza.getCode());
			saveNewPizzaSt.setString(2, pizza.getLibelle());
			saveNewPizzaSt.setDouble(3, pizza.getPrix());
			saveNewPizzaSt.setString(4, pizza.getCategorie().name());
			saveNewPizzaSt.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ER_DUP_ERROR)
				throw new SavePizzaException("Une pizza utilise déjà ce code !");
		} finally {
			if (saveNewPizzaSt != null)
				try {
					saveNewPizzaSt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			ConnectionProvider.closeConnection();
		}

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		PreparedStatement updatePizzaSt = null;
		try {
			updatePizzaSt = ConnectionProvider.openConnection()
					.prepareStatement("UPDATE PIZZA SET CODE=?, LIBELLE=?, PRIX=?, CATEGORIE=? WHERE CODE=?;");
			updatePizzaSt.setString(1, pizza.getCode());
			updatePizzaSt.setString(2, pizza.getLibelle());
			updatePizzaSt.setDouble(3, pizza.getPrix());
			updatePizzaSt.setString(4, pizza.getCategorie().name());
			updatePizzaSt.setString(5, codePizza);
			updatePizzaSt.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ER_DUP_ERROR)
				throw new UpdatePizzaException("Une pizza utilise déjà ce code !");
		} finally {
			if (updatePizzaSt != null)
				try {
					updatePizzaSt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			ConnectionProvider.closeConnection();
		}

	}

	@Override
	public void deletePizza(String codePizza) {
		PreparedStatement deletePizzaSt = null;
		try {
			deletePizzaSt = ConnectionProvider.openConnection().prepareStatement("DELETE FROM PIZZA WHERE CODE=?;");
			deletePizzaSt.setString(1, codePizza);
			deletePizzaSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (deletePizzaSt != null)
				try {
					deletePizzaSt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			ConnectionProvider.closeConnection();
		}

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		Pizza foundPizza = null;
		PreparedStatement findPizzaByCodeSt = null;
		ResultSet myResultSet = null;
		try {
			findPizzaByCodeSt = ConnectionProvider.openConnection()
					.prepareStatement("SELECT * FROM PIZZA WHERE CODE=?;");
			findPizzaByCodeSt.setString(1, codePizza);
			myResultSet = findPizzaByCodeSt.executeQuery();
			if (myResultSet.next())
				foundPizza = new Pizza(myResultSet.getString("CODE"), myResultSet.getString("LIBELLE"),
						myResultSet.getDouble("PRIX"), CategoriePizza.valueOf(myResultSet.getString("CATEGORIE")));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (myResultSet != null)
				try {
					myResultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (findPizzaByCodeSt != null)
				try {
					findPizzaByCodeSt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			ConnectionProvider.closeConnection();
		}
		return foundPizza;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		return findPizzaByCode(codePizza) != null;
	}

	@Override
	public void populatePizzas() {
		PreparedStatement truncatePizzasSt = null;
		List<Pizza> pizzasInit = new ArrayList<>();
		pizzasInit.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzasInit.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		pizzasInit.add(new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		pizzasInit.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzasInit.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzasInit.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		pizzasInit.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		pizzasInit.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
		try {
			truncatePizzasSt = ConnectionProvider.openConnection().prepareStatement("TRUNCATE TABLE PIZZA;");
			truncatePizzasSt.executeUpdate();

			for (Pizza pizza : pizzasInit)
				saveNewPizza(pizza);

		} catch (SavePizzaException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (truncatePizzasSt != null)
				try {
					truncatePizzasSt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			ConnectionProvider.closeConnection();
		}
	}

	@Override
	public void exitPizzeria() {
		// ne fait rien
		
	}

}
