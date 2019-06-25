/* class: BeanUtente
 * author: Andrea Cupito
 * version: 1.0
 * classe utile per rappresentare l'oggetto persistente Utente.
 */

package it.lucaamoriello.clickeat.model;

public class BeanUtente {
	
	private String nomeUtente;
	private String cognomeUtente;
	private String passwordUtente;
	private String ruoloUtente;
	private String idLogin;
	private Integer idUtente;
	private int errorCode = 0;
	
	public BeanUtente(int errorCode) {
		this.setErrorCode(errorCode);
	}
	
	/* costruttore vuoto per l'oggetto BeanUtente; permette di creare un oggetto BeanUtente vuoto
 	*/
	public BeanUtente() {
	
		this.nomeUtente = "";
		this.cognomeUtente = "";
		this.passwordUtente = "";
		this.ruoloUtente = "";
		this.idLogin = "";
		this.idUtente = 0;
	}


	/* costruttore per l'oggetto BeanUtente iniziallizzato con i valori dati in input
	*/
	public BeanUtente(String nomeUtente, String cognomeUtente, String passwordUtente, String ruoloUtente, String idLogin) {
	
		setNomeUtente(nomeUtente);
		setCognomeUtente(cognomeUtente);
		setPasswordUtente(passwordUtente);
		setRuoloUtente(ruoloUtente);
		setIdLogin(idLogin);
	}


	public String getNomeUtente() {
		return nomeUtente;
	}


	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}


	public String getCognomeUtente() {
		return cognomeUtente;
	}


	public void setCognomeUtente(String cognomeUtente) {
		this.cognomeUtente = cognomeUtente;
	}


	public String getPasswordUtente() {
		return passwordUtente;
	}


	public void setPasswordUtente(String passwordUtente) {
		this.passwordUtente = passwordUtente;
	}


	public String getRuoloUtente() {
		return ruoloUtente;
	}


	public void setRuoloUtente(String ruoloUtente) {
		this.ruoloUtente = ruoloUtente;
	}


	public String getIdLogin() {
		return idLogin;
	}


	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}


	public Integer getIdUtente() {
		return idUtente;
	}


	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

} 
