package integration;

import io.FileOpener;

import java.io.File;
import java.io.IOException;

import gui.LibraryMainFrame;
import gui.Splashscreen;

/**
 * Doesn't really do anything right now.
 * Eventually this will be where everything is started from
 * @author Ty Overby
 */
public class EntryPoint {

	public static void main(String[] args) throws IOException 
	{
		Splashscreen splash = new Splashscreen(2000);
		splash.showSplash();
		
		MusicLib library = FileOpener.getLib1();

		new gui.LibraryMainFrame(library);
	}

}
