// Name: Claudia & Nika
// Date: June 2, 2018
// Description: Holds the methods which are to be called in the MillionaireGui2 used for the money score part of the game.
//-----------------------------------------------------------------------------------------------------------------------------
import java.util.Scanner;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
//-----------------------------------------------------------------------------------------------------------------------------
public class MoneyBoard  {
	private int currentAmount = 0;
	//---------------------------------------------------------------CONSTRUCTOR--------------------------------------------------------------
	public MoneyBoard () {
		// DESCRIPTION: empty method created to be called in the MailliaonaireGUI2 class 
		// PARAPETERS:	none
		// RETURN: Void
	}
	//-----------------------------------------------------------GETTER------------------------------------------------------------------
	public int getScore (){
		// DESCRIPTION: returns score when called
		// PARAPETERS:	none
		// RETURN: int for the score achieved by user
		return currentAmount;
	}
	//-----------------------------------------------------------------------------------------------------------------------------
	public void resetScore (){
		// DESCRIPTION: resets score value
		// PARAPETERS: none
		// RETURN: Void
		currentAmount = 0;
	}
	//-----------------------------------------------------------------------------------------------------------------------------
	public void setScore (int counter){
		// DESCRIPTION: method to set the worth of each question
		// PARAPETERS:	counts which question its currently at
		// RETURN: Void
		if (counter == 0)
			currentAmount = 100;

		if (counter == 1) 
			currentAmount = 200;

		if (counter == 2) 
			currentAmount = 300;

		if (counter == 3)
			currentAmount = 500;

		if (counter == 4) 
			currentAmount = 1000;

		if (counter == 5) 
			currentAmount = 2000;

		if (counter == 6) 
			currentAmount = 4000;

		if (counter == 7) 
			currentAmount = 8000;

		if (counter == 8) 
			currentAmount = 16000;

		if (counter == 9)
			currentAmount = 32000;

		if (counter == 10) 
			currentAmount = 64000;

		if (counter == 11) 
			currentAmount = 125000;

		if (counter == 12) 
			currentAmount = 250000;

		if (counter == 13) 
			currentAmount = 500000;

		if (counter == 14) 
			currentAmount = 1000000;
	}

	public String toString(){
		return ""+currentAmount; 
	}

}

