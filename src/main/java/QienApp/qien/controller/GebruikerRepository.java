package QienApp.qien.controller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import QienApp.qien.domein.Gebruiker;

@Repository
public interface GebruikerRepository<T extends Gebruiker> extends CrudRepository<T, Long> {}