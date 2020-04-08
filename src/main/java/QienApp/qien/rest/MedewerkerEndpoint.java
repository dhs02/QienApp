package QienApp.qien.rest;
import QienApp.qien.controller.OpdrachtgeverService;
import QienApp.qien.domein.Opdrachtgever;
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

import QienApp.qien.controller.MedewerkerRepository;
import QienApp.qien.controller.MedewerkerService;
import QienApp.qien.domein.Medewerker;

import java.util.Optional;

@RestController
@RequestMapping("/api/medewerkers")
public class MedewerkerEndpoint {
	@Autowired
	MedewerkerService medewerkerService;
	@Autowired
	MedewerkerRepository medewerkerRepository;

	@Autowired
	private OpdrachtgeverService opdrachtgeverService;
	
	@GetMapping("/")
	public Iterable<Medewerker> verkrijgMedewerkers() {
		return medewerkerService.getAllMedewerkers();
	}
	@GetMapping("/{id}")
	public Medewerker verkrijgMedewerker(@PathVariable(value = "id") String medewerkerId) {
		return medewerkerService.getMedewerkerById(Long.parseLong(medewerkerId));
	}
	@PutMapping("/opdrachtgever/{mwid}/{wgid}") // @PostMapping alleen met objecten meesturen
	public void toevoegenOpdrachtgever(@PathVariable(value = "mwid") String medewerkerId, @PathVariable(value="wgid") String opdrachtgeverId) {
		medewerkerService.addOpdrachtgever(Long.parseLong(medewerkerId), Long.parseLong(opdrachtgeverId));
	}
	@PutMapping("/contactpersoon/{mwid}/{cpid}")
	public void toevoegenContactpersoon(@PathVariable(value = "mwid") String medewerkerId, @PathVariable(value="cpid") String contactpersoonId) {
		medewerkerService.addContactpersoon(Long.parseLong(medewerkerId), Long.parseLong(contactpersoonId));
	}
	@PostMapping("/")
	public Medewerker toevoegenMedewerker(@RequestBody Medewerker medewerker) {
		if (medewerker.getOpdrachtgever() == null) {
			Optional<Opdrachtgever> defaultOpdrachtgever = this.opdrachtgeverService.findByBedrijfsnaam(Opdrachtgever.DEFAULT_BEDRIJFSNAAM);
			if (defaultOpdrachtgever.isPresent()) {
				medewerker.setOpdrachtgever(defaultOpdrachtgever.get());
			}
		}
		return medewerkerService.addMedewerker(medewerker);
	}
	@DeleteMapping("/{id}")
	public void verwijderMedewerker(@PathVariable(value = "id") String medewerkerId) {
		medewerkerService.deleteMedewerker(Long.parseLong(medewerkerId));
	}
	@PutMapping("/{id}")
	public Medewerker vernieuwMedewerker(@PathVariable(value = "id") String medewerkerId, @RequestBody Medewerker medewerkerDetails) {
		return medewerkerService.updateMedewerker(Long.parseLong(medewerkerId), medewerkerDetails);
	}
	@PostMapping("/maakMedewerkerenKoppelOpdrachtgever/{wgid}")
	public Medewerker toevoegenMedewerkerMetOpdrachtgever(@RequestBody Medewerker medewerker, @PathVariable(value = "wgid") String opdrachtgeverId){
		Medewerker nieuweMedewerker = medewerkerService.addMedewerker(medewerker);
		medewerkerService.addOpdrachtgever(nieuweMedewerker.getId(), Long.parseLong(opdrachtgeverId));
		return nieuweMedewerker;
	}

	@GetMapping("/me")
	public Medewerker getIngelogdeMedewerker(Authentication authentication) {
		checkAuth(authentication);
		return (Medewerker) ((GebruikerPrincipal) authentication.getPrincipal()).getGebruiker();
	}

	@PutMapping("/me")
	public Medewerker updateIngelogdeMedewerker(Authentication authentication, @RequestBody Medewerker medewerkerDetails) {
		checkAuth(authentication);
		Medewerker me = (Medewerker) ((GebruikerPrincipal) authentication.getPrincipal()).getGebruiker();
		return this.medewerkerService.updateMedewerker(me.getId(), medewerkerDetails);
	}

	private static void checkAuth(Authentication authentication) {
		if (authentication == null
				|| !(authentication.getPrincipal() instanceof Medewerker)) {
			throw new AuthenticationCredentialsNotFoundException("No or incorrect credentials supplied.");
		}
	}
}