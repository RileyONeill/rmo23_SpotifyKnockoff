import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlbumDelete {

	/*
	 * Deletes an album from the db using entity managers.
	 * 
	 * @param albumID
	 */
	public void albumDelete(String albumID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SpotifyKnockoffJPA");
		EntityManager emanager = emfactory.createEntityManager();

		emanager.getTransaction().begin();

		Album a = emanager.find(Album.class, albumID);
		emanager.remove(a);

		emanager.getTransaction().commit();

		emanager.close();
		emfactory.close();
	}
}


