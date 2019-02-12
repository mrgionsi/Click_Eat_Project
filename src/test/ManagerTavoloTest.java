package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manager.ManagerTavolo;


public class ManagerTavoloTest {
	
	private int numeroTavolo = 9999;
	private int tavoloNotDb = 0000;
	private ManagerTavolo mT = new ManagerTavolo();
	
	@Test
	public void creaTavoloTest() {
		assertTrue(mT.creaTavolo(numeroTavolo));
	}
	
//	@Test
//	public void freeTavoloTest() {
//		
//		IL TEST DEVE ESSERE ANCORA DEFINITO PERCHE' IL TRIGGER NEL DB NON ESISTE AL MOMENTO
//	}
	
	@Test 
	public void setOccupatoTest() {
		//restituisce false se il tavolo non esiste  MODIFCARE
		assertFalse(mT.setOccupato(tavoloNotDb));

		
		//restituisce true se il tavolo viene occupato con successo
		assertTrue(mT.setOccupato(numeroTavolo));

	}
	
	@Test 
	public void isOccupatoTest() {
		//restituisce null se il tavolo non esiste
		assertNull(mT.isOccupato(tavoloNotDb));

		//restituisce true se il tavolo è occupato
		assertTrue(mT.isOccupato(numeroTavolo));
	}
	
	@Test
	public void getOrdinazioneDiTavoloTest() {
		
		//tavolo 999 non ha ordinazione e quindi il metodo ritorna 0 per ordinazione non presente
		assertEquals(0, mT.getOrdinazioneDiTavolo(numeroTavolo));
		
		//tavolo 7 ha l'ordinazione 152
		assertEquals(152, mT.getOrdinazioneDiTavolo(7));
	}
	
	@Test
	public void getTavoloTest() {
		//restituisce tavlo non nullo se esiste nel db
		assertNotNull(mT.getTavolo(numeroTavolo));
		
		//restituisce tavolo nullo se non esiste nel db
		assertNull(mT.getTavolo(tavoloNotDb));
		
	}
	

	
	@Test 
	public void isContoPresenteTest() {
		//restituisce null se il tavolo non esiste
		assertNull(mT.isContoPresente(tavoloNotDb));

		//restituisce false se il conto non è stato richiesto, tavolo 999 non ha conto settato a true
		assertFalse(mT.isContoPresente(numeroTavolo));
	}

	

	
	@Test
	public void eliminaTavoloTest() {
		//se il tavolo esiste e viene eliminato = true
		assertTrue(mT.eliminaTavolo(numeroTavolo));
		
		//se il tavolo non esiste = false
		assertFalse(mT.eliminaTavolo(tavoloNotDb));
	}
	
	

}
