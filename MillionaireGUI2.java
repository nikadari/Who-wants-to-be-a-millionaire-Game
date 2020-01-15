// Name: Claudia & Nika
// Date: June 2, 2018
// Description: Who wants to be a millionaire - user has to answer a series of trivia questions in order to obtain one million dollars at the end.
//-------------------------------------------------------------------------------------------------------------------------
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import java.applet.Applet;
import java.applet.AudioClip;
//import OpenScreen.Action1;
//import OpenScreen.Action2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//------------------------------------------------------------------------------------------------------------------------
public class MillionaireGUI2 extends JPanel implements ActionListener, MouseListener{
	//---------------------------------------GLOBAL VARIABLE DECLERATION & ARRAY LISTS-----------------------------------------
	private final Font CALIBRI_30 = new Font ("Calibri", Font.PLAIN, 50);
	private final Font CALIBRI_20 = new Font ("Calibri", Font.PLAIN, 20);
	private final Font CALIBRI_15 = new Font ("Calibri", Font.PLAIN, 15);
	private final Font ARIAL_20 = new Font ("Arial", Font.BOLD, 20);

	static JFrame frame;			// Name for the game board
	static JPanel myPanel;

	private Image titlePic; 		// pictures
	private Image losePic2; 
	private Image titlePic3; 
	private Image backgroundPic;
	private Image money; 
	//private Image cry;

	JButton button, button2;						//buttons + icons for JOptionPane
	ImageIcon icon = new ImageIcon("phone.png");
	ImageIcon icon2 = new ImageIcon("audience.png");

	AudioClip higherQuestions; 						//sound files
	AudioClip mouseClick;
	AudioClip phoneRinging;
	AudioClip win;
	AudioClip lose;

	String choice = "pc";			//keeps track of which arraylist is being used (pc = pop culture / at = arts / hs = history&science)
	int x,y;						//keeps track of x and y coordinate of the mouse clicks
	int screen = 0; 				//screen = 0 (main menu) screen = 1 (game menu)  screen = 2 (win screen) screen = 3 (lose screen)
	int counter = 0;				//keeps track of arrayList index (which question is being asked and displayed)
	int bubbleY = 575; 				//global variable for y of the orange bubble moving up on the money bar

	boolean display = false;		//boolean for the extra features (life lines)
	boolean callHelp = true;		
	boolean audienceHelp = true;	
	boolean fiftyFiftyHelp = true;

	ArrayList <Questions> listPop = new ArrayList <Questions> ();			//array lists to store the questions and answers
	ArrayList <Questions> listArts = new ArrayList <Questions> ();
	ArrayList <Questions> listHist = new ArrayList <Questions> ();
	ArrayList <Questions> newList ;

	String option = "";									//keeps track of user's choice
	MoneyBoard score = new MoneyBoard();
	
//------------------------------------------------------------------------------------------------------------------------

	//---------------------------------------------------CONSTRUCTOR------------------------------------------------------
	// DESCRIPTION: Constructor - Initializes graphics 
	// PARAPETERS: None
	// RETURN: N/A
	public MillionaireGUI2 () {
		readInQuestions();
		
		//Set frame size and read in background images:
		setPreferredSize(new Dimension (800, 597));					
		setBackground (new Color(0,6,84));
		setFont (CALIBRI_30); 
		titlePic = Toolkit.getDefaultToolkit().getImage("millionaire.jpg");	
		backgroundPic = Toolkit.getDefaultToolkit().getImage("background.jpg");

		setLayout (null);

		//Set graphics settings (background colour and text) for JOptionPane:
		UIManager UI=new UIManager();
		UI.put("OptionPane.background", new ColorUIResource(0,6,84));
		UI.put("Panel.background", new ColorUIResource(0,6,84));
		UI.put("OptionPane.messageForeground", Color.WHITE);

		//Change and set cursor image:
		Image image = Toolkit.getDefaultToolkit().getImage("cursor.png");
		Point hotspot = new Point (0, 0);
		Toolkit toolkit = Toolkit.getDefaultToolkit ();
		Cursor cursor = toolkit.createCustomCursor (image, hotspot, "pointer");
		frame.setCursor (cursor);

		//Set audio variables with the audio files:
		higherQuestions = Applet.newAudioClip(getCompleteURL ("higherQuestions.wav"));			
		mouseClick = Applet.newAudioClip(getCompleteURL ("mouseClick.wav"));
		win = Applet.newAudioClip(getCompleteURL ("win.wav"));
		lose =  Applet.newAudioClip(getCompleteURL ("lose.wav"));
		phoneRinging = Applet.newAudioClip(getCompleteURL ("phoneRinging.wav"));

		//Set PLAY button (background colour, text colour) for game screen:
		button = new JButton("PLAY!");
		button.setFont(new Font("Calibri", Font.BOLD, 18));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color (0,6,84));
		button.setPreferredSize(new Dimension(190, 30));
		button.setBounds(5,475,140,60);							
		button.addActionListener (this);
		button.setOpaque(true);
		button.setBorderPainted(false);		
		
