package QienApp.qien.controller.urenform;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import QienApp.qien.domein.Medewerker;
import QienApp.qien.domein.urenform.Urendeclaratie;


@Repository
public interface UrenDeclaratieRepository extends CrudRepository<Urendeclaratie, Long> 
{	
	/** @QUERY TEST: werkt nog niet...
	 * 
	 * @param id
	 * @return
	 */
	@Query(value = "SELECT * FROM `urendeclaratie` WHERE medewerker_id = ?1", nativeQuery = true)
	Iterable <Urendeclaratie> findByMedewerkerId(long id); 
	
	/** MICHIEL TEST, werkt nog niet...
	 * 
	 * @param medewerker
	 * @return
	 */
	List<Urendeclaratie> findByMedewerker(Medewerker medewerker);
	

	List<Urendeclaratie> findAllBymaandNaam(String maandNaam);
}