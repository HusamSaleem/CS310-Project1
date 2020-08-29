package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import dataStructureClasses.Queue;
import other_Data_Classes.Student;

public class RudimentaryStudentEnrollmentManager {

	Scanner scnr = new Scanner(System.in);

	Queue<Student> queue;
	ArrayList<Student> enrolledStudents;

	private final int MAX_ITEMS = 250;

	int size;
	int capacity;

	public RudimentaryStudentEnrollmentManager() {
		getInput();

		queue = new Queue<Student>(MAX_ITEMS);
		enrolledStudents = new ArrayList<Student>(capacity);

		this.size = 0;
	}

	public static void main(String[] args) {
		RudimentaryStudentEnrollmentManager app = new RudimentaryStudentEnrollmentManager();

		// Reads from the file of records and adds all students to queue and enrolled
		// array
		app.readFromFile("records.txt");
		app.showMenu();
	}

	public void getInput() {
		System.out.println("Enter the number of open seats for your class please: ");
		capacity = Integer.parseInt(scnr.next());
	}

	public void showMenu() {
		System.out.println("\tChoose an option\t");
		System.out.println("0. Exit");
		System.out.println("1. Remove an enrolled student by their redID number");
		System.out.println("2. Display all of the current enrolled students");

		int choice = Integer.parseInt(scnr.next());

		if (choice > 2 || choice < 0) {
			System.out.println("Invalid choice, please try again\n");
			showMenu();
		}

		if (choice == 0) {
			System.exit(1);
		} else if (choice == 1) {
			System.out.println("Please enter the redID number: ");

			String redID = scnr.next();

			if (deleteStudent(redID)) {
				printEnrolledStudents();
				showMenu();
			} else {
				System.out.println("Failed to find the student with that redID number\n");
				showMenu();
			}
		} else if (choice == 2) {
			printEnrolledStudents();
			showMenu();
		}
	}

	private void printEnrolledStudents() {
		System.out.println("\tCurrently Enrolled Students\t");
		System.out.println("\t-----------------------------\t");
		for (int i = 0; i < size; i++) {
			if (enrolledStudents.get(i) != null)
				System.out.println("#" + (i+1) + " " + enrolledStudents.get(i).toString());
		}

		System.out.println("\t-----------------------------\t\n");
	}

	public boolean deleteStudent(String ID) {
		for (int i = 0; i < size; i++) {
			if (enrolledStudents.get(i).getRedID().equals(ID)) {
				enrolledStudents.remove(i);

				if (!queue.isEmpty()) {
					Student s = queue.deQueue();

					enrolledStudents.add(s);

					System.out.println("Student has been successfully removed");
					System.out.println(s.getName() + " Has taken his place\n");
				} else {
					size--;
					System.out.println("Student has been successfully removed\n");
				}
				return true;
			}
		}

		return false;
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
			String name = line.substring(0, line.indexOf(','));
			String redID = line.substring(line.indexOf(',') + 2);

			redID.trim();
			addStudent(name, redID);
		}
	}

	private void addStudent(String name, String redID) {
		Student newStudent = new Student(redID, name);

		if (size < capacity) {
			enrolledStudents.add(newStudent);
			size++;
		} else {
			queue.enQueue(newStudent);
		}
	}
}