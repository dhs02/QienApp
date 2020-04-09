package QienApp.qien.controller.urenform;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QienApp.qien.controller.MedewerkerRepository;
import QienApp.qien.domein.Medewerker;
import QienApp.qien.domein.urenform.GewerkteDag;
import QienApp.qien.domein.urenform.Status;
import QienApp.qien.domein.urenform.Urendeclaratie;

@Service
public class UrenDeclaratieService {
	@Autowired
	private UrenDeclaratieRepository urenDeclaratieRepository;
	@Autowired
	private MedewerkerRepository medewerkerRepository;
	@Autowired
	private GewerkteDagService gewerkteDagService;
	@Autowired
	private GewerkteDagRepository gewerkteDagRepository;

	// VIND ALLE URENDECLARATIEFORMULIEREN
	public Iterable<Urendeclaratie> getAllUrendeclaraties() {
		return urenDeclaratieRepository.findAll();
	}

	/** 2 *
	 * GET ONE URENDECLARATIE
	 * @PARAM id	ID van een specifieke urendeclaratie
	 * @RETURN		de gevraagde urendeclaratie
	 */
	public Urendeclaratie getUrendeclaratie(long id) {
		return urenDeclaratieRepository.findById(id).get();
	}

	/** 2e VERSNELLING zoek met een field
	 * 
	 * @param maandNaam
	 * @return lijst betreffende UD's
	 */
	public List<Urendeclaratie> byMaandNaam(String maandNaam) {
		return urenDeclaratieRepository.findAllBymaandNaam(maandNaam);
	}

	/** 1 *
	 * UPDATEURENDECLARATIE
	 * @param urendDeclaratieDetails	nieuw urendeclaratie object
	 * @return ud						de aangepaste urendeclaratie		
	 */
	public Urendeclaratie postOrUpdateUrendeclaratie(Urendeclaratie urendeclaratie) {
		//MICHIEL dit wordt een status switch
		Status s = urendeclaratie.getStatus();
		switch (s) {
		case BESCHIKBAAR:
			System.out.println("beschikbaar");
			break;
		case TER_GOEDKEURING:
			break;
		case 	GOEDGEKEURD:
			break;

		case 	AFGEKEURD:
			break;
		case 	AFGEROND:
			break;
		default:
			break;
		}
		//MICHIEL nieuwe methode tot hier
		return urenDeclaratieRepository.save(urendeclaratie);
	}
	
	public Urendeclaratie updateUrendeclaratie(Urendeclaratie urendeclaratie) {
		return urenDeclaratieRepository.save(urendeclaratie);
	}

	/** 2 *
	 * MAAK EEN LEEG URENDECLARATIEFORMULIER, gevuld met dagen
	 * @PARAM maandNaam		de naam van de maand waarvoor het formulier wordt aangemaakt
	 * @PARAM maandNr		het nummer van de maand, benodigd voor aantal dagen dat er in komt
	 * @RETURN dezemaand	het lege urenformulier voor deze maand
	 */
	public Urendeclaratie maakUrendeclaratieForm(String maandNaam, int maandNr) 
	{
		// maak nieuw urendeclaratie object
		Urendeclaratie dezemaand = new Urendeclaratie();
		// geef de maand een naam
		dezemaand.setMaandNaam(maandNaam);
		// populate met dagen
		switch(maandNr) {
		case 2:
			for (int x = 0; x < 29; x++) {
				GewerkteDag dag = new GewerkteDag();
				dag.setDagnr(x+1);
				dezemaand.addDagToList(dag);
				gewerkteDagRepository.save(dag);	//TODO: is dit beter dan zoals op regel 66, 73? Ivo, Felix???
			} break;
		case 4: case 6: case 9: case 11:		
			for (int x = 0; x < 30; x++) {
				GewerkteDag dag = new GewerkteDag();
				dag.setDagnr(x+1);
				dezemaand.addDagToList(dag);
				gewerkteDagService.addDagToRepository(dag);
			} break;
		default:
			for (int x = 0; x < 31; x++) {
				GewerkteDag dag = new GewerkteDag();
				dag.setDagnr(x+1);
				dezemaand.addDagToList(dag);
				gewerkteDagService.addDagToRepository(dag);
			} break;
		}
		urenDeclaratieRepository.save(dezemaand);
		return dezemaand;
	}

	/**  
	 * MAAK & KOPPEL EEN LEEG UREDEC VOOR/AAN ALLE MEDEWERKERS IN DE DATABASE
	 * @param u		leeg urendeclaratieobject
	 * @return		mededeling dat het gelukt is
	 */
	public String maakEnKoppelAanAllen(String maandNaam, int maandNr) 
	{
		for (Medewerker persoon: medewerkerRepository.findAll()) 
		{
			Urendeclaratie u = maakUrendeclaratieForm(maandNaam, maandNr);
			u.setMedewerker(persoon);
			u.setStatus(Status.BESCHIKBAAR);
			//persoon.addUrendeclaratie(u);
			urenDeclaratieRepository.save(u);
			medewerkerRepository.save(persoon);
		}
		return "Alle medewerkers kunnen nu de declaratie van "+
				maandNaam + " gaan invullen";
	}

	/** 3 * MAAK & KOPPEL EEN LEEG URENDECLARATIEOBJECT VOOR/AAN ALLE MEDEWERKERS IN DE DATABASE
	 * @param u		leeg urendeclaratieobject
	 * @return		mededeling dat het gelukt is
	 */
	//	public String maakEnKoppelAanAllen(String maandNaam, int maandNr) {
	//		for (Medewerker persoon: medewerkerRepository.findAll()) {
	//			Urendeclaratie u = maakUrendeclaratieForm(maandNaam, maandNr);
	//			persoon.addUrendeclaratie(u);
	//			medewerkerRepository.save(persoon);
	//			//u.setMedewerker(persoon);  ==>>> TODO relatie is nog niet bidrectioneel
	//			//urenDeclaratieRepository.save(u);
	//		}
	//		return "Alle medewerkers kunnen nu de declaratie van "+ maandNaam + " gaan invullen";
	//	}

	/** 3.
	 * KOPPEL FORM AAN MEDEWERKER & SAVE
	 * @param formId
	 * @param medewerkerId		
	 * @return urendeclaratieformulier met eigenaar
	 */
	//	public Urendeclaratie koppelFormAanMedewerker(Long formId, Long medewerkerId) {
	//		Urendeclaratie tempUd = getUrendeclaratie(formId);
	//		Medewerker tempMw = medewerkerService.getMedewerkerById(medewerkerId);
	//		System.out.println("DEBUG" + tempUd);
	//		System.out.println("DEBUG" + tempMw);
	//		//add FORM to MW
	//		tempMw.addUrendeclaratie(tempUd);
	//		//tempUd.setMedewerker(tempMw);
	//		
	//		//medewerkerRepository.save(tempMw);
	//		return urenDeclaratieRepository.save(tempUd);
	//	}
}