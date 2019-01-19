package model;

public class BeanIngrediente {
	
	private Integer idIngrediente;
	private String nomeIngrediente;
	private String categoriaIngrediente;
	
	public BeanIngrediente() {
		
		setIdIngrediente(0);
		setNomeIngrediente("");
		setCategoriaIngrediente("");
	}
	
	public BeanIngrediente(Integer idIngrediente, String nomeIngrediente, String categoriaIngrediente) {
		
		setIdIngrediente(idIngrediente);
		setNomeIngrediente(nomeIngrediente);
		setCategoriaIngrediente(categoriaIngrediente);
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

	public String getCategoriaIngrediente() {
		return categoriaIngrediente;
	}

	public void setCategoriaIngrediente(String categoriaIngrediente) {
		this.categoriaIngrediente = categoriaIngrediente;
	}
}
