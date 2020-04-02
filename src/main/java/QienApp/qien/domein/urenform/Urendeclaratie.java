package QienApp.qien.domein.urenform;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import QienApp.qien.domein.Medewerker;
/*CLASS DIAGRAM SAYS:
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
*/

@Entity
@Table(name = "urendeclaratie")
public class Urendeclaratie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String notitie;

//	// TODO implementeren LocalDateTime zaken	s
//	private LocalDateTime verzendTijd;
//	Month maand;
//	Locale locale = Locale.getDefault();
	
	public void setMedewerker(Medewerker medewerker) {
		this.medewerker = medewerker;
	}

	public int getEigenaarId() {
		return eigenaarId;
	}

	public void setEigenaarId(int eigenaarId) {
		this.eigenaarId = eigenaarId;
	}

	//VOOR NU GEBRUIKEN WE DEZE VEREENVOUDIGDE MANIER:
	private int jaar;
	private String maandNaam;
	
	/** Laszlo & Michiels nieuwe ORM methode
	 *  2/4/20
	 */
	private Medewerker medewerker;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EIGENAAR_ID")
	private int eigenaarId;
	
	
	
	/**
	 * Status is een ENUM class met de mogelijkheden:
	 * BESCHIKBAAR, TER_GOEDKEURING, GOEDGEKEURD, AFGEKEURD, AFGEROND; 
	 */
	private Status status;

	/**
	 * heeft een lijst met GewerkteDagen, zoveel als de maand lang is
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="dag_id")
	private List<GewerkteDag> gewerkteDagen = new ArrayList<>();
	
	//methode wordt gebruikt door maakForm() in UrendeclaratieService
	public void addDagToList(GewerkteDag dag) {
		this.gewerkteDagen.add(dag);
		}
	
	//VEEL GETTER & SETTERS
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNotitie() {
		return notitie;
	}

	public void setNotitie(String notitie) {
		this.notitie = notitie;
	}

	public int getJaar() {
		return jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}
}
