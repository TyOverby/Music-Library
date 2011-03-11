package search;

import java.util.Collections;
import java.util.Comparator;
import java.util.regex.*;
import java.util.ArrayList;

import integration.Album;
import integration.MusicLib;
import integration.SearchList;

public class Search 
{
	/**
	 * @param library The music library that you wish to search
	 * @param search The string that you are searching for
	 * @return A list of valid songs and albums that match the search criterion
	 */
	public static SearchList search(MusicLib library,String search)
	{

		SearchList returnedList = new SearchList();

		// Alphabetize albumlist
		Collections.sort(library.getAlbumList(), new Comparator(){
			public int compare(Object o1, Object o2) {
				Album a1 = (Album) o1;
				Album a2 = (Album) o2;
				return a1.getAlbumTitle().compareToIgnoreCase(a2.getAlbumTitle());
			}
		});
		

		// If album search option is true
		if (true)
		{
			ArrayList<Album> returnedAlbums = searchAlbums(library, search);
			for (int i = 0; i < returnedAlbums.size(); i++)
				returnedList.addAlbum(returnedAlbums.get(i));
		}
		return returnedList;
	}


	/**
	 * This method will search through the albums of a MusicLib object
	 * attempting to match an input string to an album title string.  It returns
	 * an array of albums.  All matches with the input string at the beginning
	 * of the album title are first (in alphabetical order) in the array, followed
	 * by those with the input string somewhere within the album title (in
	 * alphabetical order).
	 * @param library MusicLib object to search through albums
	 * @param search String to try to match to album titles
	 * @return Array of albums matching search input
	 * @author Jonathan Ellington
	 */
	public static ArrayList<Album> searchAlbums(MusicLib library, String search) {

		ArrayList<Album> returnedAlbums = new ArrayList<Album>();

		// Create pattern (which is determined by user) for matching
		Pattern inputSearch = Pattern.compile(search, Pattern.CASE_INSENSITIVE);

		/*
		 * Steps through albumList array, testing each albumTitle string
		 * against the pattern object.  Will add an album to the returnedList array
		 * only if the pattern is in the albumTitle, and if the pattern is at the
		 * beginning of the albumTitle.
		 */
		for (int n = 0; n < library.getAlbumList().size(); n++)
		{
			// Create a matcher object using albumTitle string as input
			Matcher matcher = inputSearch.matcher(library.getAlbumList().get(n).getAlbumTitle().trim());

			if (matcher.find() && matcher.start() == 0)
			{
				returnedAlbums.add(library.getAlbumList().get(n));
			}
		}

		/*
		 * Will return an album to returnedList if the pattern is anywhere in the
		 * albumTitle, but not at the beginning
		 */
		for (int n = 0; n < library.getAlbumList().size(); n++)
		{
			Matcher matcher = inputSearch.matcher(library.getAlbumList().get(n).getAlbumTitle().trim());

			if (matcher.find() && matcher.start() != 0)
			{
				returnedAlbums.add(library.getAlbumList().get(n));
			}
		}

		return returnedAlbums;
	}
}
