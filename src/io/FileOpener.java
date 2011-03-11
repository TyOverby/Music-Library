package io;

import integration.MusicLib;

import java.io.*;
import javax.swing.JFileChooser;

public class FileOpener {

	/**
	 * Selects a file to read and tests the 
	 * LibraryReader class with a sample main method.
	 * @author James Meyers
	 * @param args
	 * @throws IOException 
	 */
	public static MusicLib getLib() throws IOException {
		
		//Gets the input file
		JFileChooser read = new JFileChooser();
		int status = read.showOpenDialog(null);
		
		if(status == JFileChooser.APPROVE_OPTION)
		{
			File inputFile = read.getSelectedFile();
			MusicLib lib =  LibraryReader.read(inputFile);
			return lib;
		}
		else
		{
			return new MusicLib();
		}
	}

}
