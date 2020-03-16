package QienApp.qien.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import QienApp.qien.controller.MedewerkerService;
import QienApp.qien.domein.Medewerker;

@RestController
public class MedewerkerEndpoint {
	@Autowired
	MedewerkerService medewerkerService;

	@GetMapping("/gebruikers/medewerkers")
	public Iterable<Medewerker> verkrijgMedewerkers() {
		return medewerkerService.getAllMedewerkers();
	}
	@GetMapping("/gebruikers/medewerkers/{id}")
	public Medewerker verkrijgMedewerker(@PathVariable(value = "id") String medewerkerId) {
		return medewerkerService.getMedewerkerById(Long.parseLong(medewerkerId));
	}
	@PostMapping("/gebruikers/medewerkers")
	public Medewerker toevoegenMedewerker(@RequestBody Medewerker medewerker) {
		return medewerkerService.addMedewerker(medewerker);
	}
	@GetMapping("/gebruikers/medewerkers/{mwid}/{wgid}") // @PostMapping alleen met objecten meesturen
	public void toevoegenOpdrachtgever(@PathVariable(value = "mwid") String medewerkerId, @PathVariable(value="wgid") String opdrachtgeverId) {
		medewerkerService.addOpdrachtgever(Long.parseLong(medewerkerId), Long.parseLong(opdrachtgeverId));
	}
	@DeleteMapping("/gebruikers/medewerkers/{id}")
	public void verwijderMedewerker(@PathVariable(value = "id") String medewerkerId) {
		medewerkerService.deleteMedewerker(Long.parseLong(medewerkerId));
	}
	@PatchMapping("/gebruikers/medewerkers/{id}")
	public Medewerker vernieuwMedewerker(@PathVariable(value = "id") String medewerkerId, @RequestBody Medewerker medewerkerDetails) {
		return medewerkerService.updateMedewerker(Long.parseLong(medewerkerId), medewerkerDetails);
	}
}