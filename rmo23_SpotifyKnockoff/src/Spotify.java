import javax.swing.table.DefaultTableModel;

import java.io.IOException;
import java.sql.SQLException;

/*
 * @author Riley O'Neill
 * @version 1.0
 * 2/14/2018
 */


public class Spotify {
	ErrorLogger errorLogger = new ErrorLogger();


	/*
	 * Queries the database to to return data to the table. Either via search term or just all the data.
	 * @param searchTerm
	 * @return data table of relevant information
	 * @return null
	 * @throws IOException
	 */
	public DefaultTableModel searchSongs(String searchTerm) throws IOException {
		String sql = "Select song_id, title, length, release_date,"
				+ " record_date from song ";
		if(!searchTerm.equals("")) {
			sql += "Where title like '%" + searchTerm + "%';";
		}
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Song ID", "Title", "Length",
					"Release Date", "Record Date"};
			return db.getDataTable(sql, columnNames);

		} catch (Exception e) {
			// TODO: handle exception
			errorLogger.log(e.getMessage());
			}
		
		return null;
	}

	/*
	 * Similar to getSongData() but albums instead
	 * @param searchTerm
	 * @return db.getDataTable
	 * @return null
	 * @throws IOException
	 */
	public DefaultTableModel searchAlbums(String searchTerm) throws IOException{
		String sql = "Select album_id, title, release_date,"
				+ " number_of_tracks, pmrc_rating from album ";
		if(!searchTerm.equals("")) {
			sql += "Where title like '%" + searchTerm + "%';";
		}
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Album ID", "Title", "Release Date",
					"Tracks", "Rating"};
			return db.getDataTable(sql, columnNames);

		} catch (Exception e) {
			// TODO: handle exception
			errorLogger.log(e.getMessage());
			}
		
		return null;
	}

	/*
	 * Similar to getSongData() but artists instead
	 * @param searchTerm
	 * @return db.getDataTable
	 * @return null
	 * @throws IOException
	 */
	public DefaultTableModel searchArtists(String searchTerm) throws IOException{
		String sql = "Select artist_id, band_name from artist ";
		if(!searchTerm.equals("")) {
			sql += "Where band_name like '%" + searchTerm + "%';";
		}
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Artist ID", "Name"};
			return db.getDataTable(sql, columnNames);

		} catch (Exception e) {
			// TODO: handle exception
			errorLogger.log(e.getMessage());

		}
		return null;
	}

	/*
	 * Unused and unfinished method to retrieve  songs on a single album
	 * @param selectedID
	 * @return db.getDataTable
	 * @return null
	 * @throws IOException
	 */
	public DefaultTableModel searchSongsByAlbum(String selectedID) throws IOException {
		String sql = "Select song_id, title, length, release_date, record_date from song join album_song on song_id = fk_song_id join album on album_id = fk_album_id where album_id = '" + selectedID + "';";

		try {
			DbUtilities db = new DbUtilities();
			return db.getDataTable(selectedID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorLogger.log(e.getMessage());

		}
		return null;
	}


}




