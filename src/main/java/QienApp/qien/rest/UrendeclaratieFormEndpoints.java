package QienApp.qien.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import QienApp.qien.controller.MedewerkerRepository;
import QienApp.qien.controller.form.GewerkteDagService;
import QienApp.qien.controller.form.UrenDeclaratieService;
import QienApp.qien.domein.Medewerker;
import QienApp.qien.domein.urenform.GewerkteDag;
import QienApp.qien.domein.urenform.Urendeclaratie;

@RestController
@RequestMapping
public class UrendeclaratieFormEndpoints {
	
	//ik ben vrienden met deze lui:
		@Autowired
		UrenDeclaratieService urenDeclaratieService;
		@Autowired
		GewerkteDagService dagService;
		@Autowired
		MedewerkerRepository medewerkerRepository;

	/*
	 * Urendeclaratie ENDPOINTS
	 * 
	 * CONSUMES
	 * maandnaam
	 * maandnr
	 * via JSON uit Pathvariable
	 * 
	 * PRODUCES
	 * 1 met GewerkteDagen populated UrendeclaratieFormulier per Medewerker in de database
	 * 
	 */
	@PostMapping("/urendeclaratie/{maandnaam}/{maandnr}")
	public void createAndAddUniqueUrendeclaratieToMedewerkers(@PathVariable(value = "maandnaam") String maandNaam, 
							@PathVariable(value = "maandNr") int maandNr) {
		for (Medewerker persoon: medewerkerRepository.findAll()) {
			urenDeclaratieService.maakUrendeclaratieForm(maandNaam, maandNr, persoon);
		}
	}

	@GetMapping("/urendeclaraties")
	public Iterable<Urendeclaratie> getUrendeclaraties() {
		return urenDeclaratieService.getAllUrendeclaraties();
	}

	/*
	 * GewerkteDag ENDPOINTS
	 * 
	 * TODO: Maurice, hoe werkt deze precies?
	 * is dit endpoint om de dag in te vullen?
	 */
	@PutMapping("/gewerktedag/{dagId}")
	public GewerkteDag updatePersoonDrDag(@PathVariable(value = "dagId") String dagId,
			@RequestBody GewerkteDag dagDetails) {
		return dagService.updateDag(Long.parseLong(dagId), dagDetails);
	}

}
