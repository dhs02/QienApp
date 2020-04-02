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
	@OneToMany(mappedBy="medewerker")
	private List<Urendeclaratie> urendeclaraties = new ArrayList<Urendeclaratie>();
	

	/** OUDE METHODE
	 * 
	 */
//	public Urendeclaratie addUrendeclaratie (Urendeclaratie u) {
//		this.urendeclaraties.add(u);
//		System.out.println("DEBUG urendeclaratie toegevoegd aan de lijst van " + getVoornaam());
//		return u;
//	}
	
	/**
	 * NIEUWE METHODE
	 * @return
	 */
	 public void addUrendeclaratie(Urendeclaratie u) {
		 	this.urendeclaraties.add(u);
	        if (u.getMedewerker() != this) {
	            u.setMedewerker(this);
	        }
		 	
	 }
	        
	public List<Urendeclaratie> getUrendeclaraties() {
		return urendeclaraties;
	}

	public void setUrendeclaraties(List<Urendeclaratie> urendeclaraties) {
		this.urendeclaraties = urendeclaraties;
	}
}