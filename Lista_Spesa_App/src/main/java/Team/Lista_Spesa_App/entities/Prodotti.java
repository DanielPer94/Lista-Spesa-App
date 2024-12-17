package Team.Lista_Spesa_App.entities;
import java.util.List;

    import org.springframework.boot.autoconfigure.domain.EntityScan;
    import jakarta.persistence.Column;
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
    @Table(name = "prodotti")
    @NoArgsConstructor @AllArgsConstructor @Data
    public class Prodotti {
    
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        
        @Column(name="nome_prod")
        private String nome;
        
        @Column(name="prezzo_prod")
        private int prezzo;
        
        
        @OneToMany(mappedBy = "prodotti")
        @ToString.Exclude
        private List<Prodotti> prodotti;
    
}

