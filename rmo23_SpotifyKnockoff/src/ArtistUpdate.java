import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ArtistUpdate {

	/*
	 * Updates artists in db.
	 * All params optional.
	 * 
	 * @param artistID
	 * @param firstName
	 * @param lastName
	 * @param bandName
	 * @param bio
	 */
	public void updateArtist(String artistID, String firstName, String lastName, String bandName, String bio) {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SpotifyKnockoffJPA");
			EntityManager emanager = emfactory.createEntityManager();

			emanager.getTransaction().begin();
			Artist a = emanager.find(Artist.class, artistID);
			
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