		//Set HOW TO PLAY button (background colour, text colour) for game screen:
		button2 = new JButton("HOW TO PLAY");
		button2.setFont(new Font("Calibri", Font.BOLD, 18));
		button2.setForeground(Color.WHITE);							//button text color
		button2.setPreferredSize(new Dimension(190, 30));			//button size
		button2.setBounds(650,470,140,60);	 						//button location
		button2.addActionListener (this);
		button2.setBackground(new Color (0,6,84));					//button color
		button2.setOpaque(true);
		button2.setBorderPainted(false);

		//~~~~~~~~~~~~~~~~DROP DOWN MENU BAR SET UP~~~~~~~~~~~~~~~~~~~~~
		//Game Menu
		JMenuItem newOption, exitOption, soundOption, musicOn, musicOff;
		JMenu gameMenu = new JMenu ("Game");								// Set up the Game menu title	

		newOption = new JMenuItem ("New");									// Set up the Start New Game title	
		gameMenu.add (newOption);											//Helps create the button for the start new game option
		gameMenu.addSeparator ();

		exitOption = new JMenuItem ("Exit");								// Set up the Exit title	
		gameMenu.add (exitOption);											//Helps create the button for the exit game option
		gameMenu.addSeparator();

		soundOption = new JMenu ("Music");									// Set up the Music Mood title
		gameMenu.add (soundOption);	

		musicOn = new JMenuItem ("Music ON");								// Set up the music on title
		soundOption.add (musicOn);

		musicOff = new JMenuItem ("Music OFF");								// Set up the music off title
		soundOption.add (musicOff);
		soundOption.setForeground(Color.black);
		soundOption.setBackground(Color.white);	  

		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~Categories for the Menu~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		JMenuItem popOption, artsOption, historyOption;
		JMenu categoryMenu = new JMenu ("Trivia Categories");

		popOption = new JMenuItem ("Pop Culture");
		categoryMenu.add(popOption);
		categoryMenu.addSeparator ();
		popOption.setBackground(new Color(255, 127, 80)); 

		artsOption = new JMenuItem ("Arts");
		categoryMenu.add(artsOption);
		categoryMenu.addSeparator();
		artsOption.setBackground(new Color (244,194,194));

		historyOption = new JMenuItem ("History & Science");
		categoryMenu.add(historyOption);
		historyOption.setBackground(new Color (152, 251, 152));
		//-------------------------------------------------------------
		JMenuBar mainMenu = new JMenuBar (); 
		mainMenu.add (gameMenu);
		mainMenu.add(categoryMenu);
		frame.setJMenuBar (mainMenu); 	
		//-------------------------------------------------------------
		newOption.setActionCommand ("New");												//Sets actions to the key words
		newOption.addActionListener (this);
		exitOption.setActionCommand ("Exit");
		exitOption.addActionListener (this);
		musicOn.setActionCommand ("Music ON");
		musicOn.addActionListener (this);
		musicOff.setActionCommand ("Music OFF");
		musicOff.addActionListener (this);

		popOption.setActionCommand ("Pop Culture");
		popOption.addActionListener(this);
		artsOption.setActionCommand ("Arts");
		artsOption.addActionListener(this);
		historyOption.setActionCommand ("History & Science");
		historyOption.addActionListener(this);

