package Team.Lista_Spesa_App.prodotti;

import java.util.List;

import Team.Lista_Spesa_App.entities.Prodotti;

public interface ProdottiService {
    public List<Prodotti> recuperaTutti();
	
	public Prodotti recuperaUno(long id); 
	
	public boolean salva(Prodotti p);
	
	public void cancella(long id);
}

