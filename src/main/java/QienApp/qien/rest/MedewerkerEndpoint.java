package QienApp.qien.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import QienApp.qien.controller.MedewerkerService;
import QienApp.qien.domein.Medewerker;

@RestController
@RequestMapping("/api/medewerkers") // LASZLO KAN DIT medewerker enkelvoud worden? wat intuïtiever
public class MedewerkerEndpoint {
	@Autowired
	MedewerkerService medewerkerService;

	@GetMapping("/")
	public Iterable<Medewerker> verkrijgMedewerkers() {
		return medewerkerService.getAllMedewerkers();
	}
	@GetMapping("/{id}")
	public Medewerker verkrijgMedewerker(@PathVariable(value = "id") String medewerkerId) {
		return medewerkerService.getMedewerkerById(Long.parseLong(medewerkerId));
	}
	@GetMapping("/opdrachtgever/{mwid}/{wgid}") // @PostMapping alleen met objecten meesturen
	public void toevoegenOpdrachtgever(@PathVariable(value = "mwid") String medewerkerId, @PathVariable(value="wgid") String opdrachtgeverId) {
		medewerkerService.addOpdrachtgever(Long.parseLong(medewerkerId), Long.parseLong(opdrachtgeverId));
	}
//	@GetMapping("/contactpersoon/{mwid}/{cpid}")
//	public void toevoegenContactpersoon(@PathVariable(value = "mwid") String medewerkerId, @PathVariable(value="cpid") String contactpersoonId) {
//		medewerkerService.addContactpersoon(Long.parseLong(medewerkerId), Long.parseLong(contactpersoonId));
//	}
	@PostMapping("/")
	public Medewerker toevoegenMedewerker(@RequestBody Medewerker medewerker) {
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
}