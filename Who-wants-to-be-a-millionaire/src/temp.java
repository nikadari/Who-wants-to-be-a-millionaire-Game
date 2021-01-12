import java.util.*;

import javax.swing.JOptionPane;

import java.io.*;
public class temp {
	static ArrayList <Questions> listPop = new ArrayList <Questions> ();
	static ArrayList <Questions> listArts = new ArrayList <Questions> ();
	static ArrayList <Questions> listHist = new ArrayList <Questions> ();
	
	public static void audience (){
		double [] statList = new double [4];
		int t = 0;
		
		for (int i = 0 ; i < 4 ; i++){
			int randNum = (int)(Math.random()*(100-1+1)+1);
			statList[i] = randNum;
			t += randNum;
			System.out.println(statList[i]+",");
		}
		System.out.println(t +" ");
		for (int i = 0 ; i < 4 ; i++){
			double temp = (statList[i]/t) * 100;
			statList[i] = (int) Math.round(temp * 1)/1.0;
			
		}
		Arrays.sort(statList);
		Questions q = new Questions ("q","cor","b","c","d","cor");
		String stats = "Pol Results:\n";
		if (q.getCorrect().equals(q.getOptionA())){
			stats += statList[3]+"% say "+q.getOptionA()+"\n" + statList[2]+"% say "+q.getOptionB()+"\n"
					+ statList[1]+"% say "+q.getOptionC()+"\n" + statList[0]+"% say "+q.getOptionD();
		}
		if (q.getCorrect().equals(q.getOptionB())){
			stats += statList[3]+"% say "+q.getOptionB()+"\n" + statList[2]+"% say "+q.getOptionA()+"\n"
					+ statList[1]+"% say "+q.getOptionC()+"\n" + statList[0]+"% say "+q.getOptionD();
		}
		if (q.getCorrect().equals(q.getOptionC())){
			stats += statList[3]+"% say "+q.getOptionC()+"\n" + statList[2]+"% say "+q.getOptionA()+"\n"
					+ statList[1]+"% say "+q.getOptionB()+"\n" + statList[0]+"% say "+q.getOptionD();
		}
		if (q.getCorrect().equals(q.getOptionD())){
			stats += statList[3]+"% say "+q.getOptionD()+"\n" + statList[2]+"% say "+q.getOptionA()+"\n"
					+ statList[1]+"% say "+q.getOptionB()+"\n" + statList[0]+"% say "+q.getOptionC();
		}
		System.out.println(stats);
	
	}
	
	
	public static void readInQuestions (){
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
	

	
	public static void main(String[] args) {
//		readInQuestions();
//		System.out.println(listPop + "\n\n" + listArts + "\n\n" + listHist);
		audience();
	}

}
