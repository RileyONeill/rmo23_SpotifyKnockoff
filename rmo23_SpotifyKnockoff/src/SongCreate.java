import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SongCreate {
	
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("rmo23_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Song s = new Song();
		s.setSongID(UUID.randomUUID().toString());
		s.setTitle("This song is not in the DB");
		s.setLength(10);
		s.setRecordDate("2018-02-19");
		s.setReleaseDate("2018-02-19");
		s.setFilePath("");
		
		emanager.persist(s);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();
	}

}
