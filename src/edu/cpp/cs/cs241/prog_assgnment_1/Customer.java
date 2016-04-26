package edu.cpp.cs.cs241.prog_assgnment_1;

public class Customer implements Comparable<Customer> {

	private String name;
	private int priority;
	
	public Customer(String name, int priority) {
		this.name = name;
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	public String toString(){
		return name;
	}
	
	/*if customer being passed in the method is less important
	 *  (the number value of priority is higher) then returns -1. */
	public int compareTo(Customer o) {
		if (this.priority < o.priority){return -1;}
		else {return 1;}
	}
}
