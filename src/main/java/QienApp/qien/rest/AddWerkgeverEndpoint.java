package QienApp.qien.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import QienApp.qien.domein.Werkgever;
import QienApp.qien.controller.WerkgeverService;

@RestController
public class AddWerkgeverEndpoint {

	@Autowired
	WerkgeverService w;
	
	@PostMapping("/toevoegenwerkgever")
	public Werkgever toevoegenWerkgever(@RequestBody Werkgever werkgever) {
		System.out.println("werkgevertoevoegingsmethode...go!");
		w.inService(werkgever);
		return(werkgever); 
	}
}
