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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
//// TODO implementeren LocalDateTime zaken	s
//private LocalDateTime verzendTijd;
//Month maand;
//Locale locale = Locale.getDefault();

@Entity
@Table(name = "urendeclaratie")
public class Urendeclaratie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String notitie;
	//VOOR NU GEBRUIKEN WE DEZE VEREENVOUDIGDE MANIER:
	private int jaar;
	private String maandNaam;
	//Status is een ENUM class met de mogelijkheden:
	//BESCHIKBAAR, TER_GOEDKEURING, GOEDGEKEURD, AFGEKEURD, AFGEROND; 
	private Status status;
	
	/**
	 * HOWTOPROGRAMWITHJAVA.COM=================
	 * bidirectional test
	 * 
	 * 
	 */
	//@JsonManagedReference
	
	@ManyToOne
	private Medewerker medewerker;

	public Medewerker getMedewerker() 
	{
		return medewerker;
	}
	/**
	 * =================================TOT HIER 
	 */

	public void setMedewerker(Medewerker medewerker) {
        this.medewerker = medewerker;
    }
	
	/**
	 * heeft een lijst met GewerkteDagen, zoveel als de maand lang is
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
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
