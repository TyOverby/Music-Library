package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import integration.Album;
import integration.MusicLib;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AlbumList extends JList
{
	private Album[] albumList;
	private ArrayList<Album> arrayListAlbum;
	private LibraryMainFrame libraryMainFrame;
	
	public AlbumList(ArrayList<Album> list,LibraryMainFrame mainFrame)
	{
		super(ArrayListToArray(list));
		this.libraryMainFrame = mainFrame;
		this.albumList = ArrayListToArray(list);
		this.arrayListAlbum = list;
		
		
		this.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent arg0)
			{
				String name = ((JList)arg0.getSource()).getSelectedValue().toString();
				Album selected = Album.findAlbum(arrayListAlbum,name);
				changeAlbum(selected);
			}
		});
		
		/*this.addMouseListener(new MouseAdapter() 
		{
		    public void mouseClicked(MouseEvent e) 
		    {
		        int pos = locationToIndex(e.getPoint());
		        String name = albumList[pos].getAlbumTitle();
		        Album selected = Album.findAlbum(arrayListAlbum,name);
		        changeAlbum(selected);
		    }
		});*/
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
