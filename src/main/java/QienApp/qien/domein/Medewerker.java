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
  
	@ManyToOne
	Contactpersoon contactpersoon;
	
	public Opdrachtgever getOpdrachtgever() {
		return opdrachtgever;
	}
	public void setOpdrachtgever(Opdrachtgever opdrachtgever) {
		this.opdrachtgever = opdrachtgever;
	}
	public Contactpersoon getContactpersoon() {
		return contactpersoon;
	}
	public void setContactpersoon(Contactpersoon contactpersoon) {
		this.contactpersoon = contactpersoon;
	}

	/**
	 * Maurice oude manier van ORM
	 */
	//@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name="urendecs_id")
	
	/** Laszlo & Michiels nieuwe ORM methode
	 *  2/4/20
	 */
	@OneToMany//(mappedBy="eigenaarId")
	private List<Urendeclaratie> urendeclaraties = new ArrayList<Urendeclaratie>();
	

	/*
	 * 
	 * CONSUMES
	 * Urendeclaratie Object van methode maakUrendeclaratieForm uit UrendeclaratieService
	 * 
	 * met deze methode wordt mijn lijst gevuld
	 * 
	 */
	public Urendeclaratie addUrendeclaratie (Urendeclaratie u) {
		this.urendeclaraties.add(u);
		System.out.println("DEBUG urendeclaratie toegevoegd aan de lijst van " + getVoornaam());
		return u;
	}
	
	public List<Urendeclaratie> getUrendeclaraties() {
		return urendeclaraties;
	}

	public void setUrendeclaraties(List<Urendeclaratie> urendeclaraties) {
		this.urendeclaraties = urendeclaraties;
	}
}