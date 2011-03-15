package gui;

import integration.Album;
import integration.MusicLib;
import integration.SearchList;
import integration.Song;
import io.FileOpener;
import io.LibraryReader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class LibraryMainFrame extends JFrame
{
	private MusicLib library;
	private LibraryMainFrame mainFrame;

	Splashscreen splash = new Splashscreen(4000);
	private JPanel buttonPanel = new JPanel();
	private boolean hack = true;

	//Set up the main panel
	private JScrollPane mainScrollPanel;
	private MusicTable mTable;

	//Set up the album list
	private JPanel albumPanel = new JPanel(new BorderLayout());
	private JScrollPane albumScrollPanel;
	private AlbumList aList;

	private final ClassLoader cldr = this.getClass().getClassLoader();
	private final ImageIcon addImage = null; // new ImageIcon(cldr.getResource("assets/addicon.gif"));
	private final ImageIcon logo = new ImageIcon(cldr.getResource("assets/logo.gif"));
	//All of the buttons that go on the button panel
	private JButton addAlbumButton = new JButton("Add Album",addImage);
	private JButton addTrackButton = new JButton("Add Track",addImage);
	private JButton deleteSongButton = new JButton("Remove Song",addImage);
	private JButton deleteAlbumButton = new JButton("Remove Album",addImage);
	private JButton printLibButton = new JButton ("Print Library",addImage);
	private JButton searchButton = new JButton("Search",addImage);
	private JButton importLibraryButton = new JButton ("Import Library",addImage);
	private JLabel tccLabel = new JLabel(logo);
	//For the list pane
	private JButton viewLibraryButton = new JButton("View All");


	private final int WINDOW_WIDTH = 1100;
	private final int WINDOW_HEIGHT = 600;

	public LibraryMainFrame(MusicLib library) 
	{
		this.mainFrame = this;
		this.library = library;

		//add the button panel to the top
		addButtons();
		this.add(buttonPanel,BorderLayout.NORTH);

		//add the main table layout to the center
		mTable = new MusicTable(library);	//add the table to the pane
		mainScrollPanel = new JScrollPane(mTable); 	//add the main panel to the scroll pane
		this.add(mainScrollPanel,BorderLayout.CENTER);	//add the scroll pane to the main frame

		//add the album list on the side
		aList = new AlbumList(library.getAlbumList(), this);
		albumPanel.add(aList,BorderLayout.CENTER);		//adds the JList to the main pane
		albumPanel.add(viewLibraryButton,BorderLayout.NORTH);
		albumScrollPanel = new JScrollPane(albumPanel); //add the main panel to the scroll pane 
		this.add(albumScrollPanel,BorderLayout.WEST);	//adds the scroll panel to the main frame






		//Crap that you don't mess with!
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); //Sets the bounds of the program window, centering it
		int x = (screen.width-900)/2;
		int y = (screen.height-600)/2;
		setBounds(x,y,WINDOW_WIDTH,WINDOW_HEIGHT);
		setVisible(true);		
		this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		this.setTitle("TCC Music Library Version 1.0");
		this.setMinimumSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
	}
	public void updateMusicLib(MusicLib library)
	{
		this.library = library;
	}

	public void updateTable()
	{
		updateTable(this.library);
		try {
			LibraryReader.writeFile(LibraryReader.fileURI);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error saving file");
			e.printStackTrace();
		}
		hack();
	}
	
	/**
	 * Updates the song table with the specified library
	 * @param library the library to update it with
	 */
	public void updateTable(MusicLib library)
	{
		this.updateMusicLib(library);

		this.mTable = new MusicTable(library);
		this.remove(this.mainScrollPanel);
		this.mainScrollPanel = new JScrollPane(mTable);
		this.add(this.mainScrollPanel,BorderLayout.CENTER);
		
		this.hack();//YAY Hacking!
		
		try {
		
			LibraryReader.writeFile(LibraryReader.fileURI);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error saving file");
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the song table with the specified album
	 * @param album
	 */
	public void updateTable(Album album)
	{
		this.mTable = new MusicTable(album);
		this.remove(this.mainScrollPanel);
		this.mainScrollPanel = new JScrollPane(mTable);
		this.add(this.mainScrollPanel,BorderLayout.CENTER);
		this.hack();//YAY Hacking!
	}
	
	public void updateTable(SearchList search)
	{
		this.mTable = new MusicTable(search.getSongList());
		this.remove(this.mainScrollPanel);
		this.mainScrollPanel = new JScrollPane(mTable);
		this.add(this.mainScrollPanel,BorderLayout.CENTER);
		this.hack();//YAY Hacking!
	}

	public void updateList()
	{
		updateList(this.library);
		
	}
	public void updateList(MusicLib library)
	{
		this.aList = new AlbumList(library.getAlbumList(),this);
		albumPanel.removeAll();
		albumPanel.add(viewLibraryButton,BorderLayout.NORTH);
		albumPanel.add(this.aList,BorderLayout.CENTER);
		
		hack();//OH NOES
	}
	public void updateList(SearchList search)
	{
		this.aList = new AlbumList(search.getAlbumList(),this);
		albumPanel.removeAll();
		albumPanel.add(viewLibraryButton,BorderLayout.NORTH);
		albumPanel.add(this.aList,BorderLayout.CENTER);
		
		hack();//OH NOES

	}
	
	
	/**
	 * Makes the panel change size.  Don't ask why.  Everything will break if this doesn't work
	 */
	public void hack()
	{
		int change = 1;
		if(this.hack)
		{
			change = 1;
		}
		else
		{
			change = -1;
		}
		this.setSize(this.getWidth()+change,this.getHeight());
		this.hack = !this.hack;
	}

	/**
	 * Adds buttons to the pane.
	 */
	private void addButtons()
	{
		
		//Add all of the buttons to their panel
		buttonPanel.add(addAlbumButton); 
		buttonPanel.add(addTrackButton);
		buttonPanel.add(deleteSongButton);
		buttonPanel.add(deleteAlbumButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(importLibraryButton);
		buttonPanel.add(printLibButton);
		buttonPanel.add(tccLabel);
		
		//Set up the action listeners for all of the buttons
		addAlbumButton.addActionListener(new AddAlbumListener());
		addTrackButton.addActionListener(new AddTrackListener());
		searchButton.addActionListener(new SearchListener());
		importLibraryButton.addActionListener(new ImportLibraryListener());
		deleteAlbumButton.addActionListener(new DeleteAlbumListener());
		deleteSongButton.addActionListener(new DeleteTrackListener());
		printLibButton.addActionListener(new PrintLibListener());
		viewLibraryButton.addActionListener(new ViewAllListener());

	}

	private class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			try {
				LibraryReader.writeFile(LibraryReader.fileURI);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Error saving file");
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}

	private class AddAlbumListener implements ActionListener{
		public void actionPerformed(ActionEvent e){

			// instance of album object
			Album newAlbum;
			// album title user will input
			String albumTitle;
			// user inputs album
			albumTitle = getInput("Enter the new album to add.");
			// new album added to MusicLib
			if(albumTitle == null || !albumTitle.trim().isEmpty())
			{
				newAlbum = new Album(albumTitle);
				
				library.addAlbum(newAlbum);
				updateList();
			}
		}
	}

	private class AddTrackListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			// declaring strings that user will input
			Song song;
			String album;
			String trackName;
			String duration;
			String artistName;
			// user input of song arguments
			trackName = getInput("Enter the track NAME.");
			if(trackName == null){return;}
			album = getInput("Enter the ALBUM name.");
			if(album == null){return;}
			artistName = getInput("Enter the ARTIST name.");
			if(artistName == null){return;}
			duration = getInput("Enter the track DURATION.");
			if(duration == null){return;}
			// instance of new Song object, using user input.
			song = new Song(trackName, album, artistName, duration);
			
			for(Album a:library.getAlbumList())
			{
				if(a.getAlbumTitle().equalsIgnoreCase(song.getAlbumStr()))
				{
					a.addSong(song);
					updateTable(a);
					return;
				}
			}
			
			Album newAlbum = new Album(album);
			newAlbum.addSong(song);
			library.addAlbum(newAlbum);
			
			updateTable();
			updateList();
			
		}
	}

	private class SearchListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new SearchWindow(library,mainFrame);
		}
	}

	private class ImportLibraryListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try 
			{
				MusicLib l = FileOpener.getLib2();
				if(l.getAlbumList().size()>0)
				{
					updateList(l);
					updateTable(l);
				}
			} 
			catch (IOException e1) 
			{				
				e1.printStackTrace();
			}
		}
	}

	private class DeleteTrackListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
                String tracktodelete;
                tracktodelete = JOptionPane.showInputDialog(null, "Enter track you wish to delete. ");
                for(Song s:library.getAllSongs())
                {
                        if(s.getTitle().equals(tracktodelete))
                        {
                                s.delSong();
                        }
                }
                updateTable();
                hack();
        }

}
	
	private class DeleteAlbumListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
                String albumtodelete;
                albumtodelete = JOptionPane.showInputDialog(null, "Enter album you wish to delete.");
                for(Album a:library.getAlbumList())
                {
                        if(a.getAlbumTitle().equals(albumtodelete))
                        {
                                library.removeAlbum(a);
                                System.out.println("removed");
                                updateList();
                                updateTable();
                                hack();
                                return;
                        }
                }
                
        }
}

	private class ViewAllListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			updateTable();
			updateList();
		}
	}
	private class PrintLibListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			PrintWindow printWindow = new PrintWindow(new MusicTable(mTable));
		}
	}
	private String getInput(String message)
	{
		while(true)
		{
			String toReturn = JOptionPane.showInputDialog(null,message);
			
			return toReturn;
		}
	}
} 



