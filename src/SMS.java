
public class SMS extends Communication {

	private String contentOfMessage;
	
	public SMS(String someNumber, String otherNumber, int aDay, int aMonth, int aYear, String someContent) {
		super(someNumber, otherNumber, aDay, aMonth, aYear);
		contentOfMessage = someContent;
	}
	
	/*
	 * Methods printInfo and getContent override their respective methods from
	 * Communication class
	 */
	
	public void printInfo() {
		System.out.println("This SMS has the following info");
		super.printInfo();
		System.out.println("Text: " + contentOfMessage);
	}
	
	public String getContent() {
		return contentOfMessage;
	}

}
