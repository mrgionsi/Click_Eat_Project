package model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class BeanOrdinazione {
	
	private Integer numeroOrdinazione;
	private ArrayList<BeanPiatto> listaPiatti;
	
	private Timestamp dataOrdine;
	
	public BeanOrdinazione() {
		this.numeroOrdinazione = 0;
		listaPiatti = new ArrayList<BeanPiatto>();
		dataOrdine = null;
	}

	public BeanOrdinazione(Integer numeroOrdinazione, Timestamp dataOrdine) {
		super();
		this.numeroOrdinazione = numeroOrdinazione;
		listaPiatti = new ArrayList<BeanPiatto>();
		this.dataOrdine = dataOrdine;
	}
	
	public Integer getNumeroOrdinazione() {
		return numeroOrdinazione;
	}
	public void setNumeroOrdinazione(Integer numeroOrdinazione) {
		this.numeroOrdinazione = numeroOrdinazione;
	}
	public ArrayList<BeanPiatto> getListaPiatti() {
		return listaPiatti;
	}
	public void setListaPiatti(ArrayList<BeanPiatto> listaPiatti) {
		this.listaPiatti = listaPiatti;
	}
	public Timestamp getDataOrdine() {
		return dataOrdine;
	}
	public void setDataOrdine(Timestamp dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	
}
