package QienApp.qien.rest;
import java.util.List;

import QienApp.qien.security.domein.GebruikerPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import QienApp.qien.controller.GebruikerService;
import QienApp.qien.domein.Gebruiker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/gebruikers")
@Api(tags = "GebruikerEndPoint", description = "REST APIs gerelateerd aan Gebruiker-entiteit.")
public class GebruikerEndpoint {
	@Autowired
	GebruikerService gebruikerService;
	
	@GetMapping("/voornaam/{voornaam}") 
	public List<Gebruiker> zoekVoornaam(@PathVariable(value="voornaam") String voornaam) {
		return gebruikerService.findByVoornaam(voornaam);
	}
	@GetMapping("/achternaam/{achternaam}") 
	public List<Gebruiker> zoekAchternaam(@PathVariable(value="achternaam") String achternaam) {
		return gebruikerService.findByAchternaam(achternaam);
	}
	
	@GetMapping("/me")
	// Geeft de huidige gebruiker terug. De gebruiker moet dan wel zijn / haar
	// gebruikersnaam en wachtwoord als HTTP Basic header toegevoegd bij de
	// GET request meesturen.
	//
	// In Postman kun je dit testen door onder het tabblad "Authorization" de
	// optie "Basic Auth" te selecteren en het emailadres en wachtwoord van een
	// bestaande gebruiker in te vullen.
	public Gebruiker getIngelogdeGebruiker(Authentication authentication) {
		if (authentication == null) {
			throw new AuthenticationCredentialsNotFoundException("Credentials "
			+ "not found.");
		}

		GebruikerPrincipal gebruikerPrincipal
				= (GebruikerPrincipal) authentication.getPrincipal();
		Gebruiker gebruiker = gebruikerPrincipal.getGebruiker();
		return gebruiker;
	}

	@GetMapping("/")
	public Iterable<Gebruiker> verkrijgGebruikers() {
		return gebruikerService.getAllGebruikers();
	}
	
	@ApiOperation(value = "Verkrijg een gebruiker.", notes = "Verkrijg een gebruiker uit de Database met het gespecificeerde ID.", response = Gebruiker.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Gebruiker succesvol verkregen."),
					@ApiResponse(code = 404, message = "Gebruiker kon niet worden verkregen") })
	@GetMapping("/{id}")
	public Gebruiker verkrijgGebruiker(@ApiParam(required = true, name = "id", value = "Gebruiker-ID", type = "String", example="10") @PathVariable(value = "id") String gebruikerId) {
		return gebruikerService.getGebruikerById(Long.parseLong(gebruikerId));
	}
	@PostMapping("/")
	public Gebruiker toevoegenGebruiker(@RequestBody Gebruiker gebruiker) {
		return gebruikerService.addGebruiker(gebruiker);
	}
	@DeleteMapping("/{id}")
	public void verwijderGebruiker(@PathVariable(value = "id") String gebruikerId) {
		gebruikerService.deleteGebruiker(Long.parseLong(gebruikerId));
	}
	@PutMapping("/{id}")
	public Gebruiker vernieuwGebruiker(@PathVariable(value = "id") String gebruikerId, @RequestBody Gebruiker gebruikerDetails) {
		return gebruikerService.updateGebruiker(Long.parseLong(gebruikerId), gebruikerDetails);
	}
	
	@GetMapping("/getByEmail/{email}")
	public Gebruiker getByEmail(@PathVariable(value = "email") String email) {
		return gebruikerService.getByEmail(email);
	}
}