package Team.Lista_Spesa_App.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String homepage() {
		// restituisco nome della view (file senza estensione html) 
		return "index";
	}
	
}