package other_Data_Classes;

import interfaces.MyComparable;

public class Process implements MyComparable {

	private String name; // process name
	private float time; // required CPU time
	// process priority is represented by positive integers
	// 1 means the highest priority
	private int priority;

	public Process(String name, int time, int priority) {
		this.name = name;
		this.time = time;
		this.priority = priority;
	}

	// compare using priority
	// return positive int: this has GREATER priority value (LOWER priority) than obj
	// return negative int: this has LESS priority value (HIGHER priority) than obj
	// return 0: this has the EQUAL priority value (SAME priority) with obj
	public int compareTo(Object obj) {
		if (priority == ((Process) obj).getPriority())
			return 0;
		if (priority > ((Process) obj).getPriority())
			return 1;
		if (priority < ((Process) obj).getPriority())
			return -1;

		return 0;
	}
	
	public String toString() {
		return "Process Name: " + name + "; " + "Time: " + time+ "; " + "Priority: " + priority;
	}

	// public getter/setter methods
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}

	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
