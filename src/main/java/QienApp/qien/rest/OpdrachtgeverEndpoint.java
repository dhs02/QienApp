package QienApp.qien.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import QienApp.qien.domein.Opdrachtgever;

import QienApp.qien.controller.OpdrachtgeverService;

@RestController
public class OpdrachtgeverEndpoint {
	@Autowired
	OpdrachtgeverService opdrachtgeverService;
	
	@PostMapping("/opdrachtgevers")
	public Opdrachtgever toevoegenOpdrachtgever(@RequestBody Opdrachtgever opdrachtgever) {
		return opdrachtgeverService.addOpdrachtgever(opdrachtgever);
	}
}