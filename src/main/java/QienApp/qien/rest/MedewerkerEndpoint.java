package QienApp.qien.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import QienApp.qien.controller.MedewerkerService;


@RestController
public class MedewerkerEndpoint {
	
	@Autowired
	MedewerkerService s;
	
	@GetMapping("/checkit")
	public String test() {
		s.inService();
		return("het werkt");
	}
}
