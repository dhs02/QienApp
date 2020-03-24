package QienApp.qien.controller.form;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import QienApp.qien.domein.urenform.GewerkteDag;

@Repository
public interface GewerkteDagRepository extends 
					CrudRepository<GewerkteDag, Long> {

}