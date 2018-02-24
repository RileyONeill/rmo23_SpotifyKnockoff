import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SongDelete {

	/*
	 * deletes song entity
	 * 
	 * @param songID
	 */
	public void songDelete(String songID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SpotifyKnockoffJPA");

		EntityManager emanager = emfactory.createEntityManager();

		emanager.getTransaction().begin();

		Song s = emanager.find(Song.class, songID);
		emanager.remove(s);

		emanager.getTransaction().commit();

		emanager.close();
		emfactory.close();
	}
}


