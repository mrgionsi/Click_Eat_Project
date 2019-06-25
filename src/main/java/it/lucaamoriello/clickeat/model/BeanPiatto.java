package it.lucaamoriello.clickeat.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanPiatto {

	private String nomePiatto;
	private String categoriaPiatto;
	private List<BeanIngrediente> listaIngredienti;
	private Float prezzoPiatto;
	private Integer idPiatto;


	public BeanPiatto() {
		setNomePiatto("");
		setCategoriaPiatto("");
		listaIngredienti = new ArrayList<BeanIngrediente> ();
		setPrezzoPiatto(null);
	}
	public BeanPiatto(int idPiatto) {
		setIdPiatto(idPiatto);
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

	public BeanPiatto(int idPiatto, String nomePiatto, String categoriaPiatto, Float prezzoPiatto, List<BeanIngrediente> listaIngredienti) {

		setIdPiatto(idPiatto);
		setListaIngredienti(listaIngredienti);
		setNomePiatto(nomePiatto);
		setCategoriaPiatto(categoriaPiatto);
		setPrezzoPiatto(prezzoPiatto);
	}

	
	public BeanPiatto(int idPiatto, String nomePiatto, String categoriaPiatto, Float prezzoPiatto, String listaIngredienti) {

		setIdPiatto(idPiatto); 
		setNomePiatto(nomePiatto);
		setCategoriaPiatto(categoriaPiatto);
		setPrezzoPiatto(prezzoPiatto);
		
		this.listaIngredienti = new ArrayList<BeanIngrediente> ();
		
		if(listaIngredienti!=null)
		{
		List<String> list =Arrays.asList(listaIngredienti.split(","));
		list.forEach(element -> {
			this.listaIngredienti.add(new BeanIngrediente(element));
		});
		}else this.listaIngredienti = new ArrayList<BeanIngrediente>();
			
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

	public List<BeanIngrediente> getListaIngredienti() {
		return listaIngredienti;
	}

	public void setListaIngredienti(List<BeanIngrediente> listaIngredienti2) {
		this.listaIngredienti = listaIngredienti2;
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
	public boolean equals(Object other) {
	    if (other == null) return false;
	    if (!(other instanceof BeanPiatto))return false;
		
	    BeanPiatto o = (BeanPiatto) other;
		if(this.idPiatto == o.getIdPiatto())
			return true;
		return false;
		
	}

}
