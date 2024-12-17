package Team.Lista_Spesa_App.prodotti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Team.Lista_Spesa_App.entities.Prodotti;
import Team.Lista_Spesa_App.liste.ListaService;

@Controller
@RequestMapping("/prodotti")
public class ProdottiController {


    private String cartella = "prodotti";
	
	@Autowired
	private ProdottiService service;
	
	@Autowired
	private ListaService serviceL;
	

	
	@GetMapping("/lista")
	public String lista(Model model) {
		
		model.addAttribute("elenco" , service.recuperaTutti());
		
		return this.cartella + "/lista";
	}

	
	@GetMapping("/prodotti")
	public String getProgetto(@RequestParam("id_prod") long id, Model model, RedirectAttributes ra) {
		
		Prodotti p = service.recuperaUno(id);
		
		if(p != null) {
			model.addAttribute("progetto", p);
			return cartella + "/progetto";
		}
		
		ra.addFlashAttribute("messaggio", "ERRORE: progetto non trovato");
		return "redirect:/" + cartella + "/lista";
	
	}
	

	
	@GetMapping("/inserisci")
	public String inserisci(Model model) {
		
		model.addAttribute("nuovo", new Prodotti());
		model.addAttribute("lise", serviceL.recuperaTutti());		
		
		return cartella + "/inserisci";
	}
	
	@PostMapping("/aggiungi")
	public String aggiungi(@ModelAttribute("nuovo") Prodotti p, Model model) {
		
		if(service.salva(p))
			return "redirect:/" + cartella + "/progetto?id=" + p.getId();
		
		model.addAttribute("messaggio", "ERRORE: controlla i dati");
		model.addAttribute("nuovo", p);
		model.addAttribute("moduli", serviceL.recuperaTutti());		
				
		return cartella + "/inserisci";
		
	}
	
	
	@GetMapping("/modifica")
	public String modifica(@RequestParam("id_prod") long id, Model model, RedirectAttributes ra) {
		
		Prodotti p = service.recuperaUno(id);
		
		if(p != null) {
			model.addAttribute("prodotti", p);
			model.addAttribute("liste", serviceL.recuperaTutti());
			return cartella + "/modifica";
		}
		
		ra.addFlashAttribute("messaggio", "ERRORE: progetto non trovato");
		return "redirect:/" + cartella + "/lista";
	
	}
	
	@PostMapping("/aggiorna")
	public String aggiorna(@ModelAttribute("progetto") Prodotti p, Model model) {
		
		if(service.salva(p))
			return "redirect:/" + cartella + "/progetto?id=" + p.getId();
		
		model.addAttribute("messaggio", "ERRORE: controlla i dati");
		model.addAttribute("prodotti", p);
		model.addAttribute("liste", serviceL.recuperaTutti());		
				
		return cartella + "/modifica";
		
		
	}
	

	@GetMapping("/cancella")
	public String cancella(@RequestParam("id_prod") long id) {
		
		service.cancella(id);
		
		return "redirect:/" + cartella + "/lista";
		
	}
	
}
