package gui;

import java.util.ArrayList;

import integration.Album;
import integration.MusicLib;
import integration.Song;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class MusicTable extends JTable
{
	private MusicLib musLib;
	private String[][] listData;

	// Needed for copy constructor
	private ArrayList<Song> currentTable;

	static String[] columnNames = {"Title","Album","Artist","Duration"};
	JTableHeader header = new JTableHeader();
	// Copy constructor needed to avoid referencing the same mTable when opening a
	// new print window.
	public MusicTable(MusicTable mTable){
		super(arrayListToStr(mTable.currentTable),columnNames);
		header.setReorderingAllowed(false);
	}

	public MusicTable(MusicLib m)
	{
		super(arrayListToStr(m.getAllSongs()),columnNames);
		this.setColumnSelectionAllowed(false);
		
		header.setReorderingAllowed(false);
		this.musLib = m;
		currentTable = m.getAllSongs();
	}

	public MusicTable(Album album)
	{
		super(arrayListToStr(album.getSongList()),columnNames);
		currentTable = album.getSongList();
	}

	public MusicTable(ArrayList<Song> songs)
	{
		super(arrayListToStr(songs),columnNames);
		currentTable = songs;
	}

	public static String[][] arrayListToStr(ArrayList<Song> songs)
	{	
		String[][] toReturn = new String[songs.size()][4];

		for(int i = 0; i<songs.size();i++)
		{
			toReturn[i][0]=songs.get(i).getTitle();
			toReturn[i][1]=songs.get(i).getAlbum().getAlbumTitle();
			toReturn[i][2]=songs.get(i).getArtist();
			toReturn[i][3]=songs.get(i).getDuration();
		}


		return toReturn;
	}

	public boolean isCellEditable(int row,int column)
	{  
		return false;  

	} 
}