
public abstract class Communication {

	private String numberOne;
	private String numberTwo;
	private int year;
	private int month;
	private int day;
	
	public Communication(String someNumber, String otherNumber, int aDay, int aMonth, int aYear) {
		numberOne = someNumber;
		numberTwo = otherNumber;
		day = aDay;
		month = aMonth;
		year = aYear;
	}
	
	public void printInfo() {
		System.out.println("Between " + numberOne + " --- " + numberTwo);
		System.out.println("on " + year + "/" + month + "/" + day);
	}
	
	public String getNumberOne() {
		return numberOne;
	}
	
	public String getNumberTwo() {
		return numberTwo;
	}
	
	public int getDurationOfCall() {
		return 0;
	}
	
	public String getContent() {
		return "";
	}
}
