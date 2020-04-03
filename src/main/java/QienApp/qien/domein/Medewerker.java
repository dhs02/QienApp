package QienApp.qien.domein;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

import QienApp.qien.domein.urenform.Urendeclaratie;
import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(value="Medewerker", description="Bevat alle waarden van de Medewerker-entiteit.")
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
	 * HOWTOPROGRAMWITHJAVA.COM=================
	 * bidirectional test
	 * 
	 * 
	 */
	
	//@JsonBackReference
	
	
	@OneToMany
	@JoinColumn(name="medewerker_id")
	private Set<Urendeclaratie> urendeclaraties = new HashSet<Urendeclaratie>();
	
	@JsonIgnore
	public Set<Urendeclaratie> getUrendeclaraties() {
		return urendeclaraties;
	} 
	
	/**
	 * TOT HIER =================================
	 */

	public void setUrendeclaraties(Set<Urendeclaratie> urendeclaraties) {
		this.urendeclaraties = urendeclaraties;
	}
	public void addUrendeclaratie(Urendeclaratie tempUd) {
		tempUd.setMedewerker(this);
		urendeclaraties.add(tempUd);
	}

	//	public List<Urendeclaratie> getUrendeclaraties() {
	//		return urendeclaraties;
	//	}
	//
	//	public void setUrendeclaraties(List<Urendeclaratie> urendeclaraties) {
	//		this.urendeclaraties = urendeclaraties;
	//	}
}