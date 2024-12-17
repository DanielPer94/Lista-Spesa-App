package Team.Lista_Spesa_App.liste;

import java.util.List;

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

	
	private String cartella = "liste";
	
	@Autowired
	private ListaService service;
	
	
	
	@GetMapping("/liste")
	public String liste(Lista lista) {
		
		lista.addAttribute("elenco", service.recuperaTutti());
		return this.cartella + "/liste";
		
	}
	@RequestMapping("/liste")
	public String getListe(Model model) {
		
		model.addAttribute("elenco", service.getElencoListe());
		
		return "liste";
		
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
		
		lista.addAttribute("nuovo", l);
		
		
		return cartella + "/inserisci";
		
	}
	
	
	
	@GetMapping("/modifica")
	public String modifica(@RequestParam("id_liste") long id, Lista lista, RedirectAttributes ra) {

		Lista l = service.recuperaUno(id);
		
		// se trovo modulo
		if(l != null) {
			
			lista.addAttribute("liste", l);
			
			return cartella + "/modifica";
			
		}
		
		
		
		ra.addFlashAttribute("messaggio", "ERRORE: lista non trovato");
		
		// genero la req che si occupa di ricaricare la lista
		return "redirect:/" + cartella + "/liste";
	}
	
	
	@PostMapping("/aggiorna")
	public String aggiorna(@ModelAttribute("liste") Lista l, Lista lista) {
		
		
		if(service.salva(l)) 
			return "redirect:/" + cartella + "/liste?id=" + l.getId();

		
		lista.addAttribute("liste", l);
		
		
		return cartella + "/modifica";

	}
	

	
	@GetMapping("/cancella")
	public String cancella(@RequestParam("id") long id) {
		
		service.cancella(id);
		return "redirect:/" + cartella + "/list";
		
	}

    
}
