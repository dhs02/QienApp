package QienApp.qien.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import QienApp.qien.domein.Werkgever;

	@Component
	public interface WerkgeverRepository extends CrudRepository<Werkgever, Long>{

	}