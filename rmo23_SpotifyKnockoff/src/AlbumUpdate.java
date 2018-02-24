import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlbumUpdate {

	/*
	 * Edits album information in database
	 * All params are optional besides id
	 * 
	 * @param albumID
	 * @param title
	 * @param releaseDate
	 * @param recordingCompany
	 * @param numberOfTracks
	 * @param pmrcRating
	 * @param length
	 */
	public void updateAlbum(String albumID, String title, String releaseDate, String recordingCompany, Integer numberOfTracks, String pmrcRating, Integer length) {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SpotifyKnockoffJPA");
			EntityManager emanager = emfactory.createEntityManager();

			emanager.getTransaction().begin();
			Album a = emanager.find(Album.class, albumID);
			
			if(title != null) {
				a.setTitle(title);
			}
			if(releaseDate != null) {
				a.setReleaseDate(releaseDate);
			}
			if(recordingCompany != null) {
				a.setRecordingCompany(recordingCompany);
			}
			if(numberOfTracks != null) {
				a.setNumberOfTracks(numberOfTracks);
			}
			if(pmrcRating != null) {
				a.setPMRCRating(pmrcRating);
			}
			if(length != null) {
				a.setLength(length);
			}

			emanager.persist(a);
			emanager.getTransaction().commit();

			emanager.close();
			emfactory.close();

		
		}

	}


