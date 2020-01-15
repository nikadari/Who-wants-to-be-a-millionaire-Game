// Name: Claudia & Nika
// Date: June 2, 2018
// Description: Holds the methods which are to be called in the MillionaireGui2 used for the money score part of the game.
//-----------------------------------------------------------------------------------------------------------------------------
import java.util.*;
import java.io.*;
//-----------------------------------------------------------------------------------------------------------------------------
public class Questions {
	private String question;
	private String correct;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	//------------------------------------------------------CONSTRUCTOR-----------------------------------------------------------------
	public Questions (String q, String a, String b, String c, String d, String cor){
		// DESCRIPTION: Method which creates variables for the quesitons and answers
		// PARAPETERS:	the variables for the questions and answers
		// RETURN: Void
		question = q;
		optionA = a;
		optionB = b;
		optionC = c;
		optionD = d;
		correct = cor;
	}
	//------------------------------------------------------GETTERS-----------------------------------------------------------------
	public String getQuestion()
	// DESCRIPTION: Method which grabs the questions 
	// PARAPETERS:	none
	// RETURN: the questions
	{	
		return question;
	}

	public String getOptionA()
	// DESCRIPTION: Method which holds option A as a possible answer
	// PARAPETERS:	none
	// RETURN: the option
	{
		return optionA;
	}

	public String getOptionB()
	// DESCRIPTION: Method which holds option B as a possible answer
	// PARAPETERS:	none
	// RETURN: the option
	{
		return optionB;
	}

	public String getOptionC()
	// DESCRIPTION: Method which holds option C as a possible answer
	// PARAPETERS:	none
	// RETURN: the option
	{
		return optionC;
	}

	public String getOptionD()
	// DESCRIPTION: Method which holds option D as a possible answer
	// PARAPETERS:	none
	// RETURN: the option
	{
		return optionD;
	}

	public String getCorrect()
	// DESCRIPTION: Method which holds the correct answer
	// PARAPETERS:	none
	// RETURN: the option
	{
		return correct;
	}

	//------------------------------------------------------TO STRING-----------------------------------------------------------------
	public String toString()
	// DESCRIPTION: Method which stores the questions and answers in the specific order in a tempoarary variable
	// PARAPETERS:	none
	// RETURN: Void
	{
		String temp = question + "\n" + optionA +"\n" + optionB+"\n" + optionC + "\n" + optionD + "\ncorrect - " + correct;
		return temp;
	}
}
