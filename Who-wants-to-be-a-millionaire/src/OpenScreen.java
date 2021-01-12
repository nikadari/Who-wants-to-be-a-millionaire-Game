// Name: Claudia & Nika
// Date: June 2, 2018
// Description: Who wants to be a millionaire - user has to answer a series of trivia questions in order to obtain one million dollars at the end.
//--------------------------------------------------------------------------------------------------------------------
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
//-----------------------------------------------------------------------------------------------------
public class OpenScreen extends JPanel implements ActionListener, MouseListener {
	private final Font CALIBRI_30 = new Font ("Calibri", Font.PLAIN, 50);
	static JFrame frame;											// Name for the game board
	static JPanel myPanel;
	private Image titlePic; 
	//------------------------------------------------CONSTRUCTOR---------------------------------------------------------------
	public OpenScreen () {		
		// DESCRIPTION:  
		// PARAPETERS:	 
		// RETURN:  
		setPreferredSize(new Dimension (800, 597));					//window size
		setBackground (new Color(0,6,84));
		setFont (CALIBRI_30); 
		titlePic = Toolkit.getDefaultToolkit().getImage("millionaire.jpg");	

		setLayout (null);

		JButton button = new JButton("PLAY!");
		button.setFont(new Font("Calibri", Font.BOLD, 18));
		button.setForeground(Color.WHITE);				// button text color
		button.setBackground(new Color (0,6,84));
		button.setPreferredSize(new Dimension(190, 30));			//button size
		button.setBounds(5,475,140,60);							
		button.addActionListener (new Action1());
		button.setOpaque(true);
		button.setBorderPainted(false);		

		JButton button2 = new JButton("HOW TO PLAY");
		button2.setFont(new Font("Calibri", Font.BOLD, 18));
		button2.setForeground(Color.WHITE);							// button text color
		button2.setPreferredSize(new Dimension(190, 30));			//button size
		button2.setBounds(650,470,140,60);	 						//button location
		button2.addActionListener (new Action2());
		button2.setBackground(new Color (0,6,84));					//button color
		button2.setOpaque(true);
		button2.setBorderPainted(false);
		
		add (button);
		add (button2);
	}
	//-----------------------------------------------PAINT COMPONENT-------------------------------------------------------------
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		g.drawImage(titlePic, -180, 0, this);						//position of image
		
		Font HowToPlay = new Font ("Yu Gothic Light", Font.PLAIN, 23);			// Setting up the HowToPlay rules text parameters
		
		g.setColor(Color.WHITE);									// Sets up the basic info of the game
		g.setFont(HowToPlay);
		
		String HowToPlay1 = "Play individually  ";
		String HowToPlay2 = " Start the game and click on the $100 question.";
		String HowToPlay3 = "Have the user chose the correct answer to the question.    ";
		String HowToPlay4 = "Press  Final Answer?  and then click the appropriate answer box.";
		String HowToPlay5 = "If a student would like to use phone a friend or ask the audience, click on the appropriate button.";
		String HowToPlay6 = "When the correct answer is selected, a new screen appears, click the word NEXT to move back to the main game board.";
		String HowToPlay7 = "As questions are answered correctly,rectangle showing which amount the user is at will appear. ";
		String HowToPlay8 = "Click on the appropriate dollar value.";
		String HowToPlay9 = "The rectangle will continue to move up the list until it reaches the $1 Million point. ";
		String HowToPlay10 = "The game is over when the user reaches the $1 Million mark.";

	
		
		g.drawString(HowToPlay1,  40, 650);									//Placement of first line of text
		g.drawString(HowToPlay2, 40, 690);									//Placement of second line of text	
		g.drawString(HowToPlay3, 40, 730);									//Placement of third line of text
		g.drawString(HowToPlay4, 40, 770);									//Placement of fourth line of text
		g.drawString(HowToPlay5, 40, 810);									//Placement of fifth line of text
		g.drawString(HowToPlay6, 40, 850);									//Placement of sixth line of text
		g.drawString(HowToPlay7, 40, 890);	
		g.drawString(HowToPlay8, 40, 910);	
		g.drawString(HowToPlay9, 40, 950);	
		g.drawString(HowToPlay10, 40, 970);	
	}
	//-------------------------------------------------MAIN METHOD--------------------------------------------------------------
	public static void main(String[] args) {
		frame = new JFrame ("Who Wants To Be A Millionaire?");
		myPanel = new OpenScreen (); 
		frame.add (myPanel);
		frame.pack ();
		frame.setVisible (true);
	}
	//--------------------------------------------Action1 - ActionListener-----------------------------------------------------
	static class Action1 implements ActionListener 
	{        
		public void actionPerformed (ActionEvent e) 
		{     
			JFrame frame2 = new JFrame("Clicked");
			frame2.setVisible(true);
			frame2.setSize(200,100);

			JLabel label = new JLabel("you clicked me");
			label.setFont(new Font("Calibri", Font.BOLD, 18));
			label.setForeground(Color.WHITE);		
			JPanel panel = new JPanel();
			frame2.add(panel);
			panel.add(label);       
		}
	}  
	//--------------------------------------------Action1 - ActionListener-----------------------------------------------------
	static class Action2 implements ActionListener 
	{        
		public void actionPerformed (ActionEvent e) 
		{     
			JFrame frame3 = new JFrame("Clicked");
			frame3.setVisible(true);
			frame3.setSize(400,600);

			JLabel label = new JLabel("How to play!");
			JLabel label2 = new JLabel("How to play!");
			
			JPanel panel = new JPanel();
			frame3.add(panel);
			panel.add(label);
		}
	}   
	//-------------------------------------------------------------------------------------------------
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

}
