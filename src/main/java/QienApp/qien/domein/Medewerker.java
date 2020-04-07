package QienApp.qien.domein;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
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
	
	@ElementCollection
	private List<String> medewerkerInfo  = new ArrayList<>();
	
	public void addToMedewerkerInfo(String s) 
	{
		this.medewerkerInfo.add(s); 
	}
	
	@OneToMany
	@JoinColumn(name="medewerker_id")
	private Set<Urendeclaratie> urendeclaraties = new HashSet<Urendeclaratie>();
	
	@JsonIgnore
	public Set<Urendeclaratie> getUrendeclaraties() 
	{
		return urendeclaraties;
	} 
	public void addUrendeclaratie(Urendeclaratie tempUd) 
	{
		urendeclaraties.add(tempUd);
	}

	public Opdrachtgever getOpdrachtgever() {return opdrachtgever;}
	public void setOpdrachtgever(Opdrachtgever opdrachtgever) {this.opdrachtgever = opdrachtgever;}
	public Contactpersoon getContactpersoon() {return contactpersoon;}
	public void setContactpersoon(Contactpersoon contactpersoon) {this.contactpersoon = contactpersoon;}
	public void setUrendeclaraties(Set<Urendeclaratie> urendeclaraties) {this.urendeclaraties = urendeclaraties;}
	public List<String> getMedewerkerInfo() {return medewerkerInfo;}
	public void setMedewerkerInfo(List<String> medewerkerInfo) {this.medewerkerInfo = medewerkerInfo;}

}

