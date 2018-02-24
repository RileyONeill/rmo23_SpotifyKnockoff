import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SongCreate {
	
	/*
	 * Creates a song entity in the db
	 * 
	 * @param title
	 * @param length
	 * @param recordDate
	 * @param releaseDate
	 * @param filePath
	 */
	public void createSong(String title, int length, String recordDate, String releaseDate, String filePath) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SpotifyKnockoffJPA");
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Song s = new Song();
		s.setSongID(UUID.randomUUID().toString());
		s.setTitle(title);
		s.setLength(length);
		s.setRecordDate(recordDate);
		s.setReleaseDate(releaseDate);
		s.setFilePath(filePath);
		
		emanager.persist(s);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();
	}


}
