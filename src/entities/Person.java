package entities;

import java.util.Arrays;
import java.util.List;

public class Person {

	private String name;
	private int age;
	private double salary;
	private Gender gender;

	public Person(String name, int age, double salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Person(String name, int age, double salary, Gender gender) {
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.gender = gender;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public static List<Person> database() {
		return Arrays.asList(
				new Person("Draven", 40, 7000, Gender.MALE),
				new Person("Annie", 10, 30, Gender.FEMALE),
				new Person("GodinhoDoOutfit", 16, 5000, Gender.MALE),
				new Person("Flash", 22, 4000, Gender.MALE),
				new Person("Nora", 17, 1300, Gender.FEMALE),
				new Person("Iris", 20, 2300, Gender.FEMALE),
				new Person("Superman", 45, 3500, Gender.MALE),
				new Person("Batman", 35, 10000, Gender.MALE)
		);
	}

}
