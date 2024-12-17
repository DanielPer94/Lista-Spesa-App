package Team.Lista_Spesa_App.prodotti;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import Team.Lista_Spesa_App.entities.Prodotti;
import Team.Lista_Spesa_App.repositories.ProdottiRepository;

public class ProdottiServiceImpl implements ProdottiService {
@Autowired
	private ProdottiRepository repo;
	
	@Override
	public List<Prodotti> recuperaTutti() {
		
		return repo.findAllByOrderByNome();
	}

	@Override
	public Prodotti recuperaUno(long id) {
		
		Optional<Prodotti> p = repo.findById(id);
		return p.isEmpty() ? null : p.get();
		
	}

	@Override
	public boolean salva(Prodotti p) {

		boolean esito = true;
		
		try {
			
			repo.save(p);
			
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

