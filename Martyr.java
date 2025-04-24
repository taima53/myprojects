package application;

public class Martyr implements Comparable<Martyr> {
	private String name;
	private String Date;
	private int age;
	private String location;
	private String district;
	private String gender;

	/* constructor with all arguments. */
	public Martyr(String name, String date, int age, String location, String district, String gender) {
		this.name = name;
		this.Date = date;
		this.age = age;
		this.location = location;
		this.district = district;
		this.gender = gender;
	}

	/* constructor with argument name */
	public Martyr(String name) {
		this.name = name;
	}

	/* Getter & Setter method of name. */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* Getter & Setter method of date. */
	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		this.Date = date;
	}

	/* Getter & Setter method of age. */
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/* Getter & Setter method of location. */
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/* Getter & Setter method of district. */
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	/* Getter & Setter method of gender. */
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/* to string method to print name */
	public String toString() {
		return name + " ";
	}

	/* compare to method that compared by age. */
	@Override
	public int compareTo(Martyr o) {
		if (this.age > o.age)
			return 1;
		else if (this.age < o.age)
			return -1;
		else
			return 0;
	}
}
