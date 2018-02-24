
public class JPATester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SongCreate sc = new SongCreate();
		SongUpdate su = new SongUpdate();
		SongDelete sd = new SongDelete();
		
		AlbumCreate ac = new AlbumCreate();
		AlbumUpdate au = new AlbumUpdate();
		AlbumDelete ad = new AlbumDelete();
		
		ArtistCreate arc = new ArtistCreate();
		ArtistUpdate aru = new ArtistUpdate();
		ArtistDelete ard = new ArtistDelete();
		
		Song songExample = new Song("Uhh", 5, "2015-05-05", "2015-05-05");
		Album albumExample = new Album("Hmm", "2014-04-04", "Limewire", 6, "Clean", 9);
		Artist artistExample = new Artist("Craig", "I forget", "Craig and the Gang");
		
		
		sc.createSong("Looney Tunes Theme", 1, "2018-02-02", "2018-02-02", null);
		su.updateSong(songExample.getSongID(), "Title", null);
		sd.songDelete(songExample.getSongID());
		
		ac.createAlbum("Songs from Looney Tunes", "2017-05-05", "Death Row Records", 35, "Explicit", 30);
		au.updateAlbum(albumExample.getAlbumID(), null, null, null, null, null, 2);
		ad.albumDelete(albumExample.getAlbumID());
		
		arc.createArtist("Deafheaven", null, null, "A metal band");
		aru.updateArtist(artistExample.getArtistID(), null, null, null, "The kind of music my older brother listens to");
		ard.artistDelete(artistExample.getArtistID());

	}

}
