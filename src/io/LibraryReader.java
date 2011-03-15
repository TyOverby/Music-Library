package io;

import java.io.*;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import integration.Album;
import integration.MusicLib;
import integration.Song;

public class LibraryReader 
{
	static private MusicLib library = new MusicLib();
	public static File fileURI;

	/**
	 * Reads a file and parses it to a MusicLib object
	 * @author James Meyers
	 * @param libraryFile the global filepath to the library file
	 * @return a MusicLib object that contains the parsed data
	 * @throws IOException 
	 * @throws IOException 
	 */

	public static MusicLib read(File libraryFile) throws IOException
	{
		
		
		String fileLine = null;
		ArrayList<String> albumNames = new ArrayList<String>();
		ArrayList<Song> songList = new ArrayList<Song>();

		//Opens the input file
		FileReader fReader = new FileReader(libraryFile);
		BufferedReader bReader = new BufferedReader(fReader);

		try {
			fileLine = bReader.readLine();	//Gets rid of the header row
			fileLine = bReader.readLine();	//Reads the first line
			while (fileLine != null) {

				//Splits the line by commas
				StringTokenizer st = new StringTokenizer(fileLine, ",");

				//Breaks up the line and assigns the parts
				String title = st.nextToken();
				String album = st.nextToken();
				String artist = st.nextToken();
				String duration = st.nextToken();

				//Creates a new song with the data as an argument
				songList.add(new Song(title, album, artist, duration));	//Creates a new Song object

				//Checks if the list of album names contains the string "album". 
				//If it does it does not add it again; but if it doesn't, it adds it.
				if (albumNames.contains(album)) {	
				} else {
					albumNames.add(album);
				}
				fileLine = bReader.readLine();	//Reads the next line
			}

		} catch (IOException e) {

			//If we hit this, there was an error reading a line from the file.
			JOptionPane.showMessageDialog(null, "Error reading a line from the file");
			e.printStackTrace();
		} catch (NoSuchElementException e) {

			//If we hit this, the file was formatted incorrectly.
			JOptionPane.showMessageDialog(null, "File was formatted incorrectly");
			e.printStackTrace();
		}
		int a;
		int s;

		//These loops create the Album objects, add them to the MusicLib library, 
		//and add the Songs to the correct Albums.
		for (a = 0; a < albumNames.size(); a++) {
			Album newAlbum = new Album(albumNames.get(a));	//Creates a new Album object
			library.addAlbum(newAlbum);	//Adds the Album to the library
			for (s = 0; s < songList.size(); s++) {
				if (songList.get(s).getAlbumStr().equals(albumNames.get(a))) {
					
						newAlbum.addSong(songList.get(s));	//Adds the Song to the Album
				}
			}
		}
		fReader.close();	//Closes file
		bReader.close();	//Likewise
		
		fileURI=libraryFile;
	
		return library;		//Returns the MusicLib object
	}
	public static MusicLib writeFile(File originalFile) throws IOException {

		StringBuffer buffer = new StringBuffer();
		FileWriter fw = new FileWriter(originalFile);
		BufferedWriter bw = new BufferedWriter(fw);
		//writes the newContents to a file
		//writes the headers
		bw.write("title,album,artist,duration");
		Collator collator = Collator.getInstance(Locale.US);

		List<String> lines  = new ArrayList<String>();

		for(Song s:library.getAllSongs())
		{
			String songLine = s.getTitle() + "," + s.getAlbum() + "," + s.getArtist() + "," + s.getDuration();
			lines.add(songLine);
		}
		Collections.sort(lines, collator);

		for (int i = 0; i < lines.size(); i++) {
			String sorted = lines.get(i);
			buffer.append(System.getProperty("line.separator")).append(sorted);
		}

		bw.write(buffer.toString());
		bw.close();
		fw.close();
		return library;
	}
}
