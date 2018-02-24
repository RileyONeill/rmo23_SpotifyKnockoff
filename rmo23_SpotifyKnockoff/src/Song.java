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
@Table (name = "song")
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column (name = "song_id")
	private String songID;
	
	@Column (name = "title")
	private String title;
	
	@Column (name = "length")
	private double length;
	
	@Column (name = "file_path")
	private String filePath;
	
	@Column (name = "release_date")
	private String releaseDate;
	
	@Column (name = "record_date")
	private String recordDate;
	
	@Transient
	Map<String, Artist> songArtists;
	
	
	public Song() {
		super();
		
	}
	
	/*
	 * Constructor. Creates song object, taking all data manually from user beside songID which is auto generated.
	 * Also instantiates songArtists hashtable.
	 * Inserts song data into database.
	 * 
	 * @param title
	 * @param length
	 * @param releaseDate
	 * @param recordDate
	 * @throws SQLException
	 */
	public Song(String title, double length, String releaseDate, String recordDate) {
		super();
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = UUID.randomUUID().toString();
		songArtists = new Hashtable<String, Artist>();
		
		String command = "insert into song (title, length, release_date, record_date, song_id)" ;
		command += "VALUES (?, ?, ?, ?, ?);";		
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(command);
			ps.setString(1, this.title);
			ps.setDouble(2,  this.length);
			ps.setString(3, this.releaseDate);
			ps.setString(4, this.recordDate);
			ps.setString(5, this.songID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Takes all song data manually from user and inserts data into a vector.
	 * 
	 * @param songID
	 * @param title
	 * @param length
	 * @param releaseDate
	 * @param recordDate
	 * @throws SQLException
	 */
	public Song(String songID, String title, double length, String releaseDate, String recordDate) {
		super();
		Vector<Vector<Song>> songTable = new Vector<>();
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = songID;
	}
	
	public void setSongID(String songID) {
		this.songID = songID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}


	public void setSongArtists(Map<String, Artist> songArtists) {
		this.songArtists = songArtists;
	}

	/*
	 * Retrieves song info from database using songID then creates new object.
	 * Also instantiates hashtable.
	 * @param songID
	 * @throws SQLException
	 */
	public Song(String songID) {
		songArtists = new Hashtable<String, Artist>();
		String sql = "Select * from song where song_id = '" + songID + "';";
		System.out.println(sql);
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.songID = rs.getString("song_id");
				System.out.println("Song ID from database: " + this.songID);
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordDate = rs.getDate("record_date").toString();
				this.length = rs.getInt("length");
				System.out.println("Song title from database: " + this.title);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//create new song object
		Song newSong = new Song(this.songID, this.title, this.length, this.releaseDate, this.recordDate);
		
	}
	
	/*
	 * @return releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}
	
	/*
	 * @return songID
	 */
	public String getSongID() {
		return songID;
	}
	/*
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/*
	 * @return length
	 */
	public double getLength() {
		return length;
	}
	/*
	 * @return filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/*
	 * Uses prepared statement to update song row with filepath
	 * @param filePath
	 * @throws SQLException
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		String sql = "Update song set file_path = ? where song_id = ?;";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, filePath);
			ps.setString(2, this.songID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 * @return recordDate
	 */
	public String getRecordDate() {
		return recordDate;
	}

	/*
	 * @return songArtists
	 */
	public Map<String, Artist> getSongArtists(){
		return songArtists;
	}
		
	/*
	 * Associates an artist with a song in the song_artist table. Also puts info in hashtable.
	 * @param artist
	 * @throws SQLException
	 */
	public void addArtist(Artist artist) {
		songArtists.put(artist.getID(), artist);
		String sql =  "Insert into song_artist (fk_song_id, fk_artist_id) values (?,?);";
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2, artist.getID());
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Deletes a song from the database
	 * @param songID
	 * @throws SQLException
	 */
	public void deleteSong(String songID) {
		String sql = "Delete from song where song_id = ?";
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, songID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * removes artist value from hashtable
	 * @param artist
	 */
	public void deleteArtist(Artist artist) {
		songArtists.values().remove(artist);
	}
	/*
	 * Same thing, different parameter
	 * @param artistID
	 */
	public void deleteArtist(String artistID) {
		songArtists.remove(artistID);
	}
	

	/*
	 * @return songRecord
	 */
	Vector<String> getSongRecord(){
		Vector<String> songRecord = new Vector<>();
		songRecord.add(this.songID);
		songRecord.add(this.title);
		songRecord.add(String.valueOf(this.length));
		songRecord.add(this.releaseDate);
		songRecord.add(this.recordDate);
		songRecord.add(this.filePath);
		return songRecord;
	}

	

}
