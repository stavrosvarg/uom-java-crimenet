import java.util.ArrayList;
import java.util.Comparator;

public class Suspect {

	private String name;
	private String codeName;
	private String cityOfOperation;
	private ArrayList<String> listOfNumbers = new ArrayList<String>();
	private ArrayList<Suspect> listOfAssociates = new ArrayList<Suspect>();
	
	public Suspect(String aName, String aNickname, String aCity) {
		name = aName;
		codeName = aNickname;
		cityOfOperation = aCity;
	}
	
	public void printInfo() {
		System.out.println("==== Suspect's Details ====");
		System.out.println("Name: " + name);
		System.out.println("Nickname: " + codeName);
		System.out.println("City of Operation: " + cityOfOperation);
		System.out.println("---------------------------");
	}
	
	public String getName() {
		return name;
	}
	
	public String getCodeName() {
		return codeName;
	}
	
	public void addNumber(String aNumber) {
		listOfNumbers.add(aNumber);
	}
	
	public ArrayList<String> getListOfNumbers() {
		return listOfNumbers;
	}
	
	public void addAssociate(Suspect aSuspect) {
		if (!this.listOfAssociates.contains(aSuspect)) { 
			listOfAssociates.add(aSuspect);
			aSuspect.listOfAssociates.add(this);
		}
	}
	
	public ArrayList<Suspect> getListOfAssociates(){
		return listOfAssociates;
	}
	
	public boolean isConnectedTo(Suspect aSuspect) {
		if(listOfAssociates.contains(aSuspect))
			return true;
		else
			return false;
	}
	
	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect) {

		ArrayList<Suspect> commonPartnersA = aSuspect.getListOfAssociates();
		ArrayList<Suspect> commonPartnersB = this.getListOfAssociates();
		ArrayList<Suspect> commonPartnersC = new ArrayList<>();
		
		for (Suspect suspect: commonPartnersA) {
			if (commonPartnersB.contains(suspect))
				commonPartnersC.add(suspect);
		}
		
		return commonPartnersC;
	}
	
	/*
	 * getSuggestedPartners traverses the Suspect's list of associates. Then for
	 * every associate it traverses their own list of associates. Every Suspect who
	 * does not have an association with the original Suspect is being added to a
	 * temporary list that in the end is returned.
	 */
	
	public ArrayList<Suspect> getSuggestedPartners() {
		ArrayList<Suspect> returnList = new ArrayList<Suspect>();
		
		for (Suspect suspect: listOfAssociates) {
			ArrayList<Suspect> associateList = suspect.getListOfAssociates();
			for (Suspect associate: associateList) {
				if ((!this.equals(associate)) && (!listOfAssociates.contains(associate))) {
					returnList.add(associate);
				}
			}
		}
		return returnList;
	}
	
}

/*
 * Comparator is used in order to sort an ArrayList<Suspect> based on the
 * Suspect's name attribute
 */

class SuspectNameComparator implements Comparator<Suspect> {

	@Override
	public int compare(Suspect o1, Suspect o2) {
		String SuspectName1 = o1.getName().toUpperCase();
  	  	String SuspectName2 = o2.getName().toUpperCase();
		  
  	  	return SuspectName1.compareTo(SuspectName2);
	}
	
}
