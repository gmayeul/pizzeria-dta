package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pizza")
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "libelle")
	private String libelle;

	@Column(name = "prix", precision = 2)
	private double prix;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "categorie")
	private CategoriePizza categorie;

	public Pizza() {

	}

	public Pizza(String code, String libelle, double prix, CategoriePizza categorie) {
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

	public String toString() {
		return getCode() + " -> " + getLibelle() + " (" + String.format("%.2f", getPrix()) + " €)" + " ["
				+ getCategorie().getNom() + "]";
	}
}
