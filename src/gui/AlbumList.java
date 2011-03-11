package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import integration.Album;
import integration.MusicLib;

import javax.swing.JList;

public class AlbumList extends JList
{
	private Album[] albumList;
	private ArrayList<Album> arrayListAlbum;
	private LibraryMainFrame libraryMainFrame;
	
	public AlbumList(MusicLib lib,LibraryMainFrame libraryMainFrame)
	{
		super(ArrayListToArray(lib.getAlbumList()));
		this.libraryMainFrame = libraryMainFrame;
		this.albumList = ArrayListToArray(lib.getAlbumList());
		this.arrayListAlbum = lib.getAlbumList();
		
		
		
		this.addMouseListener(new MouseAdapter() 
		{
		    public void mouseClicked(MouseEvent e) 
		    {
		        int pos = locationToIndex(e.getPoint());
		        String name = albumList[pos].getAlbumTitle();
		        Album selected = Album.findAlbum(arrayListAlbum,name);
		        changeAlbum(selected);
		    }
		});
	}
	
	public void changeAlbum(Album a)
	{
		this.libraryMainFrame.updateTable(a);
	}
	
	/**
	 * Gets an array list of albums and gives out something that the JList can process
	 * @param aList
	 * @return
	 */
	public static Album[] ArrayListToArray(ArrayList<Album> aList)
	{
		Album[] toReturn = new Album[aList.size()];
		for(int i = 0; i<aList.size();i++)
		{
			toReturn[i]=aList.get(i);
		}
		return toReturn;
	}
}
