package model;

import java.util.ArrayList;

public class BeanPiatto {
	
	private String nomePiatto;
	private String categoriaPiatto;
	private ArrayList<BeanIngrediente> listaIngredienti;
	private Float prezzoPiatto;
	private Integer idPiatto;
	

	public BeanPiatto() {
		setNomePiatto("");
		setCategoriaPiatto("");
		listaIngredienti = new ArrayList<BeanIngrediente> ();
		setPrezzoPiatto(null);
	}
	
	public BeanPiatto(String nomePiatto, String categoriaPiatto, Float prezzoPiatto) {
		
		setNomePiatto(nomePiatto);
		setCategoriaPiatto(categoriaPiatto);
		listaIngredienti = new ArrayList<BeanIngrediente> ();
		setPrezzoPiatto(prezzoPiatto);
	}
	
	public BeanPiatto(int idPiatto, String nomePiatto, String categoriaPiatto, Float prezzoPiatto) {
		
		setIdPiatto(idPiatto);
		setNomePiatto(nomePiatto);
		setCategoriaPiatto(categoriaPiatto);
		listaIngredienti = new ArrayList<BeanIngrediente> ();
		setPrezzoPiatto(prezzoPiatto);
	}

	public BeanPiatto(int idPiatto, String nomePiatto, String categoriaPiatto, Float prezzoPiatto, ArrayList<BeanIngrediente> listaIngredienti) {
	
		setIdPiatto(idPiatto);
		setListaIngredienti(listaIngredienti);
		setNomePiatto(nomePiatto);
		setCategoriaPiatto(categoriaPiatto);
		setPrezzoPiatto(prezzoPiatto);
	}

	public String getNomePiatto() {
		return nomePiatto;
	}
	
	public void setNomePiatto(String nomePiatto) {
		this.nomePiatto = nomePiatto;
	}
	
	public String getCategoriaPiatto() {
		return categoriaPiatto;
	}
	
	public void setCategoriaPiatto(String categoriaPiatto) {
		this.categoriaPiatto = categoriaPiatto;
	}
	
	public ArrayList<BeanIngrediente> getListaIngredienti() {
		return listaIngredienti;
	}
	
	public void setListaIngredienti(ArrayList<BeanIngrediente> listaIngredienti) {
		this.listaIngredienti = listaIngredienti;
	}
	

	public Float getPrezzoPiatto() {
		return prezzoPiatto;
	}
	
	public void setPrezzoPiatto(Float prezzoPiatto) {
		this.prezzoPiatto = prezzoPiatto;
	}
	
	public Integer getIdPiatto() {
		return idPiatto;
	}
	
	public void setIdPiatto(Integer idPiatto) {
		this.idPiatto = idPiatto;
	}
	
}
