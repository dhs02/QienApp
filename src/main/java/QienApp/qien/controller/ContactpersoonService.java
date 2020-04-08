package QienApp.qien.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import QienApp.qien.domein.Contactpersoon;
import QienApp.qien.domein.Opdrachtgever;

@Service
@Transactional
public class ContactpersoonService {
	@Autowired
	ContactpersoonRepository contactpersoonRepository;
	
	@Autowired
	OpdrachtgeverRepository opdrachtgeverRepository;
	
	public void addOpdrachtgever(Long contactpersoonId, Long opdrachtgeverId) {
		Contactpersoon contactpersoon = contactpersoonRepository.findById(contactpersoonId).get();
		Opdrachtgever opdrachtgever = opdrachtgeverRepository.findById(opdrachtgeverId).get();
		contactpersoon.setOpdrachtgever(opdrachtgever);
		Contactpersoon savedContactpersoon = contactpersoonRepository.save(contactpersoon);

		opdrachtgever.getContactpersonen().add(savedContactpersoon);
		opdrachtgeverRepository.save(opdrachtgever);

		System.out.println("Opdrachtgever toegevoegd aan contactpersoon.");
	}
	
	public Iterable<Contactpersoon> getAllContactpersonen() {
		return contactpersoonRepository.findAll();
	}

	public Iterable<Contactpersoon> findAllByOpdrachtgever(Opdrachtgever opdrachtgever) {
		return this.contactpersoonRepository.findAllByOpdrachtgever(opdrachtgever);
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

		Contactpersoon c = contactpersoonRepository.findById(userId).get();
		opdrachtgeverRepository.findById(c.getOpdrachtgever().getId()).get().getContactpersonen().remove(c);

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
	
	public Contactpersoon toevoegenContactpersoonMetOpdrachtgever(String opdrachtgeverId, Contactpersoon contactpersoon) {
		Opdrachtgever opdrachtgever = opdrachtgeverRepository.findById(Long.parseLong(opdrachtgeverId)).get();
		contactpersoon.setOpdrachtgever(opdrachtgever);
		System.out.println("Contactpersoon aangemaakt en opdrachtgever aan toegevoegd.");
		Contactpersoon savedContactpersoon = contactpersoonRepository.save(contactpersoon);

		opdrachtgever.getContactpersonen().add(savedContactpersoon);
		opdrachtgeverRepository.save(opdrachtgever);

		return savedContactpersoon;
	}
}