
public class PhoneCall extends Communication {
	
	private int durationOfCall;
	
	public PhoneCall(String someNumber, String otherNumber, int aDay, int aMonth, int aYear, int someDuration) {
		super(someNumber, otherNumber, aDay, aMonth, aYear);
		durationOfCall = someDuration;
	}
	
	/*
	 * Methods printInfo and getDurationOfCall override their respective methods
	 * from Communication class
	 */
	
	public void printInfo() {
		System.out.println("This phone call has the following info");
		super.printInfo();
		System.out.println("Duration: " + durationOfCall);
	}
	
	public int getDurationOfCall() {
		return durationOfCall;
	}
	
}
