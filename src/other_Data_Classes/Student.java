package other_Data_Classes;

import interfaces.MyComparable;

public class Student implements MyComparable {

	private String redID;
	private String name;
	
	public Student(String redID, String name) {
		this.redID = redID;
		this.name = name;
	}
	
	public String toString() {
		return "Student RedID: " + redID + "; " + "Name: " + name;
	}
	
	//compare using redID
	public int compareTo(Object obj) {
		return redID.compareTo(((Student) obj).getRedID());
	}

	//public getter/setter methods
	public String getRedID() {
		return redID;
	}
	public void setRedID(String redID) {
		this.redID = redID;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
