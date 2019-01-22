package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import manager.ManagerUtente;
import model.BeanUtente;

public class TestManagerUtente {

	@Test
	public void CreaUtenteTest() throws ClassNotFoundException {
		
		ManagerUtente mU = new ManagerUtente();
		
		assertNotNull(mU.creaUtente("Test1", "Test1", "Test1", "Test1", "Test1"));
		
		
	}

}
