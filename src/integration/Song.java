package integration;

/**
 * The 3rd level in the database.
 * Represents an individual song.
 * @author TyOverby
 */
public class Song 
{
	private String title;
	private String artist;
	private String duration;
	
	private String albumStr;
	private Album album; 
	
	/**
	 * Constructs a new Song object
	 * @param title The title of the songs
	 * @param album The title of the album of the song
	 * @param artist The name of the artist
	 * @param duration The duration of the song
	 */
	public Song(String title, String album, String artist, String duration)
	{
		this.title = title;
		this.albumStr = album;
		this.artist = artist;
		this.duration = duration;
	}
	public String toString()
	{
		return this.getTitle();
	}
	
	/**
	 * Sets the album.
	 * @param album The album that the song is in
	 */
	public void setAlbum(Album album)
	{
		this.album = album;
	}
	/**
	 * Removes the song from the album
	 */
	public void delSong()
	{
		this.album.removeSong(this);
	}

	/**
	 * @return The title of the song
	 */
	public String getTitle() 
	{
		return title;
	}
	/**
	 * @return The name of the artist that made the song
	 */
	public String getArtist() 
	{
		return artist;
	}
	/**
	 * @return The duration of the song
	 */
	public String getDuration() 
	{
		return duration;
	}
	/**
	 * @return The name of the album.
	 */
	public String getAlbumStr() 
	{
		return albumStr;
	}
	/**
	 * @return the actual album object that the song is a part of
	 */
	public Album getAlbum() 
	{
		return album;
	}
}
