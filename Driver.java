/* Name: John Ian Harris
 * Date: September 13, 2017
 * Assignment 1
 * 
 * By submitting this work, I attest it is my original work and that I did not violate the
 * University of Mississippi academic policies set forth in the "M" book.
 * 
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Driver {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//opens scanner for Choices.txt file to be read
		Scanner scanC = new Scanner(new File("Choices.txt"));
		
		String[] choices = new String[100];
		
		//reads Choices.txt and adds the values into the elements array
		int indexC = 0;
		while (scanC.hasNextLine()) {
			String line = scanC.nextLine();
			choices[indexC] = line;
			indexC++;
		}
		
		//opens scanner for eitherOr.csv file to be read
		Scanner scanS = new Scanner(new File("EitherOr.csv"));
		
		//Stores all read data from eitherOr.csv
		ArrayList<String> eitherOr = new ArrayList<String>();

		String[] parts = null;
		while (scanS.hasNextLine()) {
			String lineS = scanS.nextLine();
			parts = lineS.split(",");
			
			for (int i = 0; i < parts.length; i++) {
				eitherOr.add(parts[i]);
			}
		}
		
		ArrayList<Student> student = new ArrayList<Student>();
		
		//adds each student and their choices 
		for (int i = 0; i < eitherOr.size(); i+=51) {
			int[] tempChoices = new int[50];
			for (int j = 0; j < 50; j++) {
				tempChoices[j] = Integer.parseInt(eitherOr.get(j+i+1));
			}
			student.add(new Student(eitherOr.get(i), tempChoices));
		}
		
		//determines how many chose each choice
		for (int i = 0; i < choices.length; i+=2) {
			int choiceOne = 0;
			int choiceTwo = 0;
			int invalid = 0;
			for (int j = 0; j < student.size(); j++) {
				if (student.get(j).getChoices(i/2) == 0) {
					choiceOne += 1;
				} else if (student.get(j).getChoices(i/2) == 1) {
					choiceTwo += 1;
				} else if (student.get(j).getChoices(i/2) == -1) {
					invalid += 1;
				}
			}
			System.out.println(choices[i] + ": " + choiceOne + " \t " + choices[i+1] + ": " + choiceTwo + " \t " + "Invalid: " + invalid);
		}
		
		//storing my choices in an array for comparison
		int[] myChoices = new int[50];
		for (int i = 0; i < student.size(); i++) {
			if (student.get(i).getName().equals("John H")) {
				for (int j =  0; j < 50; j++) {
					myChoices[j] = student.get(i).getChoices(j);
				}
			}
		}
		
		//comparing my choices to other students to find students with most similar choices
		int[] similar = new int[student.size()];
		for (int i = 0; i < student.size(); i++) {
			int same = 0;
			for (int j = 0; j < 50; j++) {
				if (student.get(i).getChoices(j) == myChoices[j]) {
					same += 1;
				}
			}
			similar[i] = same;
		}
		
		System.out.println();

		//most similar
		int maxMe = 0;
		int maxOne = 0;
		int maxTwo = 0;
		for (int i = 0; i < similar.length; i++) {
			if (similar[i] > maxMe) {
				maxOne = maxMe;
				maxMe = similar[i];
			} else if (similar[i] > maxOne){
				maxOne = similar[i];
			} else if (similar[i] > maxTwo && similar[i] < maxOne) {
				maxTwo = similar[i];
			}
		}
		
		String nameOne = "";
		String nameTwo = "";
		for (int i = 0; i < similar.length; i++) {
			if (maxOne == similar[i]) {
				nameOne = student.get(i).getName();
			}
			if (maxTwo == similar[i]) {
				nameTwo = student.get(i).getName();
			}
		}
		
		//most dissimilar
		int minOne = similar[0];
		int minTwo = 0;
		for (int i = 0; i < similar.length; i++) {
			if (similar[i] < minOne) {
				minOne = minTwo;
				minTwo = similar[i];
			} else if (similar[i] < minTwo) {
				minOne = similar[i];
			}
		}
		
		String nameThree = "";
		String nameFour = "";
		for (int i = 0; i < similar.length; i++) {
			if (minOne == similar[i]) {
				nameThree = student.get(i).getName();
			}
			if (minTwo == similar[i]) {
				nameFour = student.get(i).getName();
			}
		}
		
		//output data
		System.out.println("I am most similar to the following students with " + maxOne + " and " + maxTwo + " matches.");
		System.out.println("\t" + nameOne + "\n\t" + nameTwo);
		System.out.println();
		System.out.println("I am most dissimilar to the following students with " + minOne + " and " + minTwo + " matches.");
		System.out.println("\t" + nameThree + "\n\t" + nameFour);
		
	}
	
}
