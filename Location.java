package application;

public class Location implements Comparable<Location> {
	private String locationName;
	private SingleLinkedList<Martyr> martyrLinkedList;

	/* constructor with no - arguments. */
	public Location() {
		locationName = "null";
		martyrLinkedList = new SingleLinkedList<>();

	}

	/* constructor with name location arguments. */
	public Location(String locationName) {
		this.locationName = locationName;
		martyrLinkedList = new SingleLinkedList<>();
	}

	/* constructor with all arguments. */
	public Location(String locationName, SingleLinkedList<Martyr> martyrLinkedList) {
		this.locationName = locationName;
		this.martyrLinkedList = martyrLinkedList;
	}

	/* Getter & Setter method of location name. */
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/* Getter & Setter method of martyr linked list. */
	public SingleLinkedList<Martyr> getMartyrLinkedList() {
		return martyrLinkedList;
	}

	public void setMartyrLinkedList(SingleLinkedList<Martyr> matyrLinkedList) {
		this.martyrLinkedList = martyrLinkedList;
	}

	/* compare to method by name. */
	@Override
	public int compareTo(Location o) {
		if (this.locationName.compareToIgnoreCase(o.locationName) > 0)
			return 1;
		else if (this.locationName.compareToIgnoreCase(o.locationName) < 0)
			return -1;
		else
			return 0;

	}

	/* to String method. */
	@Override
	public String toString() {
		return locationName + " ";
	}
}
