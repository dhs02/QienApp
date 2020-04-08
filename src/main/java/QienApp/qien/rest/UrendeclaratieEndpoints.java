package QienApp.qien.rest;

import java.util.List;

import QienApp.qien.domein.Gebruiker;
import QienApp.qien.domein.Medewerker;
import QienApp.qien.security.domein.GebruikerPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import QienApp.qien.domein.urenform.GewerkteDag;
import QienApp.qien.domein.urenform.Urendeclaratie;

@RestController
@RequestMapping("/api/urendeclaraties")
public class UrendeclaratieEndpoints {
	@Autowired
	UrenDeclaratieService urenDeclaratieService;
	
	@Autowired
	GewerkteDagService dagService;
	
	@Autowired
	MedewerkerRepository medewerkerRepository;
	
	@Autowired
	UrenDeclaratieRepository urenDeclaratieRepository;

	@GetMapping("/")
	public Iterable<Urendeclaratie> getUrendeclaraties() {
		return urenDeclaratieService.getAllUrendeclaraties();
	}
	
	@GetMapping("/{id}")	
	public Urendeclaratie getUrendeclaratie(@PathVariable(value = "id") String idUrendeclaratie) {
		System.out.println("getUrendeclaratie");
		return urenDeclaratieService.getUrendeclaratie(Long.parseLong(idUrendeclaratie));
	}
	
	@GetMapping("/maakurendeclaratie/{maandnaam}/{maandnr}")
	public Urendeclaratie maakLegeUrendeclaratie(@PathVariable(value = "maandnaam") String maandNaam, @PathVariable(value = "maandnr") int maandNr) {
		return urenDeclaratieService.maakUrendeclaratieForm(maandNaam, maandNr);
	}
	
	/** 2e versnelling RepZoek; get alle udecs BY maandnaam
	 * @param maandNaam
	 * @return lijst Udecs.
	 */
	@GetMapping("/byMaandNaam/{maandNaam}")
	public List<Urendeclaratie> byMaandNaam(@PathVariable(value = "maandNaam") String maandNaam) {
		return urenDeclaratieService.byMaandNaam(maandNaam);
	}

	/** 1 * HAAL ALLE URENDECs VAN 1 MEDEWERKER
	 * @param id
	 * @return
	 */
	@GetMapping("/metmedewerkerid/{mwid}")
	public java.util.Set<Urendeclaratie> getMedewerkerUrendeclaratie(@PathVariable Long mwid) {
		return medewerkerRepository.findById(mwid).get().getUrendeclaraties();
	}
	
	
	/** 1 * UPDATE EXISTING or CREATE NEW URENDECLARATIE
	 * 1e.als er een ID meekomt, 2e. als er geen ID meekomt
	 * @param Urendeclaratie object
	 * @return het nieuwe Urendeclaratie object
	 */
	@PostMapping
	public Urendeclaratie postUrendeclaratieMethode(@RequestBody Urendeclaratie u, Authentication authentication) {
		return this.urenDeclaratieMethode(u, authentication);
	}

	@PutMapping("/")
	public Urendeclaratie urenDeclaratieMethode(@RequestBody Urendeclaratie u,
												Authentication authentication) {
		if (authentication != null) {
			Gebruiker g = ((GebruikerPrincipal) authentication.getPrincipal()).getGebruiker();
			if (g instanceof Medewerker) {
				Medewerker m = (Medewerker) g;
				u.setMedewerker(m);
			}

		}
		return urenDeclaratieService.postOrUpdateUrendeclaratie(u);
	}



	/** 3 * koppel een nieuw gegenereerd leeg form aan alle medewerkers
	 * 
	 * @param urendeclaratie object
	 * @return statusbericht: gekoppeld
	 * @AUTHOR Laszlo & Michiel!!!
	 */
	@PostMapping("/maakenkoppelaanallen/{maandnaam}/{maandnr}")
	public String maakLegeUrendeclaratieEnKoppelAanAllen(@PathVariable(value = "maandnaam") String maandNaam, @PathVariable(value = "maandnr") int maandNr) {
		return urenDeclaratieService.maakEnKoppelAanAllen(maandNaam, maandNr);
	}

	/** 7 * UPDATE 1 GewerkteDag
	 * 
	 */
	@PutMapping("/gewerktedag/{dagId}")
	public GewerkteDag updatePersoonDrDag(@PathVariable(value = "dagId") String dagId, @RequestBody GewerkteDag dagDetails) {
		return dagService.updateDag(Long.parseLong(dagId), dagDetails);
	}
	
	//=======================================================>> welicht overbodige functionaliteiten
		/** 6 *	koppel een form aan een specifieke medewerker
		 * 
		 * @param formId
		 * @param medewerkerId
		 * @return gekoppeld formulier
		 */
//	@GetMapping("/koppelaanmedewerker/{udid}/{mwid}")
//	public Urendeclaratie koppelZe(@PathVariable(value = "udid") String udid, @PathVariable(value = "mwid") String mwid){
//		return urenDeclaratieService.koppelFormAanMedewerker(Long.parseLong(udid), Long.parseLong(mwid));
//	}
}