package QienApp.qien.controller.urenform;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import QienApp.qien.domein.urenform.Urendeclaratie;


@Repository
public interface UrenDeclaratieRepository extends 
					CrudRepository<Urendeclaratie, Long> {


}
