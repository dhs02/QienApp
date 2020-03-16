package QienApp.qien.controller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import QienApp.qien.domein.Opdrachtgever;

@Repository
public interface OpdrachtgeverRepository extends CrudRepository<Opdrachtgever, Long>{}