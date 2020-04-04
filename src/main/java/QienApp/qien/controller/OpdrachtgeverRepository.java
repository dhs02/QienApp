package QienApp.qien.controller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import QienApp.qien.domein.Opdrachtgever;

import java.util.Optional;

@Repository
public interface OpdrachtgeverRepository extends CrudRepository<Opdrachtgever, Long>{
    public Optional<Opdrachtgever> findByBedrijfsnaam(String bedrijfsnaam);
}