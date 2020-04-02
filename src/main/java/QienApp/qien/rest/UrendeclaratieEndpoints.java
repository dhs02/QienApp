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
@RequestMapping("/api/urendeclaraties")
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
	
	@GetMapping("/test2/{id}")
	public void getZnUren(@PathVariable(value = "id") long id) {
		urenDeclaratieService.hemZnUrendeclaraties(id);
	}


	/** 1 * UPDATE EXISTING or CREATE NEW URENDECLARATIE
	 * 1e.als er een ID meekomt, 2e. als er geen ID meekomt
	 * @param Urendeclaratie object
	 * @return het nieuwe Urendeclaratie object
	 */
	@PutMapping("/")
	public Urendeclaratie urenDeclaratieMethode(@RequestBody Urendeclaratie u) {
		return urenDeclaratieService.postOrUpdateUrendeclaratie(u);
	}

	/** 2 * maak leeg urendeclaratieformulier
	 * 
	 * TODO iets met ENUM maandnaam en maandnummers
	 * @return		leeg urenform
	 */
	@PostMapping("/{maandnaam}/{maandnr}")
	public Urendeclaratie maakLegeUrendeclaratie(@PathVariable(value = "maandnaam") String maandNaam, 
			@PathVariable(value = "maandnr") int maandNr) 
	{
		return urenDeclaratieService.maakUrendeclaratieForm(maandNaam, maandNr);
	}

	/** 3 * koppel een nieuw gegenereerd leeg form aan alle medewerkers
	 * 
	 * @param urendeclaratie object
	 * @return statusbericht: gekoppeld
	 * @AUTHOR Laszlo & Michiel!!!
	 */
	@PostMapping("/maakenkoppelaanallen/{maandnaam}/{maandnr}")
	public String maakLegeUrendeclaratieEnKoppelAanAllen(@PathVariable(value = "maandnaam") String maandNaam, 
			@PathVariable(value = "maandnr") int maandNr) 
	{
		return urenDeclaratieService.maakEnKoppelAanAllen(maandNaam, maandNr);
	}


	/** 4 * GET 1 Urendeclatarie met zn ID
	 * 
	 * @param idUrendeclaratie
	 * @return de Urendeclaratie
	 */
	@GetMapping("/{id}")	
	public Urendeclaratie getUrendeclaratie(@PathVariable(value = "id") String idUrendeclaratie) 
	{
		System.out.println("getUrendeclaratie");
		return urenDeclaratieService.getUrendeclaraties(Long.parseLong(idUrendeclaratie));
	}
	
	/** 5 * GET ALL URENDECLARATIES
	 * @return iterable met alle urendeclaraties
	 */
	@GetMapping("/")
	public Iterable<Urendeclaratie> getUrendeclaraties() 
	{
		return urenDeclaratieService.getAllUrendeclaraties();
	}
	
	/**
	 * LEGE URENFORM, ZONDER ID
	 * 
	 * @param maandNaam
	 * @param maandNr
	 */
	@PostMapping("/urendeclaratie/{maandnaam}/{maandnr}")
	public void maakLegeUrendeclaratieXXXXX(@PathVariable(value = "maandnaam") String maandNaam, 
			@PathVariable(value = "maandnr") int maandNr) {

		urenDeclaratieService.maakUrendeclaratieForm(maandNaam, maandNr);
	}



	/**
	 * GewerkteDag ENDPOINTS
	 * UPDATE
	 * 
	 */
	@PutMapping("/gewerktedag/{dagId}")
	public GewerkteDag updatePersoonDrDag(@PathVariable(value = "dagId") String dagId,
			@RequestBody GewerkteDag dagDetails) 
	{
		return dagService.updateDag(Long.parseLong(dagId), dagDetails);
	}
	//=======================================================>> welicht overbodige functionaliteiten
		/** 6 *	koppel een form aan een specifieke medewerker
		 * 
		 * @param formId
		 * @param medewerkerId
		 * @return gekoppeld formulier
		 */
		@PutMapping("/koppelaanmedewerker/{formId}/{medewerkerId}")
		public Urendeclaratie koppelZe(@PathVariable(value = "formId") long formId, 
				@PathVariable(value = "medewerkerId") long medewerkerId)
		{
			return urenDeclaratieService.koppelFormAanMedewerker(formId, medewerkerId);
		}
}