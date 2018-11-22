package de.rieckpil.learning.codingchallenges.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
	private int id;
	private String name;
	private double cgpa;

	public Student(int id, String name, double cgpa) {
		this.id = id;
		this.name = name;
		this.cgpa = cgpa;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public double getCgpa() {
		return this.cgpa;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", cgpa=" + cgpa + "]";
	}

}

class Priorities {

	public List<Student> getStudents(List<String> events) {
		java.util.PriorityQueue<Student> priorityQueue = new java.util.PriorityQueue<Student>(events.size(),
				java.util.Comparator.comparing(Student::getCgpa).reversed().thenComparing(Student::getName)
						.thenComparing(Student::getId));

		for (String event : events) {
			if (event.equals("SERVED")) {
				priorityQueue.poll();
			} else {
				String[] tokens = event.split(" ");
				Student student = new Student(Integer.valueOf(tokens[3]), tokens[1], Double.valueOf(tokens[2]));
				priorityQueue.add(student);
			}
		}

		List<Student> students = new ArrayList<>();

		while (!priorityQueue.isEmpty()) {
			students.add(priorityQueue.poll());
		}

		return students;
	}
}

public class PriorityQueueExample {
	private final static Scanner scan = new Scanner(System.in);
	private final static Priorities priorities = new Priorities();

	/**
	 * Sample input:
	 	12
		ENTER John 3.75 50
		ENTER Mark 3.8 24
		ENTER Shafaet 3.7 35
		SERVED
		SERVED
		ENTER Samiha 3.85 36
		SERVED
		ENTER Ashley 3.9 42
		ENTER Maria 3.6 46
		ENTER Anik 3.95 49
		ENTER Dan 3.95 50
		SERVED
	 * @param args
	 */
	public static void main(String[] args) {
		int totalEvents = Integer.parseInt(scan.nextLine());
		List<String> events = new ArrayList<>();

		while (totalEvents-- != 0) {
			String event = scan.nextLine();
			events.add(event);
		}

		List<Student> students = priorities.getStudents(events);

		if (students.isEmpty()) {
			System.out.println("EMPTY");
		} else {
			for (Student st : students) {
				System.out.println(st.getName());
			}
		}
	}
}
