package it.lucaamoriello.clickeat.model;

public class BeanIngrediente {
	
	private Integer idIngrediente;
	private String nomeIngrediente;
	
	public BeanIngrediente() {
		
		setIdIngrediente(0);
		setNomeIngrediente("");
	}
	
	public BeanIngrediente(Integer idIngrediente, String nomeIngrediente) {
		
		setIdIngrediente(idIngrediente);
		setNomeIngrediente(nomeIngrediente);

	}
	
	public BeanIngrediente(String nomeIngrediente) {
		
		setNomeIngrediente(nomeIngrediente);

	}

	public Integer getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(Integer idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getNomeIngrediente() {
		return nomeIngrediente;
	}

	public void setNomeIngrediente(String nomeIngrediente) {
		this.nomeIngrediente = nomeIngrediente;
	}
}
