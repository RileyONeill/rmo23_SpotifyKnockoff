import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @author Riley O'Neill
 * @version 1.0
 */

@Entity
@Table (name = "artist")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column (name = "artist_id")
	private String artistID;
	
	@Column (name = "first_name")
	private String firstName;
	
	@Column (name = "last_name")
	private String lastName;
	
	@Column (name = "band_name")
	private String bandName;
	
	@Column (name = "bio")
	private String bio;
	
	/*
	 * Constructor takes all attributes manually, besides the auto-generated id.
	 * Inserts data into database.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param bandName
	 * @throws SQLException
	 */
	
	public Artist() {
		super();
		this.artistID = UUID.randomUUID().toString();
	}
	
	public Artist(String firstName, String lastName, String bandName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		this.artistID = UUID.randomUUID().toString();
		
		String command = "insert into artist (first_name, last_name, band_name, artist_id)"
				+ "Values (?,?,?,?);" ;
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(command);
			ps.setString(1, this.firstName);
			ps.setString(2, this.lastName);
			ps.setString(3, this.bandName);
			ps.setString(4, this.artistID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	/*
	 * Constructor based on similar one for Song class.
	 * Takes all attributes manually.
	 * Creates artistTable vector, because the other one did too.
	 * @param artistID
	 * @param firstName
	 * @param lastName
	 * @param bandName
	 */
	public Artist(String artistID, String firstName, String lastName, String bandName) {
		super();
		Vector<Vector<Artist>> artistTable = new Vector<>();
		this.artistID = artistID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
	}

	/*
	 * Retrieves song info from database and creates new object.
	 * @param artistID
	 * @throws SQLException
	 */
	public Artist(String artistID) {
		super();
		String sql = "Select * from artist where artist_id = '" + artistID + "';";
		System.out.println(sql);
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.artistID = rs.getString("artist_id");
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name").toString();
				this.bandName = rs.getString("band_name").toString();
				this.bio = rs.getString("bio");
				// System.out.println("Song title from database: " + this.title);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Artist newArtist = new Artist(this.artistID, this.firstName, this.lastName, this.bandName);

	}

	/*
	 * Deletes artist from database
	 * @param artistID
	 * @throws SQLException
	 */
	public void deleteArtist(String artistID) {
		String sql = "Delete from artist where artist_id = ?";
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, artistID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * @param artistID
	 */
	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}
	
	public String getArtistID() {
		return artistID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	/*
	 * @return artistID
	 */
	public String getID() {
		return this.artistID;
	}
	/*
	 * @return firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}
	/*
	 * @return lastName
	 */
	public String getLastName() {
		return this.lastName;
	}
	/*
	 * @return bandName
	 */
	public String getBandName() {
		return this.bandName;
	}
	/*
	 * @return bio
	 */
	public String getBio() {
		return this.bio;
	}
	/*
	 * @param bio
	 * @throws SQLException
	 */
	public void setBio(String bio) {
		this.bio = bio;
		String sql = "Update artist set bio = ? where artist_id = ?;";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bio);
			ps.setString(2, this.artistID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
