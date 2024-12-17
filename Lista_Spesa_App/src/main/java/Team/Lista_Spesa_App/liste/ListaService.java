package Team.Lista_Spesa_App.liste;

import java.util.List;

import Team.Lista_Spesa_App.entities.Lista;

public interface ListaService {

	/**
	 * Metodo che permette di recuperare tutte le righe
	 * della tabella di riferimento
	 * @return lista con elementi
	 */
	
	public List <Lista> recuperaTutti();
	
	/**
	 * Metodo che permette di recuperare i dati 
	 * di una singola riga dalla tabella di riferimento
	 * @param id della riga da cercare
	 * @return oggetto che contiene i dati 
	 */
	public Lista recuperaUno(long id);
	
	/**
	 * Metodo che permette di salvare informazioni nella 
	 * tabella di riferimento.
	 * 
	 * Aggiungi: se oggetto non trovato (perchè manca id nel parametro)
	 * Modifica: se oggetto trovato (perchè contiene id valido nel parametro)
	 * 
	 * 
	 * @param l oggetto da salvare
	 * @return true quando l'operazione va a buon fine, false altrimenti
	 */
	public boolean salva(Lista l);
	
	/**
	 * Metodo che permette di cancellare una riga dalla
	 * tabella di riferimento 
	 * @param id riga da cancellare
	 */
	public void cancella(long id);
	

}
