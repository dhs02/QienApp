package QienApp.qien.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import QienApp.qien.domein.Contactpersoon;

@Service
@Transactional
public class ContactpersoonService {
	@Autowired
	ContactpersoonRepository contactpersoonRepository;
	
	public Iterable<Contactpersoon> getAllContactpersonen() {
		return contactpersoonRepository.findAll();
	}
	public Contactpersoon getContactpersoonById(Long userId) {
		System.out.println("Contactpersoon gevonden in Database");
		return contactpersoonRepository.findById(userId).get();
	}
	public Contactpersoon addContactpersoon(Contactpersoon contactpersoon) {
		System.out.println("Contactpersoon toegevoegd aan Database");
		return contactpersoonRepository.save(contactpersoon);
	}
	public void deleteContactpersoon(Long userId) {
		System.out.println("Contactpersoon verwijderd uit Database");
		contactpersoonRepository.deleteById(userId);
	}
	public Contactpersoon updateContactpersoon(Long userId, Contactpersoon contactpersoonDetails) {
		Contactpersoon contactpersoon = contactpersoonRepository.findById(userId).get();
		if (contactpersoonDetails.getVoornaam() != null && contactpersoonDetails.getVoornaam() != "") {
			contactpersoon.setVoornaam(contactpersoonDetails.getVoornaam());
		}
		if (contactpersoonDetails.getAchternaam() != null && contactpersoonDetails.getAchternaam() != "") {
			contactpersoon.setAchternaam(contactpersoonDetails.getAchternaam());
		}
		if (contactpersoonDetails.getGeboorteDatum() != null && contactpersoonDetails.getGeboorteDatum() != "") {
			contactpersoon.setGeboorteDatum(contactpersoonDetails.getGeboorteDatum());
		}
		if (contactpersoonDetails.getAdres() != null && contactpersoonDetails.getAdres() != "") {
			contactpersoon.setAdres(contactpersoonDetails.getAdres());
		}
		if (contactpersoonDetails.getEmail() != null && contactpersoonDetails.getEmail() != "") {
			contactpersoon.setEmail(contactpersoonDetails.getEmail());
		}
		if (contactpersoonDetails.getTelefoonNummer() != null && contactpersoonDetails.getTelefoonNummer() != "") {
			contactpersoon.setTelefoonNummer(contactpersoonDetails.getTelefoonNummer());
		}
		if (contactpersoonDetails.getWachtwoordHash() != null && contactpersoonDetails.getWachtwoordHash() != "") {
			contactpersoon.setWachtwoordHash(contactpersoonDetails.getWachtwoordHash());
		}
	    System.out.println("Contactpersoon veranderd in Database");
	    return contactpersoonRepository.save(contactpersoon);
	}
}