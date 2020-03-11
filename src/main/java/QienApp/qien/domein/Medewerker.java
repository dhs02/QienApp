package QienApp.qien.domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medewerker {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	private String voornaam;
	private String achternaam;
	private String geboortedatum;
	private String adres;
	private String email;
	private String telefoonnummer;

}
