package integration;

import io.FileOpener;

import java.io.File;
import java.io.IOException;

import gui.LibraryMainFrame;

/**
 * Doesn't really do anything right now.
 * Eventually this will be where everything is started from
 * @author Ty Overby
 */
public class EntryPoint {

	public static void main(String[] args) throws IOException 
	{
		MusicLib library = FileOpener.getLib();

		new gui.LibraryMainFrame(library);

				
	}

}
