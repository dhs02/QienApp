package QienApp.qien.controller.urenform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import QienApp.qien.controller.MedewerkerRepository;
import QienApp.qien.controller.MedewerkerService;
import QienApp.qien.domein.Medewerker;
import QienApp.qien.domein.urenform.GewerkteDag;
import QienApp.qien.domein.urenform.Urendeclaratie;

@Service
public class UrenDeclaratieService {

	@Autowired
	private UrenDeclaratieRepository urenDeclaratieRepository;
	@Autowired
	private MedewerkerRepository medewerkerRepository;
	@Autowired
	private MedewerkerService medewerkerService;
	@Autowired
	private GewerkteDagService gewerkteDagService;

	/**
	 * GET ONE URENDECLARATIE
	 * @PARAM id	ID van een specifieke urendeclaratie
	 * @RETURN		de gevraagde urendeclaratie
	 */
	public Urendeclaratie getUrendeclaraties(Long id) {
		return urenDeclaratieRepository.findById(id).get();
	}

	//VIND ALLE URENDECLARATIEFORMULIEREN
	public Iterable<Urendeclaratie> getAllUrendeclaraties() {
		return urenDeclaratieRepository.findAll();
	}

	/**
	 * UPDATEURENDECLARATIE ===>> IK WEET NIET ZEKER OF DEZE WERKT...
	 * 
	 * wijzigt declaratie als je de juiste udID en objectnaanm ingeeft
	 * @param udId						ID van een urendeclaratie die aangepast moet worden
	 * @param urendDeclaratieDetails	urendeclaratie object
	 * @return ud						de aangepaste urendeclaratie		
	 */
//	public Urendeclaratie updateUrendeclaratie(long udId, Urendeclaratie nieuw) 
//	{
//		Urendeclaratie oud = urenDeclaratieRepository.findById(udId).get();
//		
//		for (GewerkteDag oudedag : oud.getGewerkteDagen() ) {
//			GewerkteDag nieuwedag : nieuw.getGewerkteDagen();
//
//			dag.setAantalUrenOpdracht(dag.getAantalUrenOpdracht());
//			dag.setAantalUrenOverig(dag.getAantalUrenOverig());
//			dag.setAantalUrenOverwerk(dag.getAantalUrenOverwerk());
//			dag.setAantalUrenTraining(dag.getAantalUrenTraining());
//			dag.setAantalUrenVerlof(dag.getAantalUrenVerlof());
//			dag.setAantalUrenZiek(dag.getAantalUrenZiek());
//			dag.setVerklaringOverig(dag.getVerklaringOverig());
//		
//		}
//		return urenDeclaratieRepository.save(ud);
//	}

	/**
	 * KOPPEL FORM AAN MEDEWERKER & SAVE
	 * @param formId
	 * @param medewerkerId		
	 * @return urendeclaratieformulier met eigenaar
	 * -------
	 * michiel */
	public Urendeclaratie koppelFormAanMedewerker(Long formId, Long medewerkerId) 
	{
		Urendeclaratie tempUd = getUrendeclaraties(formId);
		Medewerker tempMw = medewerkerService.getMedewerkerById(medewerkerId);

		//add FORM to MW
		tempMw.addUrendeclaratie(tempUd);
		medewerkerRepository.save(tempMw);

		//TODO add MW to FORM ==>>> relatie is nog niet bidirectioneel
		//tempUd.setMedewerker(tempMw);
		return urenDeclaratieRepository.save(tempUd);
	}


	/**
	 * KOPPEL EEN LEEG URENDECLARATIEOBJECT AAN ALLE MEDEWERKERS IN DE DATABASE
	 * @param u		leeg urendeclaratieobject
	 * @return		mededeling dat het gelukt is
	 */
	public String koppelAanAllen(Urendeclaratie u) 
	{
		for (Medewerker persoon: medewerkerRepository.findAll()) {
			persoon.addUrendeclaratie(u);
			medewerkerRepository.save(persoon);
			//u.setMedewerker(persoon);  ==>>> TODO relatie is nog niet bidrectioneel
			urenDeclaratieRepository.save(u);
		}
		return "Alle medewerkers kunnen nu de declaratie voor " + u.getMaandNaam() + "gaan invullen";
	}


	/**
	 * MAAK EEN LEEG URENDECLARATIEFORMULIER
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
				dezemaand.addDag(dag);
				gewerkteDagService.addDag(dag);
			} break;
		case 4: case 6: case 9: case 11:
			for (int x = 0; x < 30; x++) {
				GewerkteDag dag = new GewerkteDag();
				dag.setDagnr(x+1);
				dezemaand.addDag(dag);
				gewerkteDagService.addDag(dag);
			} break;
		default:
			for (int x = 0; x < 31; x++) {
				GewerkteDag dag = new GewerkteDag();
				dag.setDagnr(x+1);
				dezemaand.addDag(dag);
				gewerkteDagService.addDag(dag);
			} break;
		}
		urenDeclaratieRepository.save(dezemaand);
		return dezemaand;
	}
}
