package QienApp.qien.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import QienApp.qien.domein.Medewerker;
import QienApp.qien.controller.MedewerkerService;

@RestController
public class testEndpoint {
	
	@Autowired
	MedewerkerService s;
	
	@PostMapping("/kijkmaar")
	public Medewerker test(@RequestBody Medewerker medewerker) {
		System.out.println("hij doet het!!");
		s.inService(medewerker);
		return(medewerker);
	}
	
}
