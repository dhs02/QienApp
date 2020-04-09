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
import QienApp.qien.controller.MedewerkerService;
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
	MedewerkerService medewerkerService;
	@Autowired
	UrenDeclaratieRepository urenDeclaratieRepository;
	/** 
	 * 		BASIC GETS
	 */
	@GetMapping("/")			//	GET ALL UDECS
	public Iterable<Urendeclaratie> getUrendeclaraties() {
		return urenDeclaratieService.getAllUrendeclaraties();
	}

	/**
	 * 							// DEZE 2 VOOR DE MAIL LINK VAN DE CONTACTPERSOON
	 * @param idUrendeclaratie
	 * @return
	 */
	@GetMapping("/{uid}")		//	GET 1 BY UDEC ID
	public Urendeclaratie getUrendeclaratie(@PathVariable(value = "uid") String idUrendeclaratie) 
	{
		return urenDeclaratieService.getUrendeclaratie(Long.parseLong(idUrendeclaratie));
	}
	/**
	 * 							// UPDATE UDEC adh van UID
	 * @param idUrendeclaratie
	 * @return
	 */
	@PutMapping("/{uid}")		//	GET 1 BY UDEC ID
	public Urendeclaratie updateUrendeclaratie(@PathVariable(value = "uid") String idUrendeclaratie) 
	{
		return urenDeclaratieService.getUrendeclaratie(Long.parseLong(idUrendeclaratie));
	}
	
	/**
	 * GET MEDEWERKERINFO BY IUD
	 * @param idUrendeclaratie
	 * @return de info
	 * @author QienDevelopers
	 */
	@GetMapping("/medewerkerinfo/{uid}")	
	public Medewerker getMedewerkerInfo(@PathVariable(value = "uid") String idUrendeclaratie) 
	{	
		return urenDeclaratieService
				.getUrendeclaratie(Long.parseLong(idUrendeclaratie))
				.getMedewerker();
	}

	/** 2e versnelling RepZoek;	//	GET ALL BY maandNaam
	 * @param maandNaam
	 * @return lijst Udecs.
	 */
	@GetMapping("/byMaandNaam/{maandNaam}")
	public List<Urendeclaratie> byMaandNaam(@PathVariable(value = "maandNaam") String maandNaam) {
		return urenDeclaratieService.byMaandNaam(maandNaam);
	}
	/** 1 * 					//	GET ALL by Medwerker ID
	 * @param id
	 * @return list udecs
	 */
	@GetMapping("/metmedewerkerid/{mwid}")
	public java.util.Set<Urendeclaratie> getMedewerkerUrendeclaratie(@PathVariable Long mwid) {
		return medewerkerRepository.findById(mwid).get().getUrendeclaraties();
	}
	/** 
	 *		 CREATE UDECS
	 */

	/** TESTESTEST DOET HET NIET!!!
	 * 							//	CREATE NEW FOR ALL Medewerkers
	 * @param urendeclaratie object
	 * @return statusbericht: gekoppeld
	 * @AUTHOR Laszlo & Michiel!!!
	 */
	@GetMapping("/maakenkoppelaanallen/{maandnaam}/{maandnr}")
	public String maakLegeUrendeclaratieEnKoppelAanAllen(@PathVariable(value = "maandnaam") String maandNaam,@PathVariable(value = "maandnr") int maandNr) {
		return urenDeclaratieService.maakEnKoppelAanAllen(maandNaam, maandNr);
	}

	/**		 					
	 * 							// UPDATE EXISTING or CREATE NEW URENDECLARATIE by UDEC body
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
		Gebruiker g = ((GebruikerPrincipal) authentication.getPrincipal()).getGebruiker();

		/*  Een Medewerker mag alleen een urendeclaratie voor zichzelf
		 *  POSTen of PUTten.
		 */
		if (g instanceof Medewerker) {
			Medewerker m = (Medewerker) g;
			u.setMedewerker(m);
		}

		return urenDeclaratieService.postOrUpdateUrendeclaratie(u);
	}
	/** 						
	 * 						// CREATE 1 UDEC BY maandNaam, maandNr
	 * @param	String maandNaam, int maandNr
	 * @return	udec
	 */
	@GetMapping("/maakurendeclaratie/{maandnaam}/{maandnr}")
	public Urendeclaratie maakLegeUrendeclaratie(@PathVariable(value = "maandnaam") String maandNaam, @PathVariable(value = "maandnr") int maandNr) {
		return urenDeclaratieService.maakUrendeclaratieForm(maandNaam, maandNr);
	}

	/**
	 * UPDATE STUFF // 
	 */


	/**
	 * 							//	UPDATE 1 GewerkteDag
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
//		@GetMapping("/koppelaanmedewerker/{udid}/{mwid}")
//		public Urendeclaratie koppelZe(@PathVariable(value = "udid") String udid, @PathVariable(value = "mwid") String mwid){
//			return urenDeclaratieService.koppelFormAanMedewerker(Long.parseLong(udid), Long.parseLong(mwid));
//		}
}