package QienApp.qien.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import QienApp.qien.controller.MedewerkerRepository;
import QienApp.qien.controller.urenform.GewerkteDagService;
import QienApp.qien.controller.urenform.UrenDeclaratieRepository;
import QienApp.qien.controller.urenform.UrenDeclaratieService;
import QienApp.qien.domein.Medewerker;
import QienApp.qien.domein.urenform.GewerkteDag;
import QienApp.qien.domein.urenform.Urendeclaratie;

@RestController
@RequestMapping
public class UrendeclaratieEndpoints {
	
	//ik ben vrienden met deze lui:
		@Autowired
		UrenDeclaratieService urenDeclaratieService;
		@Autowired
		GewerkteDagService dagService;
		@Autowired
		MedewerkerRepository medewerkerRepository;
		@Autowired
		UrenDeclaratieRepository urenDeclaratieRepository;

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
	@PostMapping("/urendeclaraties/{maandnaam}/{maandnr}")
	public void createAndAddUniqueUrendeclaratieToMedewerkers(@PathVariable(value = "maandnaam") String maandNaam, 
							@PathVariable(value = "maandnr") int maandNr) {
		for (Medewerker persoon: medewerkerRepository.findAll()) {
			urenDeclaratieService.maakUrendeclaratieForm(maandNaam, maandNr, persoon);
		}
	}
	
	// ALS DEVELOPER KAN IK EEN URENDECLARATIE INVULLEN
//	@PutMapping("/urendeclaraties/{formId}")
//	public Urendeclaratie updateUrenForm(@PathVariable(value = "formId") Long formId,
//			@RequestBody Urendeclaratie urendDeclaratieDetails) {
//		Urendeclaratie u = urenDeclaratieRepository.findById(formId).get();
//		
//		for (GewerkteDag dag : u.getGewerkteDagen() ) {
//			dag.setAantalUrenOpdracht(urendDeclaratieDetails.getAantalUrenOpdracht());
//			dag.setAantalUrenOverig(dagDetails.getAantalUrenOverig());
//			dag.setAantalUrenOverwerk(dagDetails.getAantalUrenOverwerk());
//			dag.setAantalUrenTraining(dagDetails.getAantalUrenTraining());
//			dag.setAantalUrenVerlof(dagDetails.getAantalUrenVerlof());
//			dag.setAantalUrenZiek(dagDetails.getAantalUrenZiek());
//			dag.setVerklaringOverig(dagDetails.getVerklaringOverig());
//
//			return gewerkteDagRepository.save(dag);
//		return urenDeclaratieService.updateUrendeclaratie(Long.parseLong(formId), urendDeclaratieDetails);
//	}
	
	@PostMapping("/urendeclaratie/{maandnaam}/{maandnr}")
	public void maakLegeUrendeclaratie(@PathVariable(value = "maandnaam") String maandNaam, 
							@PathVariable(value = "maandnr") int maandNr) {
	
			urenDeclaratieService.maakUrendeclaratieForm(maandNaam, maandNr);
		}

	@GetMapping("/urendeclaraties")
	public Iterable<Urendeclaratie> getUrendeclaraties() {
		return urenDeclaratieService.getAllUrendeclaraties();
	}

	/*
	 * GewerkteDag ENDPOINTS
	 */
	@PutMapping("/gewerktedag/{dagId}")
	public GewerkteDag updatePersoonDrDag(@PathVariable(value = "dagId") String dagId,
			@RequestBody GewerkteDag dagDetails) {
		return dagService.updateDag(Long.parseLong(dagId), dagDetails);
	}
	


}
