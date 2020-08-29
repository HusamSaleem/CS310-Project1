package application;

import java.util.Scanner;

import dataStructureClasses.CircularLinkedList;
import other_Data_Classes.Process;

public class RoundRobinProcess {

	CircularLinkedList<Process> list;

	Scanner scan = new Scanner(System.in);

	int timeQuantum;

	public RoundRobinProcess() {
		getInput();
		list = new CircularLinkedList<Process>();
		showMenu();
	}

	public static void main(String[] args) {
		RoundRobinProcess app = new RoundRobinProcess();
	}

	public void getInput() {
		System.out.println("Please enter a number for the specified 'Time Quantum'");
		timeQuantum = Integer.parseInt(scan.next());

		if (timeQuantum <= 0) {
			System.out.println("Please enter a Time Quantum that is greater than 0.");
			getInput();
		}
	}

	public void showMenu() {
		System.out.println();
		System.out.println("0. Exit");
		System.out.println("1. Enter a process");
		System.out.println("2. Start execution");

		int choice = Integer.parseInt(scan.next());

		if (choice > 2 || choice < 0) {
			System.out.println("Invalid choice. Please try again");
			showMenu();
		}

		if (choice == 0) {
			System.exit(0);
		} else if (choice == 1) {
			System.out.println("Please enter the name of the process");
			String name = scan.next();

			System.out.println("Please enter the required CPU time");
			int time = Integer.parseInt(scan.next());

			System.out.println("Please enter the priority of this process");
			int priot = Integer.parseInt(scan.next());

			createProcess(name, time, priot);
			System.out.println(list.toString());
			showMenu();
		} else if (choice == 2) {
			startExecution();
		}
	}

	public void startExecution() {
		CircularLinkedList<Process>.Node curNode = list.root;

		int i = 0;

		System.out.println("Initial processes: ");
		System.out.println(list.toString());
		
		int cycle = 0;
		while (!list.isEmpty()) {

			// Reduce each process time by the time quantum
			while (curNode != null && i < list.size) {
				float time = curNode.data.getTime() - timeQuantum;
				curNode.data.setTime(Math.max(time, 0));

				curNode = curNode.next;
				i++;
			}

			cycle++;
			System.out.println("Cycle: " + cycle);
			System.out.println(list.toString());

			i = 0;
			curNode = list.root;

			// Remove any processes that have finished
			while (curNode != null && i < list.size) {
				if (curNode.data.getTime() <= 0) {
					list.remove(curNode.data);
				}
				curNode = curNode.next;
				i++;
			}

			i = 0;
			curNode = list.root;
		}

		System.out.println("Cycles complete!");
		System.out.println("Exiting the program...");
		System.exit(1);
	}

	public void createProcess(String name, int time, int priority) {
		Process proc = new Process(name, time, priority);
		list.insert(proc);
	}
}