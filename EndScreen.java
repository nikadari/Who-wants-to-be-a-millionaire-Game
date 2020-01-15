// Name: Claudia & Nika
// Date: June 2, 2018
// Description: Who wants to be a millionaire - user has to answer a series of trivia questions in order to obtain one million dollars at the end.
//--------------------------------------------------------------------------------------------------------------------
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//--------------------------------------------------------------------------------------------------------
public class EndScreen extends JPanel implements ActionListener, MouseListener  {
	private final Font CALIBRI_30 = new Font ("Calibri", Font.PLAIN, 50);
	static JFrame frame2;	
	private Image titlePic; 
	//--------------------------------------------------------------------------------------------------------
	public EndScreen () {
		setPreferredSize(new Dimension (600, 580));
		setBackground (new Color(0,6,84));
		setFont (CALIBRI_30); 
		titlePic = Toolkit.getDefaultToolkit().getImage("millionaireEnd.jpg");	
	}  
	//--------------------------------------------------------------------------------------------------------
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
				g.setColor(Color.WHITE);
				String gameOver = "GAME OVER";
				int xPos = (int) ((this.getWidth() - this.getFontMetrics(CALIBRI_30).getStringBounds (gameOver, g).getWidth ()) /2);
				g.drawString(gameOver,  50,  400);
				
				g.setColor(Color.WHITE);
				String summary = "Summary:";
				final Font CALIBRI_30 = new Font ("Calibri", Font.PLAIN, 50);
				//int xPos2 = (int) ((this.getWidth() - this.getFontMetrics(CALIBRI_30).getStringBounds (gameOver, g).getWidth ()) /2);
				g.drawString(summary,  50,  450);
				
			g.drawImage(titlePic, -20, 0, this);						//position of image
		}
	//--------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		frame2 = new JFrame ("Who Wants To Be A Millionaire?");
		EndScreen myPanel = new EndScreen (); 
		frame2.add (myPanel);
		frame2.pack ();
		frame2.setVisible (true);
	}
	//--------------------------------------------------------------------------------------------------------
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
