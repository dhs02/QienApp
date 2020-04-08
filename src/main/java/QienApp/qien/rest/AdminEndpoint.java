package QienApp.qien.rest;
import QienApp.qien.security.domein.GebruikerPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import QienApp.qien.controller.AdminService;
import QienApp.qien.domein.Admin;

@RestController
@RequestMapping("/api/admins")
public class AdminEndpoint {
	@Autowired
	AdminService adminService;

	@GetMapping("/")
	public Iterable<Admin> verkrijgAdmins() {
		return adminService.getAllAdmins();
	}
	@GetMapping("/{id}")
	public Admin verkrijgAdmin(@PathVariable(value = "id") String adminId) {
		return adminService.getAdminById(Long.parseLong(adminId));
	}
	@PostMapping("/")
	public Admin toevoegenAdmin(@RequestBody Admin admin) {
		return adminService.addAdmin(admin);
	}
	@DeleteMapping("/{id}")
	public void verwijderAdmin(@PathVariable(value = "id") String adminId) {
		adminService.deleteAdmin(Long.parseLong(adminId));
	}
	@PutMapping("/{id}")
	public Admin vernieuwAdmin(@PathVariable(value = "id") String adminId, @RequestBody Admin adminDetails) {
		return adminService.updateAdmin(Long.parseLong(adminId), adminDetails);
	}

	@GetMapping("/me")
	public Admin getIngelogdeAdmin(Authentication authentication) {
		checkAuth(authentication);
		return (Admin) ((GebruikerPrincipal) authentication.getPrincipal()).getGebruiker();
	}

	@PutMapping("/me")
	public Admin updateIngelogdeAdmin(Authentication authentication, @RequestBody Admin adminDetails) {
		checkAuth(authentication);
		Admin me = (Admin) ((GebruikerPrincipal) authentication.getPrincipal()).getGebruiker();
		return this.adminService.updateAdmin(me.getId(), adminDetails);
	}

	@DeleteMapping("/me")
	public void deleteIngelogdeAdmin(Authentication authentication) {
		checkAuth(authentication);
		Admin me = (Admin) ((GebruikerPrincipal) authentication.getPrincipal()).getGebruiker();
		this.adminService.deleteAdmin(me.getId());
	}

	private static void checkAuth(Authentication authentication) {
		if (authentication == null
				|| !(authentication.getPrincipal() instanceof Admin)) {
			throw new AuthenticationCredentialsNotFoundException("No or incorrect credentials supplied.");
		}
	}
}