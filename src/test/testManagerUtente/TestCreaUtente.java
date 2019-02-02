package test.testManagerUtente;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import manager.ManagerUtente;
import model.BeanUtente;

public class TestCreaUtente {

	private static ManagerUtente mU;
	private static BeanUtente bU = new BeanUtente();
	private static String nomeUtente;
	private static String cognomeUtente;
	private static String ruoloUtente;
	private static String passwordUtente;
	private static String idLogin;
  
	
	@Before
	public static void setUp() {	
		
		nomeUtente = "test";
	    cognomeUtente = "test";
	    ruoloUtente = "test";
	    passwordUtente = "test";
	    idLogin = "test";

		
	}
	
	@Test
	public void tCreaUtente() {
		
		bU = mU.creaUtente(nomeUtente, passwordUtente, cognomeUtente, ruoloUtente, idLogin);
		
		assertNotNull(bU);
	}
	
	@After
	public static void tearDown() {
		
		mU.eliminaUtenteViaIdLogin(idLogin);
	}
	
	
	
	
}
