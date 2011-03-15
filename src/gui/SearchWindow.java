package gui;



import integration.MusicLib;
import integration.SearchList;
import integration.SearchOptions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class SearchWindow extends JFrame
{
	private final MusicLib library;
	private final LibraryMainFrame mainFrame;
	
	private final JCheckBox song = new JCheckBox("Songs");
		private final JCheckBox title = new JCheckBox("Title");
		private final JCheckBox artist = new JCheckBox("Artist");
	private final JCheckBox album = new JCheckBox("Album");
	
	private final JTextField searchBox = new JTextField();
	private final JButton searchButton = new JButton("Search");
	

	public SearchWindow(MusicLib library,LibraryMainFrame mainFrame)
	{
		this.library = library;
		this.mainFrame = mainFrame;
		this.setLayout(null);
		
		this.add(song);
		song.setBounds(5, 5, 100, 20);
		song.addActionListener(new songClick());
		song.setSelected(true);
			this.add(title);
			title.setBounds(25,25,100,20);
			title.setSelected(true);
			this.add(artist);
			artist.setBounds(25,45,100,20);
			artist.setSelected(true);
		this.add(album);
		album.setBounds(5,65,100,20);
		album.setSelected(true);
		
		this.add(searchBox);
		searchBox.setBounds(5,85,100,20);
		searchBox.addActionListener(new Submit());
		this.add(searchButton);
		searchButton.setBounds(5,110,100,20);
		searchButton.addActionListener(new Submit());
		
		this.setVisible(true);	
		this.setResizable(false);
		this.setSize(120,165);
		this.setBounds((mainFrame.getX()+mainFrame.getWidth())/2, (mainFrame.getY()+mainFrame.getHeight())/2, 120, 165);
		this.setTitle("");	
	}
	
	private class songClick implements ActionListener 
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			if(song.isSelected())
			{
				title.setEnabled(true);
				artist.setEnabled(true);
			}
			else
			{
				title.setEnabled(false);
				artist.setEnabled(false);
			}
		}
		
	}
	
	private class Submit implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			SearchList s = search.Search.search(library, searchBox.getText().trim(),new SearchOptions(song.isSelected(),album.isSelected(),title.isSelected(),artist.isSelected()));		
			s.print();
			setVisible(false);
			
			mainFrame.updateList(s);
			mainFrame.updateTable(s);
		}
	}
}
