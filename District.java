package application;

public class District implements Comparable<District> {
	private String districtName;
	private SingleLinkedList<Location> locationLinkedList;

	/* constructor with no - arguments. */
	public District() {
		districtName = "null";
		locationLinkedList = new SingleLinkedList<>();
	}

	/* constructor with district name arguments. */
	public District(String districtName) {
		this.districtName = districtName;
		locationLinkedList = new SingleLinkedList<>();
	}

	/* constructor with all arguments. */
	public District(String districtName, SingleLinkedList<Location> locationLinkedList) {
		this.districtName = districtName;
		this.locationLinkedList = locationLinkedList;
	}

	/* Getter & Setter method of district name. */
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/* Getter & Setter method of location linked list. */
	public SingleLinkedList<Location> getLocationLinkedList() {
		return locationLinkedList;
	}

	public void setLocationLinkedList(SingleLinkedList<Location> locationLinkedList) {
		this.locationLinkedList = locationLinkedList;
	}

	/* compare to method compared by district name. */
	@Override
	public int compareTo(District o) {
		if (this.districtName.compareToIgnoreCase(o.districtName) > 0)
			return 1;
		else if (this.districtName.compareToIgnoreCase(o.districtName) < 0)
			return -1;
		else
			return 0;
	}

	/* to string method. */
	@Override
	public String toString() {
		return districtName + " ";
	}
}
