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

	//LASZLO ADDED
	@PostMapping("/")
	public Urendeclaratie addUrendeclaratie(@RequestBody Urendeclaratie urendeclaratie) {
		System.out.println("post urendeclaratie");
		urenDeclaratieService.createUrendeclaratie(urendeclaratie);
		return urendeclaratie;
	}	

	/** MICHIEL ADDED, VARIATIE OP LOSZALO's HIERBOVEN
	 * MAAKT EEN GEVULD URENFORM VOOR AANLEVERENDE MEDEWERKER
	 * 
	 * @REQUESTBODY urendeclaratie_in	gevuld urendeclaratie object uit json
	 * @PATHVARIABLE medewerkerid			
	 * @RETURN urendeclaratie			het ingevuld urendecl van deze medewerker
	 */
	@PostMapping("/{medewerkerid}")
	public Urendeclaratie addUrendeclaratie2(
			@RequestBody Urendeclaratie urendeclaratie_in, 
			@PathVariable(value="medewerkerid") Long medewerkerid) {
		//haal medewerker op
		Medewerker m = medewerkerRepository.findById(medewerkerid).get();
		//maak urendeclaratie van binnenkomend object & voeg toe aan medewerker		
		return m.addUrendeclaratie(urenDeclaratieService.createOrUpdateUrendeclaratie(urendeclaratie_in));
	}
	
	/** MICHIEL ADDED
	 * MAAKT EEN LEEG URENFORM VOOR AANLEVERENDE MEDEWERKER
	 * 
	 * @PATHVARIABLE medewerkerid			
	 * @RETURN urendeclaratie			het leeg urendecl van deze medewerker
	 */
	@PostMapping("/")
	public Urendeclaratie addEmptyUrendeclaratie(
			@PathVariable(value="medewerkerid") Long medewerkerid) {
		//haal medewerker op
		Medewerker m = medewerkerRepository.findById(medewerkerid).get();
		//maak urendeclaratie van binnenkomend object & voeg toe aan medewerker		
		return m.addUrendeclaratie(urenDeclaratieService.createUrendeclaratie());
	}
	



	@GetMapping("/{id}")	
	public Urendeclaratie addUrendeclaratie(@PathVariable(value = "id") String idUrendeclaratie) {
		System.out.println("getUrendeclaratie");
		return urenDeclaratieService.getUrendeclaraties(Long.parseLong(idUrendeclaratie));
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