		higherQuestions.loop();  
		addMouseListener(this);

	}//constructor ends
	
	//----------------------------------------------------LOCATE AUDIO FROM FILE----------------------------------------------------------------
	//Description: Finds music files from computer
	//Parameters: Name of the file it should be looking for.
	//Return: does not return anything (null) 
	public URL getCompleteURL (String fileName)										
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
	//----------------------------------------------------PAINT COMPONENT-------------------------------------------------	
	//Description:Paint component - draws graphics on jpanel
	//Parameters: Takes in graphics object, used to draw
	//Return: void
	public void paintComponent (Graphics g){
		super.paintComponents(g);
		
		//Start Screen
		if (screen == 0)
		{
			g.drawImage(titlePic, -180, 0, this);						
			myPanel.add (button);
			myPanel.add (button2);
		}
		
		//Playing Screen
		if (screen == 1)
		{
			g.drawImage(backgroundPic, 0, 0, this);						
			g.setColor(new Color (0,6,84));
			g.fillRect(600, 0,800,597);
			g.setFont(ARIAL_20);
			g.setColor(Color.YELLOW);
			String q1 = "1     $100";
			g.drawString(q1,  650,  590);
			String q2 = "2     $200";
			g.drawString(q2,  650,  550);
			String q3 = "3     $300";
			g.drawString(q3,  650,  510);
			String q4 = "4     $500";
			g.drawString(q4,  650,  470);
			g.setColor(Color.white);
			String q5 = "5     $1,000";
			g.drawString(q5,  650,  430); 
			g.setColor(Color.YELLOW);
			String q6 = "6     $2,000";
			g.drawString(q6,  650,  390);
			String q7 = "7     $4,000";
			g.drawString(q7,  650,  350);
			String q8 = "8     $8,000";
			g.drawString(q8,  650,  310);
			String q9 = "9     $16,000";
			g.drawString(q9,  650,  270);
			g.setColor(Color.white);
			String q10 = "10   $32,000";					
			g.drawString(q10,  650,  230);
			g.setColor(Color.YELLOW);
			String q11 = "11   $64,000";
			g.drawString(q11,  650,  190);
			String q12 = "12   $125,000"; 
			g.drawString(q12,  650,  150);
			String q13 = "13   $250,000";
			g.drawString(q13,  650,  110);
			String q14 = "14   $500,000";
			g.drawString(q14,  650,  70);
			g.setColor(Color.WHITE);
			String q15 = "15   1 MILLION";
			g.drawString(q15,  650,  30);	
		}

		//Playing Screen - Question/Answer Display
		if (screen == 1 && display == true)
		{
			g.setColor(Color.orange);
			g.fillOval (620, bubbleY, 10, 10);
			g.setColor(Color.WHITE);
			//Checks string length and determines which font size to use:
			if (newList.get(counter).getQuestion().length() > 60)
				g.setFont(CALIBRI_15);
			else
				g.setFont(CALIBRI_20);
			
			g.drawString (newList.get(counter).getQuestion(), 70, 190);
			if (newList.get(counter).getOptionA().length() > 22 || newList.get(counter).getOptionB().length() > 22 || 
					newList.get(counter).getOptionC().length() > 22 || newList.get(counter).getOptionD().length() > 22)
				g.setFont(CALIBRI_15);
			else
				g.setFont(CALIBRI_20);
			g.drawString(newList.get(counter).getOptionA().substring(3), 80, 440);
			g.drawString(newList.get(counter).getOptionB().substring(3), 345, 440);
			g.drawString(newList.get(counter).getOptionC().substring(3), 80, 530);
			g.drawString(newList.get(counter).getOptionD().substring(3), 345, 530);
		}
		
		//Win and Ending Screen
		if (screen == 2) 		
		{
			higherQuestions.stop();
			//win.play();						//Glitch - will not play if gifs are present
			setBackground (new Color(0,6,84));
			setFont (CALIBRI_30); 
			titlePic3 = Toolkit.getDefaultToolkit().getImage("winner.jpg");
			g.drawImage(titlePic3, 0, 0, this);

			//Gifs
			money= Toolkit.getDefaultToolkit().getImage("money.gif");	
			g.drawImage(money, 650, 10, this);								
			g.drawImage(money, 20, 10, this);	
			g.drawImage(money, 650, 150, this);								
			g.drawImage(money, 20, 150, this);	
			g.drawImage(money, 650, 290, this);								
			g.drawImage(money, 20, 290, this);	
		}
		
		//Loser Screen
		if (screen == 3)
		{											//lose screen
			higherQuestions.stop();
			lose.play();
			setBackground (new Color(0,6,84));
			setFont (CALIBRI_30); 
			losePic2 = Toolkit.getDefaultToolkit().getImage("loser.jpg");		
			g.drawImage(losePic2, 0, 0, this);

			//cry= Toolkit.getDefaultToolkit().getImage("cry.gif");			//Glitch - sound effect will glitch if gif is present		
			//g.drawImage(cry, 550, 10, this);	

			//Checks for low score:
			if (counter < 5)
				score.resetScore();

			g.setColor(Color.WHITE);
			g.setFont(ARIAL_20);
			int mid = (int)((this.getWidth() - this.getFontMetrics(ARIAL_20).getStringBounds("Thanks for playing!", g).getWidth())/2);
			g.drawString("Thanks for playing!", mid, 380);
			int mid2 = (int)((this.getWidth() - this.getFontMetrics(ARIAL_20).getStringBounds("You walk away with", g).getWidth())/2);
			g.drawString("You walk away with", mid2, 410); 			
			g.setFont(CALIBRI_30);
			int mid3 = (int)((this.getWidth() - this.getFontMetrics(CALIBRI_30).getStringBounds("$"+score.getScore(), g).getWidth())/2);
			g.drawString("$"+score.getScore(), mid3, 460); 			
		}
	
	}
	//-------------------------------------------------------READS IN QUESTIONS -------------------------------------------------	
	// DESCRIPTION: Reads in questions from 3 text files. Converts each question into a Questions object and is collected inside ArrayList 
	// PARAPETERS:	None
	// RETURN: Void
	public void readInQuestions (){
		String filename = "popculture";
		for (int j = 1 ; j <= 3 ; j++){

			if (j == 2)
				filename = "arts";
			else if (j == 3)
				filename = "historyscience";

			try{
				BufferedReader inFile = new BufferedReader (new FileReader (filename+".txt"));
				for (int i = 0 ; i < 15 ; i++){
					String question = inFile.readLine();
					String a = inFile.readLine();
					String b = inFile.readLine();
					String c = inFile.readLine();
					String d = inFile.readLine();
					String correct = inFile.readLine();
					Questions q1 = new Questions (question,a,b,c,d,correct);
					if (j == 1)
						listPop.add(q1);
					else if (j == 2)
						listArts.add(q1);
					else if (j == 3)
						listHist.add(q1);
				}
			}
			catch (FileNotFoundException e){
				System.out.println("File not found");
			}
			catch (IOException e){
				System.out.println("Reading error");
			}
		}
	}
	
	//-----------------------------------------------------CALL FRIEND OPTION-------------------------------------------------	
	// DESCRIPTION: Lifeline Option - calls Ms Wong for help in answering the question
	// PARAPETERS:	None
	// RETURN: Void
	public void callAFriend (){
		phoneRinging.play();
		String message = "MS WONG: Hello?!?!\nYOU: Miss I need help with this question!\nMS WONG: Typical...I think the answer is " + newList.get(counter).getCorrect();
		JOptionPane.showMessageDialog (myPanel, message, "Call a Friend",JOptionPane.INFORMATION_MESSAGE, icon);
		callHelp = false;
	}
	
	//-----------------------------------------------------ASK AUDIENCE OPTION-------------------------------------------------	
	// DESCRIPTION: Lifeline Option - generates random statistics based of audience's opinions
	// PARAPETERS:	None
	// RETURN: Void
	public void askAudience (){
		double [] statList = new double [4];
		int t = 0;
		
		//Create random numbers from 1 to 100, calculate sum of all 4 numbers (t):
		for (int i = 0 ; i < 4 ; i++){
			int randNum = (int)(Math.random()*(100-1+1)+1);
			statList[i] = randNum;
			t += randNum;
		}
		//Going through list and dividing by total (t) and multiplying by 100 - gives us 4 percentages that add up to 100:
		for (int i = 0 ; i < 4 ; i++){
			double temp = (statList[i]/t) * 100;
			statList[i] = (int) Math.round(temp * 10)/10.0;
		}
		Arrays.sort(statList);
		//Checks answer and assigns percentage based on correct answer:
		String stats = "Pol Results:\n";
		if (newList.get(counter).getCorrect().equals(newList.get(counter).getOptionA())){
			stats += statList[3]+"% say "+newList.get(counter).getOptionA()+"\n" + statList[2]+"% say "+newList.get(counter).getOptionB()+"\n"
					+ statList[1]+"% say "+newList.get(counter).getOptionC()+"\n" + statList[0]+"% say "+newList.get(counter).getOptionD();
		}
		if (newList.get(counter).getCorrect().equals(newList.get(counter).getOptionB())){
			stats += statList[3]+"% say "+newList.get(counter).getOptionB()+"\n" + statList[2]+"% say "+newList.get(counter).getOptionA()+"\n"
					+ statList[1]+"% say "+newList.get(counter).getOptionC()+"\n" + statList[0]+"% say "+newList.get(counter).getOptionD();
		}
		if (newList.get(counter).getCorrect().equals(newList.get(counter).getOptionC())){
			stats += statList[3]+"% say "+newList.get(counter).getOptionC()+"\n" + statList[2]+"% say "+newList.get(counter).getOptionA()+"\n"
					+ statList[1]+"% say "+newList.get(counter).getOptionB()+"\n" + statList[0]+"% say "+newList.get(counter).getOptionD();
		}
		if (newList.get(counter).getCorrect().equals(newList.get(counter).getOptionD())){
			stats += statList[3]+"% say "+newList.get(counter).getOptionD()+"\n" + statList[2]+"% say "+newList.get(counter).getOptionA()+"\n"
					+ statList[1]+"% say "+newList.get(counter).getOptionB()+"\n" + statList[0]+"% say "+newList.get(counter).getOptionC();
		}		
		
		JOptionPane.showMessageDialog (myPanel, stats, "Ask Audience",JOptionPane.INFORMATION_MESSAGE, icon2);
		audienceHelp = false;
	}
	
	//-----------------------------------------------------50/50 OPTION-------------------------------------------------	
	// DESCRIPTION: Lifeline Option - Removes two incorrect options
	// PARAPETERS:	None
	// RETURN: Void	
	public void fiftyFifty() {
		Graphics g = getGraphics();
		//If correct option is C or D, then option A and B will be covered/removed:
		if (newList.get(counter).getCorrect().equals(newList.get(counter).getOptionC()) || newList.get(counter).getCorrect().equals(newList.get(counter).getOptionD()))     //idk how to "import" the options :|
		{
			g.setColor(Color.BLACK);				
			g.fillRect(70, 407, 190, 60);
			g.fillRect(335, 407, 190, 60);
		}
		//If correct option is A or B, then option C and D will be covered/removed:
		if (newList.get(counter).getCorrect().equals(newList.get(counter).getOptionA()) || newList.get(counter).getCorrect().equals(newList.get(counter).getOptionB()))     //idk how to "import" the options :|
		{
			g.setColor(Color.BLACK);				
			g.fillRect(70, 492, 190, 60);
			g.fillRect(335, 492, 190, 60);
		}

		fiftyFiftyHelp = false;
	}
	
	//-------------------------------------------------------CHECK ANSWER-------------------------------------------------	
	// DESCRIPTION: Checks user's answer and determines whether user is right or wrong
	// PARAPETERS:	None
	// RETURN: Void
	public void checkAnswer (){
		score.setScore(counter);
		//If true = continue game, onto the next question
		if (option.equalsIgnoreCase(newList.get(counter).getCorrect()) == true){
			if (counter == 14){
				screen = 2;
				System.out.println("score="+score);
				display = false;
				repaint();
			}
			counter++;
			bubbleY -= 40;
			repaint();
		}
		//If false = lost game
		else {
			screen = 3; 
			display = false;
			repaint();
		}
	}

	//------------------------------------------------------NEW GAME METHOD-------------------------------------------------	
	// DESCRIPTION: Called at beginning of game to set up newList, which holds the value of the category questions 
	// PARAPETERS:	None
	// RETURN: Void
	public void startGame (){
		//System.out.println("in");
		if (choice.equalsIgnoreCase("at")){
			newList = listArts;
		}
		else if (choice.equalsIgnoreCase("hs")){
			newList = listHist; 
		}
		else {
			newList = listPop;
		}

		display = true;
		repaint();
	}
	
	//--------------------------------------------NEW GAME METHOD PT2 (FOR THE SCREENS)--------------------------------	
	//DESCRIPTION: This method resets key variables and restarts the game. 
	//PARAMETRES: None
	//RETURN: void.
	public void newGame (){
		screen = 0; 
		counter = 0;
		score.resetScore();
		bubbleY = 575;
		display = false;
		callHelp = true;
		audienceHelp = true;
		fiftyFiftyHelp = true;
		higherQuestions.loop();
	}
	//-------------------------------------------------------MOUSE CLICKED-------------------------------------------------	
	// DESCRIPTION: Checks x and y locations for each screen, determines the outcome of where the user has clicked (abcd options, life lines)
	// PARAPETERS:	MouseEvent objects store information about the mouse when it is clicked.
	// RETURN: Void
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		mouseClick.play();
		System.out.println("x = "+x + " y = " +y + "counter = "+counter);

		//Lifeline - Call a Friend
		if ((screen == 1 && callHelp == true) && (x >= 268 && x <= 320) && (y >= 353 && y <= 390)){
			callAFriend();
		}
		//Lifeline - Ask the Audience
		if ((screen == 1) && (audienceHelp == true) && (x >= 353 && x <= 400) && (y >= 353 && y <= 390)){
			askAudience();
		}
		//Lifeline - FiftyFifty Options
		if ((screen == 1) && (fiftyFiftyHelp == true) && (x >= 187 && x <= 234) && (y >= 353 && y <= 390)){
			fiftyFifty();
		}
		
		//Determines and Checks Answers
		if ((screen == 1) && (display == true)){ 
			if ((x >= 64 && x <= 264) && (y >= 407 && y <= 474)){
				option = newList.get(counter).getOptionA();
				checkAnswer();
			}
			else if ((x >= 329 && x <= 530) && (y >= 407 && y <= 474)){
				option = newList.get(counter).getOptionB();
				checkAnswer();
			}
			else if ((x >= 64 && x <= 264) && (y >= 495 && y <= 560)){
				option = newList.get(counter).getOptionC();
				checkAnswer();
			}
			else if ((x >= 329 && x <= 530) && (y >= 495 && y <= 560)){
				option = newList.get(counter).getOptionD();
				checkAnswer();
			}
		}
	}
	//-------------------------------------------------------AUTO GENERATED METHODS-------------------------------------------------	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub	
	}


	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub	
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	//-------------------------------------------------------ACTION PERFORMED----------------------------------------------	
	//Description: This method will change the screen depending on which Menu button pressed. Can create New Game, Exit Game or Change Categories
	//Parameters: ActionEvent objects store information about the action that is chosen by the user (menu bar).
	//Return: void.
	public void actionPerformed(ActionEvent e) {
		String eventName = e.getActionCommand();
		if (eventName.equals("Exit")){
			System.exit(0);
		}
		if (eventName.equals("New")){
			choice = "pc";
			newGame();
			repaint();
		}		
		if (eventName.equals("Music OFF")){
			higherQuestions.stop();
		}
		if (eventName.equals("Music ON")){
			higherQuestions.loop();
		}
		
		if (eventName.equals("Pop Culture")){
			if (screen == 1){
				int reply = JOptionPane.showConfirmDialog(frame,"Restart Game?", "Restart",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					choice = "pc";
					newGame();
					repaint();
				}
			}
			else{
				choice = "pc";
				newGame();
				repaint();
			}
				
		}
		if (eventName.equals("Arts")){
			if (screen == 1){
				int reply = JOptionPane.showConfirmDialog(frame,"Restart Game?", "Restart",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					choice = "at";
					newGame();
					repaint();
				}
			}
			else{
				choice = "at";
				newGame();
				repaint();
			}
		}
		if (eventName.equals("History & Science")){
			if (screen == 1){
				int reply = JOptionPane.showConfirmDialog(frame,"Restart Game?", "Restart",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (reply == JOptionPane.YES_OPTION) {
					choice = "hs";
					newGame();
					repaint();
				}
			}
			else{
				choice = "hs";
				newGame();
				repaint();
			}
		}
		if (eventName.equals ("PLAY!")) {
			screen = 1;
			myPanel.remove(button);
			myPanel.remove(button2);
			startGame();
			repaint();
		}
		if (eventName.equals("HOW TO PLAY")){

			String HowToPlay11 = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~HOW TO PLAY~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\nAnswer trivia questions from the three categories: "
					+ "Pop Culture, Arts, History & Science.\n             The player will earn money every time "
					+ "a question is correctly answered.\n                     If a question is answred incorrectly, the "
					+ "player loses the game.\n                             Player will either leave the game with $0 or amount "
					+ "earned, \n                                   depending on how many questions were answred.\n                         Player can get help "
					+ " by calling a friend or asking the audience.\n                          The game is over when the user "
					+ "reaches the $1 Million mark.";
			
			
			JOptionPane.showMessageDialog (myPanel, HowToPlay11, "How to Play",JOptionPane.PLAIN_MESSAGE);
			repaint();
		}

	}
	//------------------------------------------------------!!MAIN PROGRAM!!-------------------------------------------------	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		frame = new JFrame ("Who Wants To Be A Millionaire?");
		myPanel = new MillionaireGUI2 (); 
		frame.add (myPanel);
		frame.pack ();
		frame.setVisible (true);
	}

}
