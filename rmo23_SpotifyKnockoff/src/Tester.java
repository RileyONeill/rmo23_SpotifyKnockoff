import java.awt.EventQueue;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

//package rmo23_SpotifyKnockoff;

public class Tester {
	static ErrorLogger errorLogger = new ErrorLogger();

	public static void main(String[] args) throws IOException 
    {
        try
        {
            int a = 20/0;
        } catch (Exception e)
        {
            System.out.println(e.toString());
            errorLogger.log(e.getMessage());
            // OR
            // System.out.println(e);
        }
    }
	
		/* Code segment submitted for Homework 2
		Album album1 = new Album("Exmilitary", "2011-04-25", "Independant", 13, "Explicit", 48);
		Album album2 = new Album("The Money Store", "2012-04-24", "Epic", 13, "Explicit", 41);
		Album album3 = new Album("Madvillainy", "2001-03-23", "Stone\'s Throw", 22, "Explicit", 46);
		Song song1 = new Song("Guillotine", 4, "2011-04-25", "2011-04-25");
		Song song2 = new Song("Takyon", 3, "2011-04-25", "2011-04-25");
		Song song3 = new Song("Accordion", 2, "2001-03-23","2001-03-23");
		Artist artist1 = new Artist(null, null, "Death Grips");
		Artist artist2 = new Artist(null, null, "Madvillain");

		
		artist1.setBio("Often described as difficult to listen to.");
		artist2.setBio("Collaborative effort between rapper MF Doom and producer Madlib");
		album1.addSong(song1);
		album2.addSong(song2);
		album3.addSong(song3);
		album2.deleteSong(song2);
		song1.addArtist(artist1);
		song2.addArtist(artist1);
		song2.deleteArtist(artist1.getID());

		Song song4 = new Song(song2.getSongID());
	*/
		
		/*
		Vector<Vector<String>> songTable = new Vector<>();
		try {
			DbUtilities db = new DbUtilities();
			String sql = "Select * from song;";
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {
				Song s = new Song(rs.getString("song_id"),
						rs.getString("title"),
						rs.getDouble("length"),
						rs.getString("release_date"),
						rs.getString("record_date"));
				songTable.add(s.getSongRecord());
			}
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for(int i = 0; i<songTable.size(); i++) {
			for (int j = 0; j < songTable.get(i).size(); j++) {
				System.out.print(songTable.get(i).get(j) + "\t\t\t");
			}
			System.out.println();
			*/
		}

		
		
	