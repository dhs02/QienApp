package QienApp.qien.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import QienApp.qien.domein.Opdrachtgever;

import QienApp.qien.controller.OpdrachtgeverService;

@RestController
@RequestMapping("/api/opdrachtgevers")
public class OpdrachtgeverEndpoint {
	@Autowired
	OpdrachtgeverService opdrachtgeverService;
	
	@GetMapping("/")
	public Iterable<Opdrachtgever> verkrijgOpdrachtgevers() {
		return opdrachtgeverService.getAllOpdrachtgevers();
	}
	@GetMapping("/{id}")
	public Opdrachtgever verkrijgOpdrachtgever(@PathVariable(value = "id") String opdrachtgeverId) {
		return opdrachtgeverService.getOpdrachtgeverById(Long.parseLong(opdrachtgeverId));
	}
	@PostMapping("/")
	public Opdrachtgever toevoegenOpdrachtgever(@RequestBody Opdrachtgever opdrachtgever) {
		return opdrachtgeverService.addOpdrachtgever(opdrachtgever);
	}
	@DeleteMapping("/{id}")
	public void verwijderOpdrachtgever(@PathVariable(value = "id") String opdrachtgeverId) {
		opdrachtgeverService.deleteOpdrachtgever(Long.parseLong(opdrachtgeverId));
	}
	@PutMapping("/{id}")
	public Opdrachtgever vernieuwOpdrachtgever(@PathVariable(value = "id") String opdrachtgeverId, @RequestBody Opdrachtgever opdrachtgeverDetails) {
		return opdrachtgeverService.updateOpdrachtgever(Long.parseLong(opdrachtgeverId), opdrachtgeverDetails);
	}
}