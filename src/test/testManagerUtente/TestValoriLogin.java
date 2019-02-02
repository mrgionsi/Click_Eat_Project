package test.testManagerUtente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import manager.ManagerUtente;
import model.BeanUtente;

public class TestValoriLogin {
	
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

	    bU = mU.creaUtente(nomeUtente, passwordUtente, cognomeUtente, ruoloUtente, idLogin);

	}
	
	@Test
	public void tValoriLogin() {
		
		bU = mU.valoriLogin(idLogin, passwordUtente);
		
		assertNotNull(bU);
		assertNotNull(bU.getIdUtente());
		assertEquals("test", bU.getIdLogin());
		assertEquals("test", bU.getPasswordUtente());
		
	}
	
	@After
	public static void tearDown() {
		
		mU.eliminaUtenteViaIdLogin(idLogin);
	}

}
