package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import manager.ManagerUtente;
import model.BeanUtente;

public class TestManagerUtente {

	private static ManagerUtente mU;
	private static BeanUtente bU;
	
	@BeforeAll
	public static void setUp() {
		mU = new ManagerUtente();
		bU = mU.creaUtente("test", "test", "test", "test", "test");
		bU = mU.valoriLogin(bU.getIdLogin(), bU.getPasswordUtente());
	}

	@AfterEach
	public void tearDown() {
		
		mU.eliminaUtente(bU.getIdUtente());
	}
	
	@Test
	
	public void testValoriLogin ()
	{
		assertNotNull(mU.valoriLogin(bU.getIdLogin(), bU.getPasswordUtente()));
		assertEquals("test", bU.getIdLogin());
		assertEquals("test", bU.getPasswordUtente());
	}
}


