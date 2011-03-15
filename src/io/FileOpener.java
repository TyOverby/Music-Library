package io;

import integration.MusicLib;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileOpener {

	/**
	 * Selects a file to read and tests the 
	 * LibraryReader class with a sample main method.
	 * @author James Meyers
	 * @param args
	 * @throws IOException 
	 */
	public static MusicLib getLib1() throws IOException {

		//Gets the input file
		
		boolean exists = (new File(".restore.dat")).exists();
		if (exists) {	
			FileReader fr = new FileReader(new File(".restore.dat"));
			BufferedReader br = new BufferedReader(fr);
			MusicLib lib = new MusicLib();
			try 
			{
			lib =  LibraryReader.read(new File(br.readLine()));
			}
			catch(Exception e)
			{
				
			}
			return lib;
		} 
		else {
			JFileChooser read = new JFileChooser();
			int status = read.showOpenDialog(null);

			if(status == JFileChooser.APPROVE_OPTION)
			{
				File inputFile = read.getSelectedFile();
				MusicLib lib =  LibraryReader.read(inputFile);
				
				FileWriter fw = new FileWriter(new File(".restore.dat"));
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(LibraryReader.fileURI.toString());
				bw.close();
				fw.close();
				
				return lib;
			}
			else
			{
				return new MusicLib();
			}
		}
	}
	public static MusicLib getLib2() throws IOException {

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
