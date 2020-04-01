package QienApp.qien.domein;
import javax.persistence.Entity;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(value="Admin", description="Bevat alle waarden van de Admin-entiteit.")
public class Admin extends Gebruiker {}