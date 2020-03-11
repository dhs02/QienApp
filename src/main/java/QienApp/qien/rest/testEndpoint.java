package QienApp.qien.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import QienApp.qien.domein.Medewerker;

@RestController
public class testEndpoint {
	@PostMapping("/kijkmaar")
	public String test(@RequestBody Medewerker medewerker) {
System.out.println("hij doet het!!");
		return("het werkt, echt waar");
	}
}
