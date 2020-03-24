package QienApp.qien.controller.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import QienApp.qien.controller.MedewerkerRepository;
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
	private GewerkteDagService gewerkteDagService;
	
	//VIND ALLE URENDECLARATIEFORMULIEREN
	public Iterable<Urendeclaratie> getAllUrendeclaraties() {
		return urenDeclaratieRepository.findAll();
	}
	
	/*
	 * CONSUMES 
	 * maandNaam String uit JSON, 
	 * MaandNr (1-12) uit JSON
	 * Medewerker object uit lijst in METHODE van ENDPOINT 
	 * PRODUCES
	 * per medewerker uit die lijst een Urendeclaratie Object,
	 * populated met Dag Objects (hoeveeheid hiervan wordt bepaald dmv
	 * maandNr, zie Switch hieronder)
	 */
	public Urendeclaratie maakUrendeclaratieForm(String maandNaam, int maandNr, Medewerker dezepersoon) {
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
			}
			break;
		case 4: case 6: case 9: case 11:
			for (int x = 0; x < 30; x++) {
				GewerkteDag dag = new GewerkteDag();
				dag.setDagnr(x+1);
				dezemaand.addDag(dag);
				gewerkteDagService.addDag(dag);
			}
			break;
		default:
			for (int x = 0; x < 31; x++) {
				GewerkteDag dag = new GewerkteDag();
				dag.setDagnr(x+1);
				dezemaand.addDag(dag);
				gewerkteDagService.addDag(dag);
			}
			break;
		}
		// voeg de gemaakte maand/form toe aan de lijst van de medewerker die je als argument kreeg
		dezepersoon.addUrendeclaratie(dezemaand);
		medewerkerRepository.save(dezepersoon);
		System.out.println("" + dezepersoon.getVoornaam() + dezepersoon.getAchternaam() + "gesaved met urendeclaratiefornulier voor deze maand");
		return dezemaand;
	}
	

}
