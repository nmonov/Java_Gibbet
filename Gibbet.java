/*
 * Реализирайте играта “Бесеница” – за тайна дума, потребителят трябва да въвежда буква, 
 * след което да се отпечатва думата, в която са показани отгатнатите букви, 
 * а на мястото на неотгатнатите има _. Също така потребителят трябва да знае колко опита му остават.
 */

package gibbetV2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Gibbet {
	
	static void printGibbet(int attempt) {
		switch (attempt) {
		case 1:
			System.out.println("+----+");
			System.out.println("|    |");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|\n");
			break;
		case 2:
			System.out.println("+----+");
			System.out.println("|    |");
			System.out.println("|   ( )");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|\n");
			break;
		case 3:
			System.out.println("+----+");
			System.out.println("|    |");
			System.out.println("|   ( )");
			System.out.println("|    |");
			System.out.println("|    |");
			System.out.println("|");
			System.out.println("|\n");
			break;
		case 4:
			System.out.println("+----+");
			System.out.println("|    |");
			System.out.println("|   ( )");
			System.out.println("|    |");
			System.out.println("|    |");
			System.out.println("|   /");
			System.out.println("|\n");
			break;
		case 5:
			System.out.println("+----+");
			System.out.println("|    |");
			System.out.println("|   ( )");
			System.out.println("|    |");
			System.out.println("|    |");
			System.out.println("|   / \\");
			System.out.println("|\n");
			break;
		case 6:
			System.out.println("+----+");
			System.out.println("|    |");
			System.out.println("|   ( )");
			System.out.println("|   \\|");
			System.out.println("|    |");
			System.out.println("|   / \\");
			System.out.println("|\n");
			break;
		case 7:
			System.out.println("+----+");
			System.out.println("|    |");
			System.out.println("|   ( )");
			System.out.println("|   \\|/");
			System.out.println("|    |");
			System.out.println("|   / \\");
			System.out.println("|\n");
			System.out.println("You loose!");
			break;
		}
	}
	
	static void printRules() {
		System.out.println("+========================================================================+");
		System.out.println("|                     Welcome to Java Gibbet v1.2 !                      |");
		System.out.println("+========================================================================+");
		System.out.println("RULES: Try to guess a word in 7 attempts.");
		System.out.println("On each turn type 1 letter which in your opinion exists in the word.");
		System.out.println("If it exists, it will appear on the screen. Else the gibbet will progress.");
		System.out.println("==========================================================================");
	}
	
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		
		printRules();
		
		
		// String path = System.getProperty("user.dir");
		// System.out.println(path + "\\src\\gibbetV2\\words.txt");
		File inputFile = new File("src\\gibbetV2\\words.txt");
		// File inputFile = new File(Gibbet.class.getResource("words.txt").getPath());
		// Scanner fileIn = new Scanner(inputFile, System.getProperty("sun.jnu.encoding"));
		Scanner fileIn = new Scanner(inputFile, "UTF-8");
		
		
		int lineCounter = 0;
		while(fileIn.hasNextLine()) {
			fileIn.nextLine();
			lineCounter++;
		}
		
		// System.out.println("Number Of Lines: " + lineCounter);
		
		Random rnd = new Random();
		int random = rnd.nextInt(lineCounter - 1) + 1;

		
		String answer = null;
		
		Scanner fileIn_2 = new Scanner(inputFile, "UTF-8");
		int line = 1;
		while(true) {
			if (line < random) {
				fileIn_2.nextLine();
				line++;
				continue;
			}
			if (line == random) {
				answer = fileIn_2.nextLine();
				break;
			}
		}
		
		String empty = "";
		
		for(int j=0; j<answer.length(); j++) {
			if(answer.charAt(j) == ' ') {
				empty += " ";
			} else {
				empty += "_";
			}
		}

		//Test:
		//System.out.println("Answer is: " + answer);
		//System.out.println(empty);
		//System.out.println(answer.length());
		//System.out.println(empty.length());
		
/*		
  		// this should generate random words in v2.0 of the game
		String answer = "m a c i n t o s h";
		// if answer is random generated, the empty string should have appropriate number of "_"
		String empty = "_ _ _ _ _ _ _ _ _";
*/

		
		StringBuilder word = new StringBuilder(empty);

		int attempt = 1;

		do {
			
			System.out.printf("Attempt %d. Guess the word (enter a letter): %s \n", attempt, word);
			
			//System.out.println("Enter a char:");
			char userInput = sc.next().trim().charAt(0);
			
			int counter = 0;
			for(int i=0; i<answer.length(); i++) {
				if (String.valueOf(userInput).equalsIgnoreCase(String.valueOf(answer.charAt(i))) ) {
					word.setCharAt(i, Character.toUpperCase(userInput)); 
					if (answer.equalsIgnoreCase(word.toString())) {
						System.out.printf("Attempt %d. Guess the word (enter a letter): %s \n", attempt, word);
						System.out.println("\nYou win!");
						break;
					}
				} else {
					counter++;
				}
				
			}
			
			if (counter == answer.length()) { // if there's no concurrence of the char in the answer
				printGibbet(attempt);
				attempt++;
				if (attempt == 8) {
					break;
				}
			}
			
		} while (!answer.equalsIgnoreCase(word.toString()));
		
		System.out.println("The word is  " + answer.toUpperCase());
		
		
		
		sc.close();
		fileIn.close();
		fileIn_2.close();
		
		

	} // end main

} // end class
