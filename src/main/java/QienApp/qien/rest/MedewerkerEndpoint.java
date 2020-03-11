package QienApp.qien.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import QienApp.qien.controller.MedewerkerService;
import QienApp.qien.domein.Medewerker;


@RestController
public class MedewerkerEndpoint {
	
	@Autowired
	MedewerkerService s;
	
	@GetMapping("/medewerkers")
	public Iterable<Medewerker> jojo() {
		return s.getMedewerker();
	}
	
	
	@GetMapping("/checkit")
	public String test() {
		s.inService();
		return("het werkt");
	}
}
