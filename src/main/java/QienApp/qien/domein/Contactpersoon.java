package QienApp.qien.domein;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(value="Contactpersoon", description="Bevat alle waarden van de Contactpersoon-entiteit.")
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