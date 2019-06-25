/* class: BeanTavolo
 * author: AndreaCupito
 * classe utile per rappresentare l'oggetto persistente tavolo.
 */

package it.lucaamoriello.clickeat.model;

public class BeanTavolo {
	
	private Integer numeroTavolo;
	private Integer numeroOrdinazione;
	private boolean flagOccupato;
	private boolean flagConto;
	
	public BeanTavolo() {
		this.numeroTavolo = 0;
		this.numeroOrdinazione = 0;
		this.flagOccupato = false;
		this.flagConto = false;
	}
	
	public BeanTavolo(Integer numeroTavolo) {
		
		setNumeroTavolo(numeroTavolo);
		setNumeroOrdinazione(0);
		setFlagConto(false);
		setFlagOccupato(false);
	}

	public BeanTavolo(int numeroTavolo, int numeroOrdinazione, boolean flagOccupato, boolean flagConto) {
		setNumeroTavolo(numeroTavolo);
		setNumeroOrdinazione(numeroOrdinazione);
		setFlagConto(flagConto);
		setFlagOccupato(flagOccupato);	}

	public Integer getNumeroTavolo() {
		return numeroTavolo;
	}

	public void setNumeroTavolo(Integer numeroTavolo) {
		this.numeroTavolo = numeroTavolo;
	}

	public Integer getNumeroOrdinazione() {
		return numeroOrdinazione;
	}

	public void setNumeroOrdinazione(Integer numeroOrdinazione) {
		this.numeroOrdinazione = numeroOrdinazione;
	}

	public boolean isFlagOccupato() {
		return flagOccupato;
	}

	public void setFlagOccupato(boolean flagOccupato) {
		this.flagOccupato = flagOccupato;
	}

	public boolean isFlagConto() {
		return flagConto;
	}

	public void setFlagConto(boolean flagConto) {
		this.flagConto = flagConto;
	}
	
}
