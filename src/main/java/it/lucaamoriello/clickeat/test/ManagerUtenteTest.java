//When I executed tests, every test was properly done.
//After some tests I noticed that something was wrong with tests.
//I looked for the order of execution and I realized that is not guaranteed.

package it.lucaamoriello.clickeat.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.lucaamoriello.clickeat.manager.ManagerUtente;

public class ManagerUtenteTest {

	//I seguenti dati caratterizzano l'utente che utilizzeremo per tutti i test
	private String nomeUtente = "nomeTest";
	private String passwordUtente = "passwordTest";
	private String cognomeUtente = "cognomeTest";
	private String ruoloUtente = "ruoloTest";
	private String idLogin = "loginTest";
	private String wrongIdLogin = "wrongIdLogin";
	private ManagerUtente mU = new ManagerUtente();
	

	//Preparo lo scenario per testare
	@BeforeEach
	public void setUp() {
		//questo id viene usato per il creaUtente
		mU.eliminaUtente("nuovoIdLogin");
		System.out.println("eliminato nuovoIdLogin");
		mU.creaUtente(nomeUtente, passwordUtente, cognomeUtente, ruoloUtente, idLogin);
	}
	//Ritorna BeanUtente istanziato se va a buon fine
	//BeanUtente con attr errorCode uguale a:
	//1062 se esiste un utente con lo stesso idLogin
	//1613 se la connessione al db va in timeout
	//Null in tutti gli altri casi
	@Test
	public void creaUtenteTest() {
		

		//primo caso di test, non esiste utente con questi dati, il test deve restituire l'utente non nullo
		assertNotNull(mU.creaUtente(nomeUtente, passwordUtente, cognomeUtente, ruoloUtente, "nuovoIdLogin"));
		//secondo caso di test, l'utente con i seguenti dati esiste già, l'utente restituito ha un unico attr con il codice di errore
		assertEquals(1062, (mU.creaUtente(nomeUtente, passwordUtente, cognomeUtente, ruoloUtente, idLogin).getErrorCode()));

	}
	
	

	
	@Test
	public void valoriLoginTest() {
		
		//primo caso di test, l'utente esiste nel db e viene restituito non nullo
		assertNotNull(mU.valoriLogin(idLogin, passwordUtente));
		//secondo caso di test, la coppia di valori non è presente nel db
		assertEquals(1329, mU.valoriLogin(wrongIdLogin, passwordUtente).getErrorCode());
	}
	

	
	@Test
	public void eliminaUtenteTest() {
		//primo caso di test, l'utente esiste e viene correttamente eliminato
		assertTrue((mU.eliminaUtente(idLogin)));
		//secondo caso di test, l'utente non esiste e quindi il metodo ritorna false
		assertFalse((mU.eliminaUtente(wrongIdLogin)));
		
	}
	

	
	@Test
	public void ottieniUtentiTest() {
		
		//primo caso di test, il metodo ritorna una lista non nulla
		assertNotNull(mU.ottieniUtenti());
		
	}
	
	

	@AfterEach
	public void tearDown() {
		
		mU.eliminaUtente(idLogin);
	}
}
