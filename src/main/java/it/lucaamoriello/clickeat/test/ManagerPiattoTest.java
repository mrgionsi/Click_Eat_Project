package it.lucaamoriello.clickeat.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.lucaamoriello.clickeat.manager.ManagerPiatto;
import it.lucaamoriello.clickeat.model.BeanIngrediente;
import it.lucaamoriello.clickeat.model.BeanPiatto;

public class ManagerPiattoTest {

	private ManagerPiatto mP = new ManagerPiatto();
	private String nomePiatto = "newTestPlate";
	private float prezzoPiatto = 22;
	private String categoriaPiatto = "Primi";
	private BeanPiatto bP = new BeanPiatto();
	private BeanPiatto bPN = new BeanPiatto();
	private BeanPiatto bPSave = new BeanPiatto();
	private int id = 0;
	private BeanIngrediente bI1 = new BeanIngrediente("IngredienteP1");
	private BeanIngrediente bI2 = new BeanIngrediente("IngredienteP2");
	private BeanIngrediente bI3 = new BeanIngrediente("IngredienteP3");

	ArrayList<BeanIngrediente> listaIngredienti = new ArrayList<BeanIngrediente> ();
	
	@Test
	public void ottieniListaPiattiTest() {
		//ritorna la lista dei piatto se viene correttamente effettuate la select nel db
		assertNotNull(mP.ottieniListaPiatti());
	}
	
	@Test
	public void CreaPiattoTest() {
		bP = mP.creaPiatto(nomePiatto, prezzoPiatto, categoriaPiatto);
		System.out.println(id);
		//questa merda di JUnit non funziona e quindi gli faccio avere l'id così
		id = bP.getIdPiatto();
		System.out.println(id);

		bPSave = bP;
		//Ritorna oggetto non nullo per nuovo piatto creato
		assertNotNull(bP);
		
	}
	
	@Test
	public void ingredientiNelPiattoTest() {
		//Ritorna nullo se non esistono ingredienti nel piatto
		assertNull(mP.ingredientiNelPiatto(id));
		//Ritorna nullo se non se si tenta di prendere ingredienti da un piatto che non esiste
		assertNull(mP.ingredientiNelPiatto(000));
		//Ritorna non nullo se esistono ingredienti nel piatto // 1 ha ingredienti nel piatto ed esiste
		assertNotNull(mP.ingredientiNelPiatto(1));

	}
	
	@Test
	public void ottieniPiattioTest() {
		//ritorna piatto non nullo se viene richiesto un piatto esistente nel db
		assertNotNull(mP.ottieniPiatto(1));
		//ritorna piatto nullo se viene richiesto un piatto non esistente nel db
		assertNull(mP.ottieniPiatto(999));
	}
	
	@Test
	public void inserisciIngredientiNelPiattoTest(){
		listaIngredienti.add(bI1);
		listaIngredienti.add(bI2);
		listaIngredienti.add(bI3);
		
		bPSave.setListaIngredienti(listaIngredienti);
		System.out.println(bPSave.getIdPiatto());
		//Piatto non nullo se l'aggiunta va a buon fine
		assertNotNull(mP.inserisciIngredientiNelPiatto(bPSave));
		
		//Null perché bPN non esiste
		//Ritorna null se si prova ad aggiungere ad un piatto che non esiste nel db
		assertNull(mP.inserisciIngredientiNelPiatto(bPN));

	}	
	
	@Test
	public void eliminaPiattoTest() {
		
		
		//ritorna true se il piatto esiste nel db
		assertTrue(mP.eliminaPiatto(bP.getIdPiatto()));
		
		//ritorna false se il piatto non esiste nel db
		assertFalse(mP.eliminaPiatto(999));
	}
	
}
