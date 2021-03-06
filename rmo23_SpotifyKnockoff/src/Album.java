/*
 * @author Riley O'Neill
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name = "album")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	
	@Column (name = "album_id")
	private String albumID;
	
	@Column (name = "title")
	private String title;
	
	@Column (name = "release_date")
	private String releaseDate;
	
	@Column (name = "cover_image_path")
	private String coverImagePath;
	
	@Column (name = "recording_company_name")
	private String recordingCompany;
	
	@Column (name = "number_of_tracks")
	private int numberOfTracks;
	
	@Column (name = "pmrc_rating")
	private String pmrcRating;
	
	@Column (name = "length")
	private double length;
	
	@Transient
	private Hashtable<String,Song> albumSongs = new Hashtable<String,Song>();
	
	public Album() {
		super();
		this.albumID = UUID.randomUUID().toString();
	}


	/*
	 * Constructor method. User inputs all data manually, except for ID which is generated by method.
	 * Method then uses prepared statement to insert data into database.
	 * also instantiates albumSongs hashtable.
	 * 
	 * @param title
	 * @param releaseDate
	 * @param recordingCompany
	 * @param numOfTracks
	 * @param pmcrRating
	 * @param length
	 * @throws SQLException
	 */
	public Album(String title, String releaseDate, String recordingCompany, int numOfTracks, String pmcrRating, int length) {
		super();
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numOfTracks;
		this.pmrcRating = pmcrRating;
		this.albumID = UUID.randomUUID().toString();
		this.length = length;	
		albumSongs = new Hashtable<String, Song>();


		String command = "insert into album (title, release_date, recording_company_name, number_of_tracks, PMRC_rating, length, album_id) "
				+ "values(?,?,?,?,?,?,?);";
	
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(command);
			ps.setString(1, this.title);
			ps.setString(2, this.releaseDate);
			ps.setString(3, this.recordingCompany);
			ps.setInt(4, this.numberOfTracks);
			ps.setString(5, pmrcRating);
			ps.setDouble(6, length);
			ps.setString(7, albumID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * This is an attempt to create a constructor that takes all of the parameters for the upcoming Album(String albumID) method. But that method
	 * does not recognize this constructor for some reason. It is a replication of the method for the song class that also creates a vector.
	 * 
	 * @param albumID
	 * @param title
	 * @param coverImagePath
	 * @param releaseDate
	 * @param recordingCompany
	 * @param numberOfTracks
	 * @param pmrcRating
	 * @param length
	 */
	public Album(String albumID, String title, String coverImagePath, String releaseDate,
			String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		super();
		Vector<Vector<Album>> albumTable = new Vector<>();
		this.albumID = albumID;
		this.title = title;
		this.coverImagePath = coverImagePath;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
	}
	
	
	/*
	 * Alternate constructor. Takes albumID and retrieves the rest of the data from the database.
	 * Then tries, but fails, to create a new album object from those parameters. It insists that a constructor method of these parameters
	 * does not exists but it definitely does. I cannot figure this out, I commented out the faulty line in the meantime.
	 * 
	 * @param albumID
	 * @throws SQLException
	 */
	public Album(String albumID) {
		super();
		String sql = "Select * from albums where album_id = '" + albumID + "';";
		albumSongs = new Hashtable<>();
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.albumID = rs.getString("album_id");
				// System.out.println("Song ID from database: " + this.songID);
				this.title = rs.getString("title");
				this.coverImagePath = rs.getString("cover_image_path").toString();
				this.releaseDate = rs.getString("release_date").toString();
				this.recordingCompany = rs.getString("recording_company");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("pmrc_rating");
				this.length = rs.getInt("length");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Album newAlbum = Album(this.albumID, this.title, this.coverImagePath, this.releaseDate,
//				this.recordingCompany, this.numberOfTracks, this.pmrcRating, this.length);
	}

	/*
	 * retrieves albumID
	 * @return albumID
	 */
	public String getID() {
		return this.albumID;
	}
	
	/*
	 * Creates a relationship between song and album in the album_song table of the database.
	 * Also inserts data into hashtable.
	 * @param song
	 * @throws SQLException
	 */
	public void addSong(Song song) {
		this.albumSongs.put(song.getSongID(), song);
		String sql =  "Insert into album_song (fk_song_id, fk_album_id) values (?,?);";
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, song.getSongID());
			ps.setString(2, this.getID());
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * retrieves albumSongs hashtable information
	 * @return albumSongs
	 */
	public Map<String, Song> getSong() {
		return albumSongs;
	}
	
	/*
	 * Removes album data from database.
	 * @param albumID
	 * @throws SQLException
	 */
	public void deleteAlbum(String albumID) {
		String sql = "Delete from album where album_id = ?";
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, albumID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * removes song from hashtable but not database
	 * @param songID
	 */
	public void deleteSong(String songID) {
		albumSongs.remove(songID);
	}
	/*
	 * Same as above but using different parameter.
	 * @param song
	 */
	public void deleteSong(Song song) {
		albumSongs.values().remove(song);
	}
	/*
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/*
	 * @return releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}
	/*
	 * @return recordingCompany
	 */
	public String getRecordingCompany() {
		return recordingCompany;
	}
	/*
	 * @return coverImagePath
	 */
	public String getCoverImagePath() {
		return coverImagePath;
	}
	/*
	 * @param coverImagePath
	 * @throws SQLException
	 */
	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
		String sql = "Update song set cover_image_path = ? where album_id = ?;";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, coverImagePath);
			ps.setString(2, this.albumID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 * @return numberOfTracks
	 */
	public int getNumberOfTracks() {
		return numberOfTracks;
	}
	/*
	 * @return pmrcRating
	 */
	public String getPMRCRating() {
		return pmrcRating;
	}
	/*
	 * @param rating
	 * @throws SQLException
	 */
	public void setPMRCRating(String rating) {
		this.pmrcRating = rating;
		String sql = "Update song set pmrc_rating = ? where album_id = ?;";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, rating);
			ps.setString(2, this.albumID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 * @return length
	 */
	public double getLength() {
		return length;
	}
	/*
	 * @return albumSongs
	 */
	public Hashtable getAlbumSongs() {
		return albumSongs;
	}
	public String getAlbumID() {
		return albumID;
	}


	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}


	public String getPmrcRating() {
		return pmrcRating;
	}


	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
	}


	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}


	public void setLength(double length) {
		this.length = length;
	}


	public void setAlbumSongs(Hashtable<String, Song> albumSongs) {
		this.albumSongs = albumSongs;
	}
}
