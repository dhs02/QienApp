package QienApp.qien.rest;

import QienApp.qien.controller.OpdrachtgeverService;
import QienApp.qien.security.domein.GebruikerPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import QienApp.qien.controller.ContactpersoonService;
import QienApp.qien.domein.Contactpersoon;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/contactpersonen")
public class ContactpersoonEndpoint {
	@Autowired
	ContactpersoonService contactpersoonService;

	@Autowired
	private OpdrachtgeverService opdrachtgeverService;

	@GetMapping("/")
	public Iterable<Contactpersoon> verkrijgContactpersonen() {
		return contactpersoonService.getAllContactpersonen();
	}
	@GetMapping("/{id}")
	public Contactpersoon verkrijgContactpersoon(@PathVariable(value = "id") String contactpersoonId) {
		return contactpersoonService.getContactpersoonById(Long.parseLong(contactpersoonId));
	}

	@GetMapping("/opdrachtgever/{opdrachtgeverId}")
	public void getAllContactpersonenByOpdrachtgever(HttpServletResponse response,
													 @PathVariable Long opdrachtgeverId) {
		try {
			response.sendRedirect("/api/opdrachtgevers/" + opdrachtgeverId + "/contactpersonen");
		}
		catch (IOException ioe) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Redirect failed.");
		}
	}

	@PutMapping("/opdrachtgever/{cpid}/{ogid}") // @PostMapping alleen met objecten meesturen
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

	@GetMapping("/me")
	public Contactpersoon getIngelogdeContactpersoon(Authentication authentication) {
		checkAuth(authentication);
		return (Contactpersoon) ((GebruikerPrincipal) authentication.getPrincipal()).getGebruiker();
	}

	@PutMapping("/me")
	public Contactpersoon updateIngelogdeContactpersoon(Authentication authentication, @RequestBody Contactpersoon contactpersoonDetails) {
		checkAuth(authentication);
		Contactpersoon me = (Contactpersoon) ((GebruikerPrincipal) authentication.getPrincipal()).getGebruiker();
		return this.contactpersoonService.updateContactpersoon(me.getId(), contactpersoonDetails);
	}

	private static void checkAuth(Authentication authentication) {
		if (authentication == null
				|| !(authentication.getPrincipal() instanceof Contactpersoon)) {
			throw new AuthenticationCredentialsNotFoundException("No or incorrect credentials supplied.");
		}
	}
}