package gui;

import java.util.ArrayList;

import integration.Album;
import integration.MusicLib;
import integration.Song;

import javax.swing.JTable;

public class MusicTable extends JTable
{
	private MusicLib musLib;
	private String[][] listData;
	
	static String[] columnNames = {"Title","Album","Artist","Duration"};

	
	public MusicTable(MusicLib m)
	{
		super(arrayListToStr(m.getAllSongs()),columnNames);
		this.setColumnSelectionAllowed(true);
		this.musLib = m;
		
		
	}
	
	public MusicTable(Album album)
	{
		super(arrayListToStr(album.getSongList()),columnNames);
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
