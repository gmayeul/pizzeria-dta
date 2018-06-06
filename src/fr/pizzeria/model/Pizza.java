package fr.pizzeria.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Pizza {
	private static final AtomicInteger count = new AtomicInteger(0);
	public int id;
	public String code;
	public String libelle;
	public double prix;
	
	public Pizza(String code, String libelle, double prix) {
		this.id = count.incrementAndGet();
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
	}
	
	public Pizza(int id, String code, String libelle, double prix) {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
	}
}
