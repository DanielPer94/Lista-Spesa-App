package Team.Lista_Spesa_App.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Team.Lista_Spesa_App.entities.Lista;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {
	
	
	public List<Lista> findAllByOrderByNome();
	
	

}
