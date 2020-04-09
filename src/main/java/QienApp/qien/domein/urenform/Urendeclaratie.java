package QienApp.qien.domein.urenform;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
	private int jaar = Calendar.getInstance().get(Calendar.YEAR);
	private String maandNaam;
	private Status status = Status.BESCHIKBAAR;

	/**
	 * ORM fields
	 */
	@ManyToOne
	private Medewerker medewerker;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<GewerkteDag> gewerkteDagen = new ArrayList<>();

	/**
	 * wordt gebruik door 2. maakUrendeclaratieForm in UrenDeclaratieService
	 * @param dag	nieuw aangemaakte lege GewerkteDag
	 */
	public void addDagToList(GewerkteDag dag) 
	{
		this.gewerkteDagen.add(dag);
	}
	
	/**
	 * VEEL GETTER & SETTERS
	 */
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	public String getMaandNaam() { return maandNaam; }
	public void setMaandNaam(String maandNaam) { this.maandNaam = maandNaam; }
	public List<GewerkteDag> getGewerkteDagen() { return gewerkteDagen; }
	public void setGewerkteDagen(List<GewerkteDag> gewerkteDagen) { this.gewerkteDagen = gewerkteDagen; }
	public Status getStatus() { return mijnStatus; }
	public void setStatus(Status status) { this.mijnStatus = status; }
	public Status getMijnStatus() { return mijnStatus; }
	public void setMijnStatus(Status mijnStatus) { this.mijnStatus = mijnStatus; }
	public Medewerker getMedewerker() { return medewerker; }
	public void setMedewerker(Medewerker medewerker) { this.medewerker = medewerker; }
	public String getNotitie() { return notitie; }
	public void setNotitie(String notitie) { this.notitie = notitie; }
	public int getJaar() { return jaar; }
	public void setJaar(int jaar) { this.jaar = jaar; }
}