package integration;

public class SearchOptions
{
	public final boolean sSongs;
	public final boolean sAlbums;
	
	public final boolean sTitle;
	public final boolean sArtist;
	
	public SearchOptions(boolean sSongs,boolean sAlbums, boolean sTitle,boolean sArtist)
	{
		this.sSongs = sSongs;
		this.sAlbums = sAlbums;
		
		this.sTitle = sTitle;
		this.sArtist = sArtist;
	}
}
