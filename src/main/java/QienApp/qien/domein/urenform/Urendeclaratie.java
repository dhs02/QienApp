package QienApp.qien.domein.urenform;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	// TODO: deze 5
	private Medewerker medewerker;
	private boolean goedKeuring;
	private boolean goedKeuringOpdrachtgever;
	private String notitie;
	private LocalDateTime verzendTijd;
	
	// TODO implementeren LocalDateTime zaken
	Month maand;
	Locale locale = Locale.getDefault();
	
	//VOOR NU GEBRUIKEN WE DEZE VEREENVOUDIGDE MANIER:
	private String maandNaam;
	
	
	//heeft een lijst met GewerkteDagen, zoveel als de maand lang is
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="dag_id")
	private List<GewerkteDag> gewerkteDagen = new ArrayList<>();
	
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

	public Medewerker getMedewerker() {
		return medewerker;
	}

	public void setMedewerker(Medewerker medewerker) {
		this.medewerker = medewerker;
	}

	public boolean isGoedKeuring() {
		return goedKeuring;
	}

	public void setGoedKeuring(boolean goedKeuring) {
		this.goedKeuring = goedKeuring;
	}

	public boolean isGoedKeuringOpdrachtgever() {
		return goedKeuringOpdrachtgever;
	}

	public void setGoedKeuringOpdrachtgever(boolean goedKeuringOpdrachtgever) {
		this.goedKeuringOpdrachtgever = goedKeuringOpdrachtgever;
	}

	public String getNotitie() {
		return notitie;
	}

	public void setNotitie(String notitie) {
		this.notitie = notitie;
	}

	public LocalDateTime getVerzendTijd() {
		return verzendTijd;
	}

	public void setVerzendTijd(LocalDateTime verzendTijd) {
		this.verzendTijd = verzendTijd;
	}

	public Month getMaand() {
		return maand;
	}

	public void setMaand(Month maand) {
		this.maand = maand;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getMaandNaam() {
		return maandNaam;
	}

	public void setMaandNaam(String maandNaam) {
		this.maandNaam = maandNaam;
	}

	public List<GewerkteDag> getGewerkteDagen() {
		return gewerkteDagen;
	}

	public void setGewerkteDagen(List<GewerkteDag> gewerkteDagen) {
		this.gewerkteDagen = gewerkteDagen;
	}
	


	
	
	

	
}
