import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SongUpdate {


	/*
	 * Updates song info in db.
	 * All params optional.
	 * 
	 * @param songID
	 * @param title
	 * @param filePath
	 */
	public void updateSong(String songID, String title, String filePath) {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SpotifyKnockoffJPA");
			EntityManager emanager = emfactory.createEntityManager();

			emanager.getTransaction().begin();
			Song s = emanager.find(Song.class, songID);
			
			if(title != null) {
				s.setTitle(title);

			}
			if(filePath != null) {
				s.setFilePath(filePath);
			}

			emanager.persist(s);
			emanager.getTransaction().commit();

			emanager.close();
			emfactory.close();

		
		}

	}


