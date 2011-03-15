package integration;

import java.util.ArrayList;

/**
 * The first level of the database.  Contains an array of albums.
 * @author Ty Overby
 */
public class MusicLib 
{
	private ArrayList<Album> albumList = new ArrayList<Album>();
	
	/**
	 * 
	 */
	public String toString()
	{
		String str = "LIBRARY\n=======";
		for(Album a: this.getAlbumList())
		{
			str += "\n\t" + a.getAlbumTitle();
			
			for(Song s: a.getSongList())
			{
				str += "\n\t\t"+s.getTitle();
			}
		}
		return str;
	}
	
	/**
	 * Adds an album to the album list
	 * @param album The album that you want to add
	 */
	public void addAlbum(Album album)
	{
		albumList.add(album);
	}
	/**
	 * Removes an album from the list
	 * @param album The album that you want to remove
	 */
	public void removeAlbum(Album album)
	{
		albumList.remove(album);
	}
	/**
	 * @return The list of albums
	 */
	public ArrayList<Album> getAlbumList()
	{
		return albumList;
	}
	/**
	 * Returns all of the songs in the library
	 * @return the list of songs
	 */
	public ArrayList<Song> getAllSongs()
	{
		ArrayList<Song> songs = new ArrayList<Song>();
		for(Album a: getAlbumList())
		{
			for(Song s:a.getSongList())
			{
				songs.add(s);
			}
		}
		return songs;
	}
	
	//TODO: add a toString method for easy debugging
}
