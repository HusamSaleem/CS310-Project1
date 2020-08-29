package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dataStructureClasses.Stack;

public class BalancedParentheses {

	Stack<String> stack;
	
	public final int MAX_ITEMS = 250;

	public BalancedParentheses() {
		stack = new Stack<String>(MAX_ITEMS);
	}

	public static void main(String[] args) {
		BalancedParentheses app = new BalancedParentheses();

		System.out.println("TEST CASE 1:");
		app.readFromFile("BalanceTest1.txt");
		app.checkIfBalanced();
		
		System.out.println("\nTEST CASE 2:");
		app.readFromFile("BalanceTest2.txt");
		app.checkIfBalanced();
		
		System.out.println("\nTEST CASE 3:");
		app.readFromFile("BalanceTest3.txt"); 
		app.checkIfBalanced(); 
		
		System.out.println("\nTEST CASE 4:");
		app.readFromFile("BalanceTest4.txt");
		app.checkIfBalanced();
	}
	

	// Read any text file
	public void readFromFile(String fileName) {

		File file;
		Scanner scan = null;

		try {
			file = new File("testingInputs/" + fileName);
			scan = new Scanner(file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scan.hasNextLine()) {
			String line = scan.nextLine();

			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '{' || line.charAt(i) == '}' || line.charAt(i) == '(' || line.charAt(i) == ')'
						|| line.charAt(i) == '[' || line.charAt(i) == ']') {
					stack.insert(line.charAt(i));
				}
			}
		}
	}

	// Check if the braces, parentheses and brackets are balanced
	public void checkIfBalanced() {
		// Index 0 is for curly braces
		// Index 1 is for Parentheses
		// Index 2 is for brackets
		int[] leftSidedSymbols = { 0, 0, 0 };
		int[] rightSidedSymbols = { 0, 0, 0 };
		String[] symbols = { "{", "(", "[", "}", ")", "]" };
		
		String[] strings = {"Braces", "Parentheses", "Square Brackets"};

		// Empty the stack
		while (!stack.isEmpty()) {
			String s = stack.pop().toString();

			for (int i = 0; i < 6; i++) {
				if (symbols[i].equals(s)) {
					if (i <= 2) {
						leftSidedSymbols[i]++;
					} else {
						rightSidedSymbols[i - 3]++;
					}
				}	
			}
		}
		
		// Display info
		for (int i = 0; i < 3; i++) {
			if (leftSidedSymbols[i] != rightSidedSymbols[i]) { 
				System.out.println(strings[i] + " " + symbols[i] + symbols[i + 3] + " are not balanced.");
			} else {
				System.out.println(strings[i] + " " + symbols[i] + symbols[i + 3] + " are balanced");
			}
		}
	}
}