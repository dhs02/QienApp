package QienApp.qien.rest;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import QienApp.qien.controller.GebruikerService;
import QienApp.qien.domein.Gebruiker;

@RestController
public class GebruikerEndpoint {
	@Autowired
	GebruikerService gebruikerService;
	
	@GetMapping("/gebruikers/achternaam/{achternaam}") 
	public List<Gebruiker> zoekAchternaam(@PathVariable(value="achternaam") String achternaam) {
		return gebruikerService.findByAchternaam(achternaam);
	}
	
	@GetMapping("/gebruikers/voornaam/{voornaam}") 
	public Optional<Gebruiker> zoekVoornaam(@PathVariable(value="voornaam") String voornaam) {
		return gebruikerService.findByVoornaam(voornaam);
	}

	@GetMapping("/gebruikers")
	public Iterable<Gebruiker> verkrijgGebruikers() {
		return gebruikerService.getAllGebruikers();
	}
	@GetMapping("/gebruikers/{id}")
	public Gebruiker verkrijgGebruiker(@PathVariable(value = "id") String gebruikerId) {
		return gebruikerService.getGebruikerById(Long.parseLong(gebruikerId));
	}
	@PostMapping("/gebruikers")
	public Gebruiker toevoegenGebruiker(@RequestBody Gebruiker gebruiker) {
		return gebruikerService.addGebruiker(gebruiker);
	}
	@DeleteMapping("/gebruikers/{id}")
	public void verwijderGebruiker(@PathVariable(value = "id") String gebruikerId) {
		gebruikerService.deleteGebruiker(Long.parseLong(gebruikerId));
	}
	@PutMapping("/gebruikers/{id}")
	public Gebruiker vernieuwGebruiker(@PathVariable(value = "id") String gebruikerId, @RequestBody Gebruiker gebruikerDetails) {
		return gebruikerService.updateGebruiker(Long.parseLong(gebruikerId), gebruikerDetails);
	}
}