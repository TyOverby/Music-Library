package integration;

import java.util.ArrayList;
/**
 * The album is the 2nd level of the database.
 * It has a title, and then contains songs that you add to it. 
 * @author Ty Overby
 */
public class Album 
{
	private String albumTitle;
	private ArrayList<Song> songList = new ArrayList<Song>();
	
	/**
	 * Constructs a new Song object
	 * @param albumTitle The title of the album
	 */
	public Album(String albumTitle)
	{
		this.albumTitle = albumTitle;
	}

	/**
	 * @return A string that contains the title of all songs
	 */
	public String toString()
	{
		return this.getAlbumTitle();
	}

	/**
	 * Adds a song to the list of songs in the album
	 * @param s The songs to add
	 */
	public void addSong(Song s)
	{
		songList.add(s);
		s.setAlbum(this);
	}
	/**
	 * Removes the selected song from the album
	 * @param s The song to be removed
	 */
	public void removeSong(Song s)
	{
		songList.remove(s);
	}
	
	/**
	 * @return The title of the album
	 */
	public String getAlbumTitle() 
	{
		return albumTitle;
	}
	/**
	 * @return The list of songs
	 */
	public ArrayList<Song> getSongList() 
	{
		return songList;
	}
	
	public static Album findAlbum(ArrayList<Album> albums,String search)
	{
		for(Album a:albums)
		{
			if(a.getAlbumTitle().equalsIgnoreCase(search))
			{
				return a;
			}
		}
		return new Album("");
	}
}
