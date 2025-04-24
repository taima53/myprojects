package application;

public class DateMartyrCount implements Comparable<DateMartyrCount>{
	 String date;
    int count;

    DateMartyrCount(String date, int count) {
        this.date = date;
        this.count = count;
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(DateMartyrCount o) {
		// TODO Auto-generated method stub
		return 0;
	}
    

}
