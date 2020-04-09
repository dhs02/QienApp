package QienApp.qien.domein;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@ApiModel(value="Gebruiker", description="Bevat alle waarden van de Gebruiker-entiteit.")
@Inheritance
@Table(name="gebruiker_table")
public class Gebruiker {
	private static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(hidden = true)
	private long id;
	
	@ApiModelProperty(position = 1, value = "De aanhef van een Gebruiker.")
	private String aanhef;
	@ApiModelProperty(position = 2, value = "De voornaam van een Gebruiker.")
	private String voornaam;
	@ApiModelProperty(position = 3, value = "De achternaam van een Gebruiker.")
	private String achternaam;
	@ApiModelProperty(position = 4, value = "De geboortedatum van een Gebruiker.")
	private String geboorteDatum;
	@ApiModelProperty(position = 5, value = "Het adres van een Gebruiker.")
	private String adres;
	@ApiModelProperty(position = 6, value = "De postcode van een Gebruiker.")
	private String postcode;
	@ApiModelProperty(position = 7, value = "De plaatsnaam van een Gebruiker.")
	private String plaats;
	@ApiModelProperty(position = 8, value = "Het emailadres van een Gebruiker.")
	private String email;
	@ApiModelProperty(position = 9, value = "Het telefoonnummer van een Gebruiker.")
	private String telefoonNummer;
	
	@ApiModelProperty(position = 10, value = "De datum van indiensttreding van een Gebruiker.")
	private String datumInDienst;
	@ApiModelProperty(position = 11, value = "De datum van uitdiensttreding van een Gebruiker.")
	private String datumUitDienst;
	
	@ApiModelProperty(position = 12, value = "De wachtwoordhash van een Gebruiker.")
	private String wachtwoordHash;
	@ApiModelProperty(position = 13, value = "De profielfoto van een Gebruiker.")
	private String afbeelding;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAanhef() {
		return aanhef;
	}
	public void setAanhef(String aanhef) {
		this.aanhef = aanhef;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getAchternaam() {
		return achternaam;
	}
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	public String getGeboorteDatum() {
		return geboorteDatum;
	}
	public void setGeboorteDatum(String geboorteDatum) {
		this.geboorteDatum = geboorteDatum;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPlaats() {
		return plaats;
	}
	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefoonNummer() {
		return telefoonNummer;
	}
	public void setTelefoonNummer(String telefoonNummer) {
		this.telefoonNummer = telefoonNummer;
	}
	
	public String getDatumInDienst() {
		return datumInDienst;
	}
	public void setDatumInDienst(String datumInDienst) {
		this.datumInDienst = datumInDienst;
	}
	public String getDatumUitDienst() {
		return datumUitDienst;
	}
	public void setDatumUitDienst(String datumUitDienst) {
		this.datumUitDienst = datumUitDienst;
	}
	
	@JsonIgnore
	public String getWachtwoordHash() {
		return wachtwoordHash;
	}
	@JsonProperty
	public void setWachtwoordHash(String wachtwoordHash) {
		this.wachtwoordHash = passwordEncoder.encode(wachtwoordHash);
	}
	public String getAfbeelding() {
		return afbeelding;
	}
	public void setAfbeelding(String afbeelding) {
		this.afbeelding = afbeelding;
	}

	public String getGebruikerType() {
		String gebruikerType = this.getClass().toString();
		return gebruikerType.substring(gebruikerType.lastIndexOf('.') + 1);
	}
}