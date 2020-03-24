package QienApp.qien.domein;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Contactpersoon extends Gebruiker{
	@ManyToOne
	Opdrachtgever opdrachtgever;
	
	public Opdrachtgever getOpdrachtgever() {
		return opdrachtgever;
	}
	public void setOpdrachtgever(Opdrachtgever opdrachtgever) {
		this.opdrachtgever = opdrachtgever;
	}
}