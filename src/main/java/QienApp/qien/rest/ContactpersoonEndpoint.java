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

import QienApp.qien.controller.ContactpersoonService;
import QienApp.qien.domein.Contactpersoon;

@RestController
@RequestMapping("/api/contactpersonen")
public class ContactpersoonEndpoint {
	@Autowired
	ContactpersoonService contactpersoonService;

	@GetMapping("/")
	public Iterable<Contactpersoon> verkrijgContactpersonen() {
		return contactpersoonService.getAllContactpersonen();
	}
	@GetMapping("/{id}")
	public Contactpersoon verkrijgContactpersoon(@PathVariable(value = "id") String contactpersoonId) {
		return contactpersoonService.getContactpersoonById(Long.parseLong(contactpersoonId));
	}
	@GetMapping("/opdrachtgever/{cpid}/{ogid}") // @PostMapping alleen met objecten meesturen
	public void toevoegenOpdrachtgever(@PathVariable(value = "cpid") String contactpersoonId, @PathVariable(value="ogid") String opdrachtgeverId) {
		contactpersoonService.addOpdrachtgever(Long.parseLong(contactpersoonId), Long.parseLong(opdrachtgeverId));
	}
	@PostMapping("/")
	public Contactpersoon toevoegenContactpersoon(@RequestBody Contactpersoon contactpersoon) {
		return contactpersoonService.addContactpersoon(contactpersoon);
	}
	@DeleteMapping("/{id}")
	public void verwijderContactpersoon(@PathVariable(value = "id") String contactpersoonId) {
		contactpersoonService.deleteContactpersoon(Long.parseLong(contactpersoonId));
	}
	@PutMapping("/{id}")
	public Contactpersoon vernieuwContactpersoon(@PathVariable(value = "id") String contactpersoonId, @RequestBody Contactpersoon contactpersoonDetails) {
		return contactpersoonService.updateContactpersoon(Long.parseLong(contactpersoonId), contactpersoonDetails);
	}
	
	@PostMapping("/{ogid}")
	public Contactpersoon toevoegenContactpersoonMetOpdrachtgever(@PathVariable(value = "ogid") String opdrachtgeverId, @RequestBody Contactpersoon contactpersoon){
		return contactpersoonService.toevoegenContactpersoonMetOpdrachtgever(opdrachtgeverId, contactpersoon);
	}	
}