package QienApp.qien.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import QienApp.qien.domein.Opdrachtgever;

import QienApp.qien.controller.OpdrachtgeverService;

@RestController
public class AddOpdrachtgeverEndpoint {

	@Autowired
	OpdrachtgeverService w;
	
	@PostMapping("/toevoegenopdrachtgever")
	public Opdrachtgever toevoegenWerkgever(@RequestBody Opdrachtgever opdrachtgever) {
		System.out.println("werkgevertoevoegingsmethode...go!");
		w.inService(opdrachtgever);
		return(opdrachtgever); 
	}
}