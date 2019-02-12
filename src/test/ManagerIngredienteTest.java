package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manager.ManagerIngrediente;

public class ManagerIngredienteTest {
	
	private ManagerIngrediente mI = new ManagerIngrediente();
	private String exists = "Esiste";
	private String notExists = "NonEsiste";
	private int idToSearch = 76;
	
	@BeforeEach
	public void setUp() {
		
		//Aggiungo ingrediente di test per controllare i fallimenti su inserimenti con lo stesso nome
		//e metodi di ricerca
		mI.creaIngrediente(exists);
	}
	
	
	@Test
	public void creaIngredienteTest() {
		
		//ritorna bean non nullo se si prova ad inserire un ingrediente non esistente
		//e la aggiunta al db va a buon fine

		assertNotNull(mI.creaIngrediente(notExists));
		
		//ritorna bean nullo se si prova ad inserire un ingrediente esistente
		assertNotNull(mI.creaIngrediente(exists));
		assertEquals(exists, mI.creaIngrediente(exists).getNomeIngrediente());
		
	}
	
	@Test
	public void ricercaPerIdTest() {
		
		//metodo ritorna bean non nullo se si prova ad cercare un ingrediente che esiste

		assertNotNull(mI.ricercaPerId(idToSearch));
		
		
		//metodo ritorna bean nullo se si prova ad cercare un ingrediente che non esiste

		assertNull(mI.ricercaPerId(0000));
	}
	
	@Test
	public void ricercaPerNomeTest() {
		
		//metodo ritorna bean non nullo se si prova ad cercare un ingrediente che esiste

		assertNotNull(mI.ricercaPerNome(exists));
		//metodo ritorna bean nullo se si prova ad cercare un ingrediente che non esiste

		assertNull(mI.ricercaPerNome(notExists));

	}
	
	@Test
	public void ottieniListaIngredienti() {
		
		//metodo ritorna una lista non nulla se riesce ad ottenere elementi dal db
		assertNotNull(mI.ottieniListaIngredienti());
	}
	
	@Test
	public void eliminaIngrediente() {
		
		//metodo ritorna true se si prova ad eliminare un ingrediente che esiste

		assertTrue(mI.eliminaIngrediente(exists));
		
		
		//metodo ritorna true se si prova ad eliminare un ingrediente che non esiste
		assertTrue(mI.eliminaIngrediente(notExists));
	}

}
