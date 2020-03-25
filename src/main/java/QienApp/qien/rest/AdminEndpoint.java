package QienApp.qien.rest;
import org.springframework.beans.factory.annotation.Autowired;
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
}