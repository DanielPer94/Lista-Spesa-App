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
@RequestMapping("/lista")
public class ListaContorller {



	private String cartella = "liste";
	
	@Autowired
	private ListaService service;
	
	
	
	@GetMapping("/lista")
	public String lista(Model model) {
		
		model.addAttribute("elenco", service.recuperaTutti());
		return this.cartella + "/lista";
		
	}

	
	
	@GetMapping("/lista")
	public String getModulo(@RequestParam("id_liste") long id, Model model, RedirectAttributes ra) {
		
		
		Lista l = service.recuperaUno(id);
		
		
		if(l != null) {
			model.addAttribute("lista",l);
			return cartella + "/lista";
		}
		
		
		ra.addFlashAttribute("messaggio", "ERRORE: modulo non trovato");
		
		
		return "redirect:/" + cartella + "/lista";
		
		
	}
	
	
	
	@GetMapping("/inserisci")
	public String inserisci(Model model) {
		
		model.addAttribute("nuovo", new Lista());
		return cartella + "/inserisci";
		
	}
	
	@PostMapping("/aggiungi")
	public String aggiungi(@ModelAttribute("nuovo") Lista l, Model model) {
		
	
		if(service.salva(l))
			return "redirect:/" +  cartella + "/lista?id=" + l.getId();
		
		
		model.addAttribute("nuovo", l);
		model.addAttribute("messaggio", "ERRORE: controlla il nome");
		
		return cartella + "/inserisci";
		
	}
	
	

	
	@GetMapping("/modifica")
	public String modifica(@RequestParam("id_liste") long id, Model model, RedirectAttributes ra) {
		
	
		Lista l = service.recuperaUno(id);
		
		
		if(l != null) {
		
			
			model.addAttribute("lista", l);
			
			
			return cartella + "/modifica";
			
		}
		
		
		
		ra.addFlashAttribute("messaggio", "ERRORE: modulo non trovato");
	
		return "redirect:/" + cartella + "/lista";
	}
	
	
	@PostMapping("/aggiorna")
	public String aggiorna(@ModelAttribute("lista") Lista l, Model model) {
		
		
		if(service.salva(l)) 
			return "redirect:/" + cartella + "/lista?id=" + l.getId();
	
		
		model.addAttribute("lista", l);
		model.addAttribute("messaggio", "ERRORE: controlla il nome");
		
		return cartella + "/modifica";

	}
	
	@GetMapping("/cancella")
	public String cancella(@RequestParam("id_liste") long id) {
		
		service.cancella(id);
		return "redirect:/" + cartella + "/lista";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
