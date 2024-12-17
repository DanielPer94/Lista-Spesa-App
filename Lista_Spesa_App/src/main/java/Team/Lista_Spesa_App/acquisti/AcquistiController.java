package Team.Lista_Spesa_App.acquisti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ch.qos.logback.core.model.Model;

@Controller
public class AcquistiController {

    @Autowired
	//private AcquistiService service;
	
	@RequestMapping("/acquisti")
	public String getAcquisti(Model model) {
		
//	model.addAttribute("elenco", service.getElencoAcqusiti());
		
		return "acqusiti";
		
	}
}

