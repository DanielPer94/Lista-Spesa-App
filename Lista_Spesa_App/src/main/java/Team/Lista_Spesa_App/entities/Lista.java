package Team.Lista_Spesa_App.entities;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EntityScan
@Table(name = "liste")
@NoArgsConstructor @AllArgsConstructor @Data
public class Lista {

    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="nome_lista")
	private String nome;
	
	//da controllare -----
	@OneToMany(mappedBy = "liste")
	@ToString.Exclude
	private List<Lista> liste;
	
}
