import java.util.ArrayList;

public class Registry {

	private ArrayList<Suspect> allSuspects = new ArrayList<Suspect>();
	private ArrayList<Communication> allCommunications = new ArrayList<Communication>();
	
	public void addSuspect(Suspect aSuspect) {
		allSuspects.add(aSuspect);
	}
	
	/*
	 * Method addCommunication traverses the Registry's list of Suspects twice,
	 * first to find the Suspect who owns the first phone number and then a
	 * traverses the list a second time for the second number. Finally creates an
	 * association between the two Suspects.
	 */
	
	public void addCommunication(Communication aCommunication) {
		allCommunications.add(aCommunication);
		Suspect s1 = null;
		Suspect s2 = null;
		
		for (Suspect suspect: allSuspects) {
			ArrayList<String> tempList = suspect.getListOfNumbers();
			for (String number: tempList) {
				if (number.equals(aCommunication.getNumberOne()))
					s1 = suspect;
			}
		}
		for (Suspect suspect: allSuspects) {
			ArrayList<String> tempList = suspect.getListOfNumbers();
			for (String number: tempList) {
				if (number.equals(aCommunication.getNumberTwo()))
					s2 = suspect;
			}
		}
			
		if (s1 != s2)
			s1.addAssociate(s2);
	}
	
	public Suspect getSuspectWithMostPartners() {
		Suspect s1 = null;
		
		int mostPartners = 0;
		for(Suspect suspect: allSuspects) {
			ArrayList<Suspect> tempList = suspect.getListOfAssociates();
			if(tempList.size() > mostPartners) {
				mostPartners = tempList.size();
				s1 = suspect;
			}
		}
		return s1;
	}
	
	
	public PhoneCall getLongestPhoneCallBetween(String number1, String number2) {
		int longestDuration = 0;
		PhoneCall c1 = null;
		
		for (Communication communication: allCommunications) {
			if((communication.getNumberOne().equals(number1) && communication.getNumberTwo().equals(number2)) 
					|| (communication.getNumberOne().equals(number2) && communication.getNumberTwo().equals(number1))) {
				if (communication.getDurationOfCall() > longestDuration) {
					c1 = (PhoneCall) communication;
				}
			}
		}
		
		return c1;
	}
	
	
	public ArrayList<SMS> getMessagesBetween(String number1, String number2){
		ArrayList<SMS> tempList = new ArrayList<SMS>();
		
		for (Communication communication: allCommunications) {
			if((communication.getNumberOne().equals(number1) && communication.getNumberTwo().equals(number2)) 
					|| (communication.getNumberOne().equals(number2) && communication.getNumberTwo().equals(number1))) {
				if (communication.getContent().contains("Bomb") || communication.getContent().contains("Attack") 
						|| communication.getContent().contains("Explosives") || communication.getContent().contains("Gun")) {
					tempList.add((SMS) communication);
				}
			}
		}
		return tempList;
	}
	
	public ArrayList<Suspect> getSuspects() {
		return allSuspects;
	}
	
	/*
	 * findSuspect returns a specific Suspect object based on the Suspect's name
	 */	
	
	public Suspect findSuspect(String suspectName) {
		Suspect s1 = null;
		
		for (Suspect suspect: allSuspects) {
			if (suspectName.equals(suspect.getName())) {
				s1 = suspect;
			}
		}
		
		return s1;
	}
}
