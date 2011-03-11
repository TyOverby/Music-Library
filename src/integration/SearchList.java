package integration;

import java.util.ArrayList;

/**
 * Returns two different lists, a list of songs and a list of albums.
 * The albums and songs that are returned match the search criterion.
 * @author Ty Overby
 */
public class SearchList
{
	private ArrayList<Song> songList = new ArrayList<Song>();
	private ArrayList<Album> albumList = new ArrayList<Album>();
	
	/**
	 * Adds a song to the list of songs
	 * @param song The song that you are adding
	 */
	public void addSong(Song song)
	{
		songList.add(song);
	}
	/**
	 * Adds an album to the list of albums
	 * @param album
	 */
	public void addAlbum(Album album)
	{
		albumList.add(album);
	}
	
	/**
	 * @return The list of songs
	 */
	public ArrayList<Song> getSongList()
	{
		return songList;
	}
	/**
	 * @return The list of albums
	 */
	public ArrayList<Album> getAlbumList()
	{
		return albumList;
	}
	
	
}
