//When I executed tests, every test was properly done.
//After some tests I noticed that something was wrong with tests.
//I looked for the order of execution and I realized that is not guaranteed.

package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import manager.ManagerOrdinazione;

public class ManagerOrdinazioneTest {
	
	private ManagerOrdinazione mO = new ManagerOrdinazione();
	private int nTavolo = 25;
	
	@Test
	public void creaOrdinazioneTest(){
	
		assertNotNull(mO.creaOrdinazione());
		
	}
	
	@Test
	public void ottieniOrdinazione() {
		
		//tavolo 25 ha assegnata l'ordinazione n 1, 
		//metodo ritorna l'ordinazione e quindi accedendo all'attr numeroOrdinazione
		//mi assicuro che l'ordinazione viene correttamente istanziata e che 
		//il numero dell'ordinazione è quello che mi aspetto
		assertEquals(1, mO.ottieniOrdinazione(nTavolo).getNumeroOrdinazione());
		
		//cerco l'ordinazione di un tavolo non esistente ottengo valore null
		assertNull(mO.ottieniOrdinazione(88888));
	}

}
