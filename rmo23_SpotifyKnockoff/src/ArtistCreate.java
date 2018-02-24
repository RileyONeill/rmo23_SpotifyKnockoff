import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ArtistCreate {
	
	/*
	 * creates artist entity
	 * 
	 * @param firstName
	 * @param lastName
	 * @param bandName
	 * @param bio
	 */
	public void createArtist(String firstName, String lastName, String bandName, String bio) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SpotifyKnockoffJPA");
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Artist a = new Artist();
		a.setArtistID(UUID.randomUUID().toString());
		
		if(firstName != null) {
			a.setFirstName(firstName);
		}
		if(lastName != null) {
			a.setLastName(lastName);
		}
		if(bandName != null) {
			a.setBandName(bandName);
		}
		if(bio != null) {
			a.setBio(bio);
		}
		
		emanager.persist(a);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();
	}

}
