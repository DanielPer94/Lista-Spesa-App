package Team.Lista_Spesa_App.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team.Lista_Spesa_App.entities.Prodotti;



    @Repository
public interface ProdottiRepository extends JpaRepository<Prodotti, Long>{
	
	// select * from modulo order by nome
	public List<Prodotti> findAllByOrderByNome();
	
	

}

