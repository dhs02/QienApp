package QienApp.qien.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import QienApp.qien.domein.Admin;

@Service
@Transactional
public class AdminService {
	@Autowired
	AdminRepository adminRepository;
	
	public Iterable<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}
	public Admin getAdminById(Long userId) {
		System.out.println("Admin gevonden in Database");
		return adminRepository.findById(userId).get();
	}
	public Admin addAdmin(Admin admin) {
		System.out.println("Admin toegevoegd aan Database");
		return adminRepository.save(admin);
	}
	public void deleteAdmin(Long userId) {
		System.out.println("Admin verwijderd uit Database");
		adminRepository.deleteById(userId);
	}
	public Admin updateAdmin(Long userId, Admin adminDetails) {
		Admin admin = adminRepository.findById(userId).get();
		
		if (adminDetails.getAanhef() != null && adminDetails.getAanhef() != "") {
			admin.setAanhef(adminDetails.getAanhef());
		}
		if (adminDetails.getVoornaam() != null && adminDetails.getVoornaam() != "") {
			admin.setVoornaam(adminDetails.getVoornaam());
		}
		if (adminDetails.getAchternaam() != null &&adminDetails.getAchternaam() != "") {
			admin.setAchternaam(adminDetails.getAchternaam());
		}
		if (adminDetails.getGeboorteDatum() != null && adminDetails.getGeboorteDatum() != "") {
			admin.setGeboorteDatum(adminDetails.getGeboorteDatum());
		}
		if (adminDetails.getAdres() != null && adminDetails.getAdres() != "") {
			admin.setAdres(adminDetails.getAdres());
		}
		if (adminDetails.getPostcode() != null && adminDetails.getPostcode() != "") {
			admin.setPostcode(adminDetails.getPostcode());
		}
		if (adminDetails.getPlaats() != null && adminDetails.getPlaats() != "") {
			admin.setPlaats(adminDetails.getPlaats());
		}
		if (adminDetails.getEmail() != null && adminDetails.getEmail() != "") {
			admin.setEmail(adminDetails.getEmail());
		}
		if (adminDetails.getTelefoonNummer() != null && adminDetails.getTelefoonNummer() != "") {
			admin.setTelefoonNummer(adminDetails.getTelefoonNummer());
		}
		if (adminDetails.getDatumInDienst() != null && adminDetails.getDatumInDienst() != "") {
			admin.setDatumInDienst(adminDetails.getDatumInDienst());
		}
		if (adminDetails.getDatumUitDienst() != null && adminDetails.getDatumUitDienst() != "") {
			admin.setDatumUitDienst(adminDetails.getDatumUitDienst());
		}
		if (adminDetails.getWachtwoordHash() != null && adminDetails.getWachtwoordHash() != "") {
			admin.setWachtwoordHash(adminDetails.getWachtwoordHash());
		}
		if (adminDetails.getAfbeelding() != null && adminDetails.getAfbeelding() != "") {
			admin.setAfbeelding(adminDetails.getAfbeelding());
		}
	    System.out.println("Admin veranderd in Database");
	    return adminRepository.save(admin);
	}
}