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

import Team.Lista_Spesa_App.entities.Lista;

@Controller
@RequestMapping("/liste")
public class ListaContorller {

	
	private String cartella = "lista";
	
	@Autowired
	private ListaService service;
	
	
	@GetMapping("/lista")
	public String liste(Lista lista) {
		
		lista.addAttribute("elenco", service.recuperaTutti());
		return this.cartella + "/lista";
		
	}


	@GetMapping("/lista")
	public String getModulo(@RequestParam("id") long id, Lista lista, RedirectAttributes ra) {
		
		
		Lista l = service.recuperaUno(id);
		
		
		if(l != null) {
			lista.addAttribute("lista", l);
			return cartella + "/lista";
		}
		
	
		ra.addFlashAttribute("messaggio", "ERRORE: modulo non trovato");
		
		
		return "redirect:/" + cartella + "/lista";
		
		
	}
	
	

	
	@GetMapping("/inserisci")
	public String inserisci(Lista lista) {
		
		lista.addAttribute("nuovo", new Lista());
		return cartella + "/inserisci";
		
	}
	
	@PostMapping("/aggiungi")
	public String aggiungi(@ModelAttribute("nuovo") Lista l, Lista lista) {
		
	
		if(service.salva(l))
			return "redirect:/" +  cartella + "/lista?id=" + l.getId();
		
		// salvo oggetto nuovo aggiornato
		lista.addAttribute("nuovo", l);
		lista.addAttribute("messaggio", "ERRORE: controlla il nome");
		
		return cartella + "/inserisci";
		
	}
	
	
	
	@GetMapping("/modifica")
	public String modifica(@RequestParam("id") long id, Lista lista, RedirectAttributes ra) {
		
	
		Lista l = service.recuperaUno(id);
		
		// se trovo modulo
		if(l != null) {
			
			
			lista.addAttribute("lista", l);
			
			
			return cartella + "/modifica";
			
		}
		
		
		
		ra.addFlashAttribute("messaggio", "ERRORE: lista non trovato");
		
		// genero la req che si occupa di ricaricare la lista
		return "redirect:/" + cartella + "/lista";
	}
	
	
	@PostMapping("/aggiorna")
	public String aggiorna(@ModelAttribute("modulo") Lista l, Lista lista) {
		
		
		
	
		if(service.salva(l)) 
			return "redirect:/" + cartella + "/lista?id=" + l.getId();

		
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
