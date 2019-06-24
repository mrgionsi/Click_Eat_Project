/* class: BeanTavolo
 * author: Giovanni Pasquariello
 * classe che rappresentà l'entità Piatto in real time.
 */



package it.lucaamoriello.clickeat.model;

public class EFACT_BeanPiatto extends BeanPiatto {


	public EFACT_BeanPiatto(int idPiatto, String nomePiatto, String categoriaPiatto, Float prezzoPiatto, String listaIngredienti,int quantita)
	{
		super( idPiatto,  nomePiatto,  categoriaPiatto,  prezzoPiatto,  listaIngredienti);
		this.setQuantita(quantita);
	}
	private int quantita;
	

	public EFACT_BeanPiatto(int idPiatto,int quantita)
	{
		super( idPiatto);
		this.setQuantita(quantita);
	}


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
}