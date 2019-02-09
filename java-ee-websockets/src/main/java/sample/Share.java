package sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Share {

	@Id
	@GeneratedValue
	private Long id;
	
}
