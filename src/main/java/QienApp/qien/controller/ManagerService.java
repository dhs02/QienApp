package QienApp.qien.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import QienApp.qien.domein.Manager;

@Service
@Transactional
public class ManagerService {
	@Autowired
	ManagerRepository managerRepository;
	
	public Iterable<Manager> getAllManagers() {
		return managerRepository.findAll();
	}
	public Manager getManagerById(Long userId) {
		System.out.println("Manager gevonden in Database");
		return managerRepository.findById(userId).get();
	}
	public Manager addManager(Manager manager) {
		System.out.println("Manager toegevoegd aan Database");
		return managerRepository.save(manager);
	}
	public void deleteManager(Long userId) {
		System.out.println("Manager verwijderd uit Database");
		managerRepository.deleteById(userId);
	}
	public Manager updateManager(Long userId, Manager managerDetails) {
		Manager manager = managerRepository.findById(userId).get();
		if (managerDetails.getVoornaam() != null && managerDetails.getVoornaam() != "") {
			manager.setVoornaam(managerDetails.getVoornaam());
		}
		if (managerDetails.getAchternaam() != null &&managerDetails.getAchternaam() != "") {
			manager.setAchternaam(managerDetails.getAchternaam());
		}
		if (managerDetails.getGeboorteDatum() != null && managerDetails.getGeboorteDatum() != "") {
			manager.setGeboorteDatum(managerDetails.getGeboorteDatum());
		}
		if (managerDetails.getAdres() != null && managerDetails.getAdres() != "") {
			manager.setAdres(managerDetails.getAdres());
		}
		if (managerDetails.getEmail() != null && managerDetails.getEmail() != "") {
			manager.setEmail(managerDetails.getEmail());
		}
		if (managerDetails.getTelefoonNummer() != null && managerDetails.getTelefoonNummer() != "") {
			manager.setTelefoonNummer(managerDetails.getTelefoonNummer());
		}
		if (managerDetails.getWachtwoordHash() != null && managerDetails.getWachtwoordHash() != "") {
			manager.setWachtwoordHash(managerDetails.getWachtwoordHash());
		}
	    System.out.println("Manager veranderd in Database");
	    return managerRepository.save(manager);
	}
}