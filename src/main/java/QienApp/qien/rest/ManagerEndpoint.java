package QienApp.qien.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import QienApp.qien.controller.ManagerService;
import QienApp.qien.domein.Manager;

@RestController
public class ManagerEndpoint {
	@Autowired
	ManagerService managerService;

	@GetMapping("/gebruikers/managers")
	public Iterable<Manager> verkrijgManagers() {
		return managerService.getAllManagers();
	}
	@GetMapping("/gebruikers/managers/{id}")
	public Manager verkrijgManager(@PathVariable(value = "id") String managerId) {
		return managerService.getManagerById(Long.parseLong(managerId));
	}
	@PostMapping("/gebruikers/managers")
	public Manager toevoegenManager(@RequestBody Manager manager) {
		return managerService.addManager(manager);
	}
	@DeleteMapping("/gebruikers/managers/{id}")
	public void verwijderManager(@PathVariable(value = "id") String managerId) {
		managerService.deleteManager(Long.parseLong(managerId));
	}
	@PatchMapping("/gebruikers/managers/{id}")
	public Manager vernieuwManager(@PathVariable(value = "id") String managerId, @RequestBody Manager managerDetails) {
		return managerService.updateManager(Long.parseLong(managerId), managerDetails);
	}
}