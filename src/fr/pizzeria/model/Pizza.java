package fr.pizzeria.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Pizza {	
	private int id;
	private String code;
	private String libelle;
	private double prix;
	private CategoriePizza categorie;
	
	private static final AtomicInteger count = new AtomicInteger(0);
	
	public Pizza(String code, String libelle, double prix, CategoriePizza categorie) {
		this.setId(count.incrementAndGet());
		this.setCode(code);
		this.setLibelle(libelle);
		this.setPrix(prix);
		this.setCategorie(categorie);
	}
	
	public Pizza(int id, String code, String libelle, double prix, CategoriePizza categorie) {
		this.setId(id);
		this.setCode(code);
		this.setLibelle(libelle);
		this.setPrix(prix);
		this.setCategorie(categorie);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

	public String getPizza() {
		return getCode() 
				+ " -> " 
				+ getLibelle() 
				+ " (" 
				+ String.format("%.2f", getPrix())
				+ " €)" 
				+ " [" 
				+ getCategorie().getNom() 
				+ "]";
	}
}
