package Team.Lista_Spesa_App.liste;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team.Lista_Spesa_App.entities.Lista;
import Team.Lista_Spesa_App.repositories.ListaRepository;

@Service
public class ListaServiceImpl implements ListaService {
/*
	 * autowired
	 * 		chiedo a spring di cercare la classe che implementa
	 * 		l'interfaccia richiesta per ottenerne una istanza
	 */
		
	@Autowired
	private ListaRepository repo;

	@Override
	public List<Lista> recuperaTutti() {
		
		return repo.findAllByOrderByNome();
	
	}

	@Override
	public Lista recuperaUno(long id) {

		/*
		 * cerco il modulo rispetto all'id
		 * 
		 * la riga di interesse potrebbe esserci oppure no
		 * 
		 * Optional è una tipologia di dato che
		 * potrebbe contenere un oggetto
		 */

		Optional<Lista> m = repo.findById(id);
		
		/*
		 * se Optional contiene l'oggetto lo restituisco, 
		 * altrimenti esco con null 
		 */
		return m.isEmpty() ? null : m.get();
		
	}

	@Override
	public boolean salva(Lista l) {

		boolean esito = true;
		
		try {
			
			repo.save(l);
			
		}
		catch (Exception e) {
			esito = false;
		}
		
		return esito;
	}

	@Override
	public void cancella(long id) {

		repo.deleteById(id);
		
	}

}
