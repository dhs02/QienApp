package QienApp.qien.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import QienApp.qien.domein.Contactpersoon;
import QienApp.qien.domein.Medewerker;
import QienApp.qien.domein.Opdrachtgever;
import QienApp.qien.domein.urenform.Urendeclaratie;

@Service
@Transactional
public class MedewerkerService {
	@Autowired
	MedewerkerRepository medewerkerRepository;
	@Autowired
	OpdrachtgeverRepository opdrachtgeverRepository;
	@Autowired
	ContactpersoonRepository contactpersoonRepository;
	
	public void putMedewerkerInfo(Urendeclaratie u) 
	{
		Medewerker medewerker = u.getMedewerker();
			medewerker.addToMedewerkerInfo(medewerker.getVoornaam());
			medewerker.addToMedewerkerInfo(medewerker.getAchternaam());
			medewerker.addToMedewerkerInfo(medewerker.getGeboorteDatum());
			medewerker.addToMedewerkerInfo(medewerker.getAdres());
			medewerker.addToMedewerkerInfo(medewerker.getTelefoonNummer());
			medewerker.addToMedewerkerInfo(medewerker.getEmail());
			medewerkerRepository.save(medewerker);
	}
	
	public Iterable<Medewerker> getAllMedewerkers() {
		return medewerkerRepository.findAll();
	}
	public Medewerker getMedewerkerById(Long userId) {
		System.out.println("Medewerker gevonden in Database");
		return medewerkerRepository.findById(userId).get();
	}
	
	public List<Medewerker> findByVoornaam(String voornaam) {
		return medewerkerRepository.findByVoornaam(voornaam);
	}
	public List<Medewerker> findByAchternaam(String achternaam) {
		return medewerkerRepository.findByAchternaam(achternaam);
	}
	public List<Medewerker> findByVoornaamAndAchternaam(String voornaam, String achternaam) {
		return medewerkerRepository.findByVoornaamAndAchternaam(voornaam, achternaam);
	}
	
	public void addOpdrachtgever(Long medewerkerId, Long opdrachtgeverId) {
		Medewerker medewerker = medewerkerRepository.findById(medewerkerId).get();
		Opdrachtgever opdrachtgever = opdrachtgeverRepository.findById(opdrachtgeverId).get();
		medewerker.setOpdrachtgever(opdrachtgever);
		medewerkerRepository.save(medewerker);
		System.out.println("Opdrachtgever toegevoegd aan medewerker.");
	}
	public void addContactpersoon(Long medewerkerId, Long contactpersoonId) {
		Medewerker medewerker = medewerkerRepository.findById(medewerkerId).get();
		Contactpersoon contactpersoon = contactpersoonRepository.findById(contactpersoonId).get();
		medewerker.setContactpersoon(contactpersoon);
		medewerkerRepository.save(medewerker);
		System.out.println("Contactpersoon toegevoegd aan medewerker.");
	}
	public Medewerker addMedewerker(Medewerker medewerker) {
		System.out.println("Medewerker toegevoegd aan Database");
		return medewerkerRepository.save(medewerker);
	}
	public void deleteMedewerker(Long userId) {
		System.out.println("Medewerker verwijderd uit Database");
		medewerkerRepository.deleteById(userId);
	}
	public Medewerker updateMedewerker(Long userId, Medewerker medewerkerDetails) {
		Medewerker medewerker = medewerkerRepository.findById(userId).get();
		if (medewerkerDetails.getVoornaam() != null && medewerkerDetails.getVoornaam() != "") {
			medewerker.setVoornaam(medewerkerDetails.getVoornaam());
		}
		if (medewerkerDetails.getAchternaam() != null && medewerkerDetails.getAchternaam() != "") {
			medewerker.setAchternaam(medewerkerDetails.getAchternaam());
		}
		if (medewerkerDetails.getGeboorteDatum() != null && medewerkerDetails.getGeboorteDatum() != "") {
			medewerker.setGeboorteDatum(medewerkerDetails.getGeboorteDatum());
		}
		if (medewerkerDetails.getAdres() != null && medewerkerDetails.getAdres() != "") {
			medewerker.setAdres(medewerkerDetails.getAdres());
		}
		if (medewerkerDetails.getEmail() != null && medewerkerDetails.getEmail() != "") {
			medewerker.setEmail(medewerkerDetails.getEmail());
		}
		if (medewerkerDetails.getTelefoonNummer() != null && medewerkerDetails.getTelefoonNummer() != "") {
			medewerker.setTelefoonNummer(medewerkerDetails.getTelefoonNummer());
		}
		if (medewerkerDetails.getWachtwoordHash() != null && medewerkerDetails.getWachtwoordHash() != "") {
			medewerker.setWachtwoordHash(medewerkerDetails.getWachtwoordHash());
		}
	    System.out.println("Medewerker veranderd in Database");
	    return medewerkerRepository.save(medewerker);
	}
}