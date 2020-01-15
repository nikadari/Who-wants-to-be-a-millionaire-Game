// Name: claudia & nika
// Date:
// Description:
//--------------------------------------------------------------------------------------------------
import javax.swing.*;
//import MillionaireGUI.Symbol;
import java.applet.Applet;
import javax.swing.*; 
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//--------------------------------------------------------------------------------------------------
public class MillionaireGUI extends JFrame implements ActionListener, WindowListener, MouseListener { 
	//Private variable declarations & Jbutton,Jlabel extensions
	private JScrollPane scrollpanel; 
	private JTextArea questions;    
	private JLabel MoneyWonLabel;
	private JLabel[] answerlbl;
	private JButton[] answerButton;
	private JButton nextQuestionButton;
	private JButton quitButton;

	private final int questionBoxSpace = 5; 					//the number of lines that can fit in the question box.        
	private final int numOfAnswer = 4;
	private String image = "images/";

	private Color backgroundColor = new Color (50,20,20);		//background color of game
	private MillionaireGame mg;									//calls the game Class

	int openScreen = 0;

	static JFrame frame;								// Name for the game board

	//-------------------------------------------AUDIO VARIABLE NAMES----------------------------------------------------------------------------------
	AudioClip drop;
	AudioClip loop;
	AudioClip loser;
	AudioClip winner;;
	// FONT PARAMETERS-----------------------------------------------------------------------------------------------------------------------
	Font Welcome = new Font ("Yu Gothic Light", Font.ITALIC, 60);			// Setting up the Title text parameters  
	Font Title = new Font ("Yu Gothic Light", Font.ITALIC, 80);				// Setting up the Title text parameters  
	//---------------------------------------OPEN SCREEN METHOD/CONSTRUCTOR--------------------------------------------------------
	public MillionaireGUI (MillionaireGame _mg) {
		mg = _mg;
		Container c = getContentPane();        
		c.setLayout (new BorderLayout());

		JPanel pnlCenter = new JPanel();
		pnlCenter.setLayout (new GridLayout (2,1));       
		pnlCenter.add (initLogoArea());
		pnlCenter.add (initAnswerPane());

		c.add (pnlCenter, BorderLayout.CENTER);                
		c.add(initBottomPane(), BorderLayout.SOUTH);

		addWindowListener(this);                				// info for title display
		setTitle ("Who Wants to be a Millionaire"); 
		setLocation (300, 300);
		setSize (410, 440);
		//	setResizable(false);
		//	show(); 
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~drop down menu code~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`
		JMenuItem newOption, exitOption, soundOption;	
		JMenu gameMenu = new JMenu ("Millionaire");							// Set up the Game menu title	

		newOption = new JMenuItem ("New Game");						// Set up the Start New Game title	
		gameMenu.add (newOption);											//Helps create the button for the start new game option
		gameMenu.addSeparator ();
		newOption.setForeground(Color.black);
		newOption.setBackground(Color.yellow);

		exitOption = new JMenuItem ("Exit");								// Set up the Exit title	
		gameMenu.add (exitOption);											//Helps create the button for the exit game option
		gameMenu.addSeparator ();
		exitOption.setForeground(Color.black);
		exitOption.setBackground(Color.pink);

		soundOption = new JMenuItem ("Turn Off sound");						// Set up the Turn Off sound title
		gameMenu.add (soundOption);											//Helps create the button for the turn sound off option
		gameMenu.addSeparator ();
		soundOption.setForeground(Color.black);
		soundOption.setBackground(Color.white);	      

		JMenuBar mainMenu = new JMenuBar (); 
		mainMenu.add (gameMenu);
		frame.setJMenuBar (mainMenu); 	

		newOption.setActionCommand ("New");												//Sets actions to the key words
		newOption.addActionListener (this);

		exitOption.setActionCommand ("Exit");
		exitOption.addActionListener (this);

		soundOption.setActionCommand ("Sound");
		soundOption.addActionListener (this);

		drop.play ();

		gameCode ();
	}//constructor ends
	//-------------------------------------------READING IN SOUND FILE METHOD------------------------------------------------------------------	
	public URL getCompleteURL (String fileName)										//Code to locate audio from files
	{
		try
		{
			return new URL ("file:" + System.getProperty ("user.dir") + "/" + fileName);
		} 
		catch (MalformedURLException e)
		{
			System.err.println (e.getMessage ());
		}
		return null;
	}
	//--------------------------------------------LOGO APPEARANCE METHOD------------------------------------------------------
	private JComponent initLogoArea() {
		JPanel gameLogo = new JPanel();
		ImageIcon logo = new ImageIcon (image + "millionaire.jpg");
		gameLogo.add (new JLabel(logo));
		return gameLogo;
	}
	//--------------------------------------------ANSWER PANEL METHOD----------------------------------------------------------
	private Component initAnswerPane() 	{
		//to do: - read from file "questions"
		JPanel pnlAnswer = new JPanel();
		JPanel pnlCenter = new JPanel();        
		JPanel pnlDown = new JPanel();    
		JPanel pnlUp = new JPanel();

		pnlAnswer.setLayout (new BorderLayout());

		questions = new JTextArea(4,50);
		questions.setFont(new Font ("Courier", Font.PLAIN, 12));
		questions.setWrapStyleWord(true);  
		questions.setForeground (Color.white);    
		questions.setBackground (backgroundColor);          
		questions.setEditable(false);		

		scrollpanel = new JScrollPane(questions);			//adds the feature to make the question "scrollable" if the question is really long

		pnlUp.setBackground (backgroundColor);
		pnlUp.add (scrollpanel);    
		pnlUp.setPreferredSize (new Dimension (410,70));

		MoneyWonLabel = new JLabel ("Money Won: $");                
		pnlDown.add (MoneyWonLabel);   
		pnlDown.setBackground (backgroundColor);
		MoneyWonLabel.setForeground (Color.white);                 

		initanswerButton();
		//	initanswerButton();

		String[] s = {"", "", "", ""};
		setanswerlbl(s);

		String[] btnLabels = {"A","B","C","D"};

		for (int i = 0; i < numOfAnswer; i++) 
		{
			answerButton[i].setPreferredSize (new Dimension (50,30));

			pnlCenter.add (answerButton[i]);                     
			answerlbl[i].setPreferredSize (new Dimension (135, 30));

			pnlCenter.add (answerlbl[i]);              
			answerButton[i].setBackground (backgroundColor);  
			answerButton[i].setForeground (Color.white);
			answerlbl[i].setForeground (backgroundColor);  
		}
		enableBtnAnswer(false);   
		pnlAnswer.add (pnlUp, BorderLayout.NORTH);     
		pnlAnswer.add (pnlCenter ,BorderLayout.CENTER);       
		pnlAnswer.add (pnlDown, BorderLayout.SOUTH);

		return pnlAnswer;
	}
	//--------------------------------------------------------------------------------------------------
	private JComponent initBottomPane () {
		JPanel pnlBottom = new JPanel();
		pnlBottom.setLayout (new GridLayout (1,2));

		Icon question = new ImageIcon (image +"question.jpg");

		nextQuestionButton = new JButton ("Next Question: ", question);
		nextQuestionButton.addActionListener(this);

		Icon stop = new ImageIcon (image+"stop.jpg");

		quitButton = new JButton ("Quit: ", stop);
		quitButton.addActionListener(this);       

		pnlBottom.add (nextQuestionButton);         
		pnlBottom.add (quitButton);

		return pnlBottom; 
	}
	//--------------------------------------------------------------------------------------------------
	private void enableBtnAnswer(boolean b) {
		answerlbl = new JLabel[numOfAnswer]; 

		for (int i = 0; i < numOfAnswer; i++)
		{
			answerlbl[i] = new JLabel();
		} 
	}
	//--------------------------------------------------------------------------------------------------
	private void setanswerlbl(String[] s) {
		String [] answers = {"sdf", "sdfds"};
		//HashMap <String, MillionaireGUI> answers = new HashMap <String, MillionaireGUI>();

		if (answers.length < numOfAnswer) 
			return;
		for (int i = 0; i < numOfAnswer; i++)
		{
			answerlbl[i].setText(answers[i]);
		}      
	}
	//--------------------------------------------------------------------------------------------------
	private void initanswerButton() {
		String[] btnLabels = {"A","B","C","D"};
		answerButton = new JButton[numOfAnswer];

		for (int i = 0; i < numOfAnswer; i++) 
		{
			answerButton[i] = new JButton(btnLabels[i]);
			answerButton[i].addActionListener(this);
		}
	}
	//----------------------------------------WELCOME SCREEN-----------------------------------------------------------
	public void paintComponent(Graphics g) 
	// DESCRIPTION: Method which draws the grid
	// PARAPETERS:	None 
	// RETURN: Void
	{  																				// Invoke via repaint()
		super.paintComponents(g);  
		if (openScreen == 0)  
		{
			g.setColor(Color.PINK);									// Writes out the opening screen title
			g.setFont(Welcome);
			String Welcome = "WELCOME TO";
			g.drawString(Welcome, 0, 80);
		}	
		else if (openScreen ==1) 
		{																	// Fill background
			setBackground (new Color (80,30,87)); 							// Set its background color
			g.setColor(Color.YELLOW);	
		}
	}
	//--------------------------------------------------------------------------------------------------
	public void gameCode() {

	}
	//--------------------------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent e) {

		String eventName = e.getActionCommand ();									
		System.out.println (eventName);														// if statement to game screen when the 'start new game' button is pressed

		if (eventName.equals ("New"))
		{
			openScreen = 1;
			repaint(); 
			gameCode ();
		}
		else if (eventName.equals ("Exit"))													// if statement to quit program when the 'exit' button is clicked
		{
			System.exit (0);
		}

		if (e.getSource() == nextQuestionButton) 
		{
			nextQuestionButton.setEnabled(false);
			enableBtnAnswer(true);	    
			mg.nextQuestion();	 
			questions.setText(mg.getQuestion());          

			//Creates an array of Strings used to set the label text            
			String[] answers = new String[numOfAnswer];

			for (int i = 0; i < numOfAnswer; i++) 
			{
				answers[i] = mg.getTextForAnswer(answerButton[i].getText());
			}
			setanswerlbl (answers);                                                 
		} 

		if (e.getSource() == quitButton)  					//Quit button pressed
		{
			System.exit(0);
		}

		for (int i = 0; i < numOfAnswer; i++) 				 //loop if one of the answer buttons was pressed
		{
			if (e.getSource() == answerButton[i]) 
			{
				nextQuestionButton.setEnabled(true);
				boolean correct = mg.isCorrectAnswer (answerButton[i].getText());

				if (correct) 								//Displays message if the player got the answer wrong
				{
					if (mg.hasWon()) 
					{
						MoneyWonLabel.setText("You won 1 million dollars!!!");
						nextQuestionButton.setEnabled(false);
					}
					else
					{
						MoneyWonLabel.setText("Money Won: " + mg.getMoneyWonLabel());
					}
				}
				else 										 //Displays message if the player got the answer wrong
				{
					MoneyWonLabel.setText("You lose");
					nextQuestionButton.setEnabled(false);
				}                      
				enableBtnAnswer(false);                
			}
		}    
	}
	//--------------------------------Because this class is implementing WindowListener-------------------------------------------------------
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Don't have to put anything in these methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void windowActivated(WindowEvent event) {
	}

	public void windowClosed(WindowEvent event) {
	}

	public void windowClosing(WindowEvent event) {
		System.exit(0);  
	}

	public void windowDeactivated(WindowEvent event) {
	}

	public void windowDeiconified(WindowEvent event) {
	}

	public void windowIconified(WindowEvent event) {
	}

	public void windowOpened(WindowEvent event) {
	}

	//--------------------------------------------------MAIN PROGRAM--------------------------------------------------------
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		new MillionaireGUI(new MillionaireGame());
		frame = new JFrame ("Millionaire");
		//MillionaireGUI myPanel = new MillionaireGUI (); 
		//frame.add (myPanel);
		//frame.pack ();
		//frame.setVisible (true);
	}
	//--------------------------------------------------=-QUESTIONS-----------------------------------------------------------
	static ArrayList <Questions> listQuestions = new ArrayList <Questions> ();
	public void readInQuestions (String filename){	
		try{
			BufferedReader inFile = new BufferedReader (new FileReader (filename+".txt"));
			for (int i = 0 ; i < 15 ; i++)
			{
				String question = inFile.readLine(); 

				String a = inFile.readLine();

				String b = inFile.readLine();

				String c = inFile.readLine();

				String d = inFile.readLine();

				String correct = inFile.readLine();
				Questions q1 = new Questions (question,a,b,c,d,correct);
				listQuestions.add(q1);
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch (IOException e)
		{
			System.out.println("Reading error");
		}
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}


