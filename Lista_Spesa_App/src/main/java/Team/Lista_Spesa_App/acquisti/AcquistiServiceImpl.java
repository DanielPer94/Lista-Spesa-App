package Team.Lista_Spesa_App.acquisti;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AcquistiServiceImpl implements AcquistiService {


    
	@Override
	public List<String> getElencoAcqusiti() {

		String[] elenco =  {""};
		Arrays.sort(elenco);
		
		return Arrays.asList(elenco);
		 
	}
}
