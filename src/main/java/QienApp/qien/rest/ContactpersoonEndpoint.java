package QienApp.qien.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import QienApp.qien.controller.ContactpersoonService;
import QienApp.qien.domein.Contactpersoon;

@RestController
public class ContactpersoonEndpoint {
	@Autowired
	ContactpersoonService contactpersoonService;

	@GetMapping("/gebruikers/contactpersonen")
	public Iterable<Contactpersoon> verkrijgContactpersonen() {
		return contactpersoonService.getAllContactpersonen();
	}
	@GetMapping("/gebruikers/contactpersonen/{id}")
	public Contactpersoon verkrijgContactpersoon(@PathVariable(value = "id") String contactpersoonId) {
		return contactpersoonService.getContactpersoonById(Long.parseLong(contactpersoonId));
	}
	@PostMapping("/gebruikers/contactpersonen")
	public Contactpersoon toevoegenContactpersoon(@RequestBody Contactpersoon contactpersoon) {
		return contactpersoonService.addContactpersoon(contactpersoon);
	}
	@DeleteMapping("/gebruikers/contactpersonen/{id}")
	public void verwijderContactpersoon(@PathVariable(value = "id") String contactpersoonId) {
		contactpersoonService.deleteContactpersoon(Long.parseLong(contactpersoonId));
	}
	@PatchMapping("/gebruikers/contactpersonen/{id}")
	public Contactpersoon vernieuwContactpersoon(@PathVariable(value = "id") String contactpersoonId, @RequestBody Contactpersoon contactpersoonDetails) {
		return contactpersoonService.updateContactpersoon(Long.parseLong(contactpersoonId), contactpersoonDetails);
	}
}