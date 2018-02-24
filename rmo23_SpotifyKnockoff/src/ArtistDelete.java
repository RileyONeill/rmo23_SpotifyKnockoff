import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ArtistDelete {

	/*
	 * deletes artist from db
	 * 
	 * @param artistID
	 */
	public void artistDelete(String artistID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SpotifyKnockoffJPA");
		EntityManager emanager = emfactory.createEntityManager();

		emanager.getTransaction().begin();

		Artist a = emanager.find(Artist.class, artistID);
		emanager.remove(a);

		emanager.getTransaction().commit();

		emanager.close();
		emfactory.close();
	}
}


