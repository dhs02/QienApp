package QienApp.qien.domein;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import QienApp.qien.domein.urenform.Urendeclaratie;


@Entity
public class Medewerker extends Gebruiker {
	@ManyToOne
	Opdrachtgever opdrachtgever;
	
//ik heb een lijst met urendeclaraties
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="urendecs_id")
	private List<Urendeclaratie> urendeclaraties = new ArrayList<Urendeclaratie>();
	
	/*
	 * 
	 * CONSUMES
	 * Urendeclaratie Object van methode maakUrendeclaratieForm uit UrendeclaratieService
	 * 
	 * met deze methode wordt mijn lijst gevuld
	 * 
	 */
	public void addUrendeclaratie (Urendeclaratie u) {
		this.urendeclaraties.add(u);
		System.out.println("DEBUG urendeclaratie toegevoegd aan de lijst van " + getVoornaam());
    }
	
	
	public Opdrachtgever getOpdrachtgever() {
		return opdrachtgever;
	}
	public void setOpdrachtgever(Opdrachtgever opdrachtgever) {
		this.opdrachtgever = opdrachtgever;
	}


	public List<Urendeclaratie> getUrendeclaraties() {
		return urendeclaraties;
	}


	public void setUrendeclaraties(List<Urendeclaratie> urendeclaraties) {
		this.urendeclaraties = urendeclaraties;
	}
}