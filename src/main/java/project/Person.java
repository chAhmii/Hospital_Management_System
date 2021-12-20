package project;

public class Person {
	protected String name;
	protected String gender;
	protected String Address;
	protected int age;
	
	public Person(String n, String g, String a, int ag) {
		name = n;
		gender= g;
		Address = a;
		age = ag;
	}
	public Person(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
