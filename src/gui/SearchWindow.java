package gui;



import integration.MusicLib;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class SearchWindow extends JFrame
{

	private JPanel headerPanel = new JPanel();
	private int text_field = 40;
	private JPanel textPanel = new JPanel();
	private JPanel textboxPanel = new JPanel();
	private JTextField searchField = new JTextField(text_field);
	private ImageIcon logo = new ImageIcon("C:\\Users\\Stephen\\Documents\\Java workspace\\MusicLibrary\\Programming\\logo.gif");
	private JLabel tccLabel = new JLabel(logo);
	private JLabel searchOption = new JLabel("Choose a search criteria:");
	private JPanel radioButtonPanel = new JPanel();
	private JButton searchbutton = new JButton("Search Library");
	private JMenuBar menubar;
	private ButtonGroup radioButtonGroup;
	String output;
	int i= 1;
	JRadioButton albumButton = new JRadioButton("album");
	JRadioButton songButton = new JRadioButton("song");
	JRadioButton artistButton = new JRadioButton("artist");



	private final int WINDOW_WIDTH = 600;
	private final int WINDOW_HEIGHT = 300;

	public SearchWindow(MusicLib library) {



		this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		this.setTitle("TCC Music Library Version 1.0");

		headerPanel.add(tccLabel);
		textPanel.add(searchOption);
		textPanel.add(radioButtonPanel);
		textboxPanel.add(searchField);
		textboxPanel.add(searchbutton);


		JSplitPane searchPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,textPanel,textboxPanel);
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,headerPanel,searchPanel);

		searchbutton.addActionListener(new searchlbr());


		add(sp);
		tccLabel.setBorder(BorderFactory.createLineBorder(Color.black,1));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); //Sets the bounds of the program window, centering it
		int x = (screen.width-900)/2;
		int y = (screen.height-600)/2;
		setBounds(x,y,WINDOW_WIDTH,WINDOW_HEIGHT);
		setVisible(true);
		buildPanel();
		buildMenuBar();

	}
	public void buildMenuBar(){
		menubar = new JMenuBar();


		setJMenuBar(menubar);
	}
	public void buildPanel(){


		radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(albumButton);
		radioButtonGroup.add(songButton);
		radioButtonGroup.add(artistButton);

		radioButtonPanel.add(albumButton);
		radioButtonPanel.add(artistButton);
		radioButtonPanel.add(songButton);
	}



	public class RadioButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent f){
			String input;
			input = searchField.getText();

			if (f.getSource()== albumButton){
				i = 0;
			}
			else if (f.getSource()== artistButton){
				i = 1;
			}
			else if (f.getSource()== songButton){
				i= 2;
			}
		}
	}
	public class searchlbr implements ActionListener{
		public void actionPerformed(ActionEvent e){	
			output = "You are searching by "; 
			output += (i<1 ? "album": "");
			output += (i==1 ? "artist": "");
			output += (i>1 ? "song":"");
			output += ".";
			JOptionPane.showMessageDialog(null,output);
			//exit
			System.exit(0);



		}
	}
}