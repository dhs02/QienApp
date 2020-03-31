package QienApp.qien.domein.urenform;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import QienApp.qien.controller.urenform.Status;

import QienApp.qien.domein.Medewerker;

/*CLASS DIAGRAM SAYS:
*
*
- medewerker: Medewerker
- opdracht: Opdracht(TODO)?? in overleg met Thomas overgeslagen 24/04
- maand: LocalDate
- gewerkteDagen: ArrayList<GewerkteDag>
- goedKeuring: boolean
- opdrachtgever: Opdrachtgever
- goedKeuringOpdrachtgever: boolean
- notitie: String
- verzendTijd: LocalDateTime
*
*
*/


@Entity
@Table(name = "urendeclaratie")
public class Urendeclaratie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	//ADDED LASZLO
	@ManyToOne
	private Medewerker medewerker;
	private LocalDate maand;
	private String notitie;
	private Status status;
	
	@OneToMany
	//@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name="dag_id")
	private Collection<GewerkteDag> gewerkteDagen;
	
	//NOG TE DOEN
//	private boolean goedKeuring;	-> uitzoeken booleans in database
//	private boolean goedKeuringOpdrachtgever;
//	private LocalDateTime verzendTijd;	
	//Ongecomment LASZLO
	

	//?WEET NIET WAT DIT IS?
//	Locale locale = Locale.getDefault();

//	LASZLO gecomment mogelijk weg
//	private String maandNaam;
	
	
	
	
	//methode wordt gebruikt door maakForm() in UrendeclaratieService
	public void addDag(GewerkteDag dag) {
		this.gewerkteDagen.add(dag);
		}
	
	//VEEL GETTER & SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	//LASZLO UITGECOMMENT
//	public String getMaandNaam() {
//		return maandNaam;
//	}
//
//	public void setMaandNaam(String maandNaam) {
//		this.maandNaam = maandNaam;
//	}

	public Collection<GewerkteDag> getGewerkteDagen() {
		return gewerkteDagen;
	}

	public void setGewerkteDagen(List<GewerkteDag> gewerkteDagen) {
		this.gewerkteDagen = gewerkteDagen;
	}
	
	//LASZLO ADDED
	public String getNotitie() {
		return this.notitie;
	}
	
	//LASZLO ADDED
	public void setNotitie(String notitie) {
		this.notitie = notitie;
	}
	
	//LASZLO ADDED
	public LocalDate getMaand() {
		return maand;
	}
	
	//LASZLO ADDED
	public void setMaand(LocalDate maand) {
		this.maand = maand;
	}

	public Medewerker getMedewerker() {
		return medewerker;
	}

	public void setMedewerker(Medewerker medewerker) {
		this.medewerker = medewerker;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	

	
	

	
}
