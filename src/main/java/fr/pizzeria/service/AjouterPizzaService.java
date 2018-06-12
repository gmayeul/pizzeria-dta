package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDao;

public class AjouterPizzaService extends MenuService {
	public AjouterPizzaService() {
		super();
	}
	
	@Override
	public void executeUC(Scanner sc, PizzaMemDao pizzaMemDao) throws SavePizzaException {
		System.out.println("Veuillez saisir le code : ");
		String code = sc.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) : ");
		String libelle = sc.nextLine();
		System.out.println("Veuillez saisir le prix : ");
		String sPrix = sc.nextLine();
		double prix;
		try {
			prix = Double.parseDouble(sPrix);
		} catch (NumberFormatException e) {
			throw new SavePizzaException();
		}
		if (prix < 0)
			throw new SavePizzaException("Erreur : Le prix saisi est inférieur à 0.");
		System.out.println("Veuillez saisir la catégorie :");
		int categorieNumber = 1;
		for (CategoriePizza categorieChoice : CategoriePizza.values()) {
			System.out.println(categorieNumber + ": " + categorieChoice.getNom());
			categorieNumber++;
		}
		categorieNumber = sc.nextInt();
		CategoriePizza categorie = null;
		switch (categorieNumber) {
		case 1:
			categorie = CategoriePizza.VIANDE;
			break;
		case 2:
			categorie = CategoriePizza.POISSON;
			break;
		case 3:
			categorie = CategoriePizza.SANS_VIANDE;
			break;	
		default:
			categorie = CategoriePizza.SANS_VIANDE;
		}
		Pizza newPizza = new Pizza(code, libelle, prix, categorie);
		pizzaMemDao.saveNewPizza(newPizza);
	}
}
