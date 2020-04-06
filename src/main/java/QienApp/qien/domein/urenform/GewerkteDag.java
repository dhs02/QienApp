package QienApp.qien.domein.urenform;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gewerkte_dag")
public class GewerkteDag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//UUR SOORTEN
	private float aantalUrenOpdracht;
	private float aantalUrenOverwerk;
	private float aantalUrenVerlof;
	private float aantalUrenZiek;
	private float aantalUrenTraining;
	private float aantalUrenOverig;
	//TEXT VOOR VERKLARING
	private String verklaringOverig;
	
	//DATUM DINGEN TIJDELIJK
	private Date datum;
	private int dagnr;
	
	
	/* CLASS DIAGRAM SAYS:
	- datum: LocalDate
	- opdracht: float
	- overwerk: float
	- verlof: float
	- ziek: float
	- training: float
	- overig: float
	- verklaringOverig: String
	*/
	
	//VEEL GETTERS & SETTERS
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public float getAantalUrenOpdracht() {
		return aantalUrenOpdracht;
	}
	public void setAantalUrenOpdracht(float aantalUrenOpdracht) {
		this.aantalUrenOpdracht = aantalUrenOpdracht;
	}
	public float getAantalUrenOverwerk() {
		return aantalUrenOverwerk;
	}
	public void setAantalUrenOverwerk(float aantalUrenOverwerk) {
		this.aantalUrenOverwerk = aantalUrenOverwerk;
	}
	public float getAantalUrenVerlof() {
		return aantalUrenVerlof;
	}
	public void setAantalUrenVerlof(float aantalUrenVerlof) {
		this.aantalUrenVerlof = aantalUrenVerlof;
	}
	public float getAantalUrenZiek() {
		return aantalUrenZiek;
	}
	public void setAantalUrenZiek(float aantalUrenZiek) {
		this.aantalUrenZiek = aantalUrenZiek;
	}
	public float getAantalUrenTraining() {
		return aantalUrenTraining;
	}
	public void setAantalUrenTraining(float aantalUrenTraining) {
		this.aantalUrenTraining = aantalUrenTraining;
	}
	public float getAantalUrenOverig() {
		return aantalUrenOverig;
	}
	public void setAantalUrenOverig(float aantalUrenOverig) {
		this.aantalUrenOverig = aantalUrenOverig;
	}
	public String getVerklaringOverig() {
		return verklaringOverig;
	}
	public void setVerklaringOverig(String verklaringOverig) {
		this.verklaringOverig = verklaringOverig;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public int getDagnr() {
		return dagnr;
	}
	public void setDagnr(int dagnr) {
		this.dagnr = dagnr;
	}

}