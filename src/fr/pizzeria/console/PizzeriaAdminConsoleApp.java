package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDao;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		PizzaMemDao dao = new PizzaMemDao();
		Iterator<Pizza> it = dao.findAllPizzas().iterator();
		
		int choice = 0;	//dernier choix effectué
		Scanner sc = new Scanner(System.in);
		
		while (choice != 99) {
			System.out.println("***** Pizzeria Administration *****\n" + "1. Lister les pizzas\n"
					+ "2. Ajouter une nouvelle pizza\n" + "3. Mettre à jour une pizza\n" + "4. Supprimer une pizza\n"
					+ "99. Sortir");

			choice = sc.nextInt();
			switch (choice) {
			
			/* Lister les pizzas */
			case 1:
				it = dao.findAllPizzas().iterator();
				while (it.hasNext()){
					Pizza myPizza = (Pizza) it.next();
					System.out.println(myPizza.code 
							+ " -> " 
							+ myPizza.libelle 
							+ " (" 
							+ myPizza.prix 
							+ "€)"
							);
				}
				break;
				
			/* Ajouter une nouvelle pizza */
			case 2:
				System.out.println("Veuillez saisir le code : ");
				String code = sc.next();
				System.out.println("Veuillez saisir le nom (sans espace) : ");
				String libelle = sc.next();
				System.out.println("Veuillez saisir le prix : ");
				double prix = sc.nextDouble();
				Pizza newPizza = new Pizza(code, libelle, prix);
				dao.saveNewPizza(newPizza);
				break;
				
			/* Mettre à jour une pizza */	
			case 3:
				it = dao.findAllPizzas().iterator();
				while (it.hasNext()){
					Pizza myPizza = (Pizza) it.next();
					System.out.println(myPizza.code 
							+ " -> " 
							+ myPizza.libelle 
							+ " (" 
							+ myPizza.prix 
							+ "€)"
							);
				}
				System.out.println("Veuillez choisir le code de la pizza à modifier :");
				String oldCode = sc.next();
				
				System.out.println("Veuillez saisir le nouveau code :");
				String newCode = sc.next();
				System.out.println("Veuillez saisir le nouveau nom (sans espace) :");
				String newLibelle = sc.next();
				System.out.println("Veuillez saisir le nouveau prix :");
				double newPrix = sc.nextDouble();
				dao.updatePizza(oldCode, new Pizza(newCode, newLibelle, newPrix));
				break;
			case 4:
				System.out.println("4. Supprimer une pizza\n");
				break;
			case 99:
				break;
			}
		}
	}
}
