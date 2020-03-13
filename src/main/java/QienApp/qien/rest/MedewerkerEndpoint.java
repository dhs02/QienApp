package QienApp.qien.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@DeleteMapping("/medewerkers/{id}")
	public void delete(@PathVariable String id) {
		Long userId = Long.parseLong(id);
		s.verwijderen(userId);
	}
	
	@GetMapping("/test")
	public String test() {
		return("het werkt");
	}
	
	@PutMapping("/updatenaam/{id}")
	public void update() {
		
	}
	

	
}
