package Team.Lista_Spesa_App.liste;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Team.Lista_Spesa_App.entities.Lista;
import Team.Lista_Spesa_App.repositories.ListaRepository;

@Service
public class ListaServiceImpl implements ListaService {

		
	@Autowired
	private ListaRepository repo;

	@Override
	public List<Lista> recuperaTutti() {
		
		return repo.findAllByOrderByNome();
	
	}

	@Override
	public Lista recuperaUno(long id) {

		

		Optional<Lista> l = repo.findById(id);
		
		
		return l.isEmpty() ? null : l.get();
		
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
