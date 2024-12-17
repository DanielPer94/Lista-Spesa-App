package Team.Lista_Spesa_App.liste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import java.util.*;
import Team.Lista_Spesa_App.entities.Lista;

@Controller
@RequestMapping("/liste")
public class ListaContorller {

		// salvo il nome della cartella che contiene le view
	private String cartella = "lista";
	
	@Autowired
	private ListaService service;
	
	// LISTA -------------------------------------------------------------
	// REQ: /moduli/lista
	
	@GetMapping("/lista")
	public String lista(Lista lista) {
		
		lista.addAttribute("elenco", service.recuperaTutti());
		return this.cartella + "/lista";
		
	}

	// MODULO ------------------------------------------------------------
	
	@GetMapping("/lista")
	public String getModulo(@RequestParam("id") long id, Lista lista, RedirectAttributes ra) {
		
		// recupero il odulo rispetto all'id
		Lista l = service.recuperaUno(id);
		
		// se trovo il modulo passo alla view che se ne occupa
		if(l != null) {
			lista.addAttribute("lista", l);
			return cartella + "/lista";
		}
		
		// se non trovo il modulo salvo un messaggio di errore in redirect
		ra.addFlashAttribute("messaggio", "ERRORE: modulo non trovato");
		
		// genero la request che gestisce la lista
		return "redirect:/" + cartella + "/lista";
		
		
	}
	
	
	// AGGIUNGI ----------------------------------------------------------
	
	@GetMapping("/inserisci")
	public String inserisci(Lista lista) {
		
		lista.addAttribute("nuovo", new Lista());
		return cartella + "/inserisci";
		
	}
	
	@PostMapping("/aggiungi")
	public String aggiungi(@ModelAttribute("nuovo") Lista l, Lista lista) {
		
		// nuovo modulo salvato -> visualizzo pagina modulo (genero req che se ne occupa)
		if(service.salva(l))
			return "redirect:/" +  cartella + "/lista?id=" + l.getId();
		
		// salvo oggetto nuovo aggiornato
		lista.addAttribute("nuovo", l);
		lista.addAttribute("messaggio", "ERRORE: controlla il nome");
		
		return cartella + "/inserisci";
		
	}
	
	
	// MODIFICA ----------------------------------------------------------
	
	@GetMapping("/modifica")
	public String modifica(@RequestParam("id") long id, Lista lista, RedirectAttributes ra) {
		
		// cerco modulo rispetto all'id
		Lista m = service.recuperaUno(id);
		
		// se trovo modulo
		if(l != null) {
			
			// salvo modulo nel model affinché possa essere utilizzato dalla view
			model.addAttribute("modulo", l);
			
			// passo il comando alla view per la modifica
			return cartella + "/modifica";
			
		}
		
		// se non trovo modulo 
		// predispongo messaggio messaggio di cortesia
		
		ra.addFlashAttribute("messaggio", "ERRORE: modulo non trovato");
		
		// genero la req che si occupa di ricaricare la lista
		return "redirect:/" + cartella + "/lista";
	}
	
	
	@PostMapping("/aggiorna")
	public String aggiorna(@ModelAttribute("modulo") Lista l, Lista lista) {
		
		// delego al service il salvataggio dei dati
		
		// se modulo aggiornato correttamente --> genero req per accedere ai dati
		if(service.salva(l)) 
			return "redirect:/" + cartella + "/lista?id=" + l.getId();
		
		// se modulo non aggiornato correttamente
		//	--> salvo oggetto nel model (per non perdere info)
		// 	--> salvo messaggio di cortesia nel model
		//  --> passo il comando alla view per gestire altre modifiche
		
		lista.addAttribute("lista", l);
		lista.addAttribute("messaggio", "ERRORE: controlla il nome");
		
		return cartella + "/modifica";

	}
	
	// ELIMINA -----------------------------------------------------------
	
	@GetMapping("/cancella")
	public String cancella(@RequestParam("id") long id) {
		
		service.cancella(id);
		return "redirect:/" + cartella + "/lista";
		
	}

    
}
