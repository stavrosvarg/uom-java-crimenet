import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class SuspectFrame extends JFrame {

	private JPanel firstPanel = new JPanel();
	private JPanel secondPanel = new JPanel();
	private JPanel thirdPanel = new JPanel();
	private JPanel fourthPanel = new JPanel();
	private JPanel lastPanel = new JPanel();
	private JPanel panel = new JPanel();
	private JTextArea phoneNumberTextArea, messagesTextArea, suspectNameTextArea, suspectCodeNameTextArea;
	private JTextArea partnersTextArea, suggestedPartnersTextArea;
	private JTextField phoneNumberTextField;
	private JButton findSMSButton, backToSearchButton;
	private JLabel partnersLabel, suggestedPartnersLabel;
	private Suspect suspect;
	private Registry registry;
	
	public SuspectFrame(Registry aRegistry, String aSuspectName) {
		this.registry = aRegistry;
		this.suspect = registry.findSuspect(aSuspectName);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Border greyline = BorderFactory.createLineBorder(Color.gray);			// Sets the border color around each panel
		
		//First Panel
		
		phoneNumberTextArea = new JTextArea(4, 9);
		phoneNumberTextArea.setEditable(false);			// Makes the TextArea non editable
		suspectNameTextArea = new JTextArea(suspect.getName(), 1, 9);
		suspectNameTextArea.setEditable(false);
		suspectCodeNameTextArea = new JTextArea(suspect.getCodeName(), 1, 9);
		suspectCodeNameTextArea.setEditable(false);
		
		ArrayList<String> suspectNumbers = suspect.getListOfNumbers();
		for (String number: suspectNumbers) {
			phoneNumberTextArea.append(number + "\n");	// Prints the results on the text area instead of the console without overwriting previous text
		}
		
		firstPanel.add(suspectNameTextArea);
		firstPanel.add(suspectCodeNameTextArea);
		firstPanel.add(phoneNumberTextArea);
		firstPanel.setMaximumSize(firstPanel.getPreferredSize());
		firstPanel.setBorder(greyline);
		
		//Second Panel
		
		phoneNumberTextField = new JTextField("Enter Suspect's Phone");
		findSMSButton = new JButton("Find SMS");
		messagesTextArea = new JTextArea(10, 24);
		messagesTextArea.setEditable(false);
		
		secondPanel.add(phoneNumberTextField);
		secondPanel.add(messagesTextArea);
		secondPanel.add(findSMSButton);
		secondPanel.setMaximumSize(secondPanel.getPreferredSize());
		secondPanel.setBorder(greyline);
		
		//Third Panel
		
		partnersTextArea = new JTextArea(10, 24);
		partnersTextArea.setEditable(false);
		partnersLabel = new JLabel("Partners");
		
		ArrayList<Suspect> suspectAssociates = suspect.getListOfAssociates();
		
		Collections.sort(suspectAssociates, new SuspectNameComparator()); // Used to sort the list of associates based on their name attribute
		
		for (Suspect suspect: suspectAssociates) {
			partnersTextArea.append(suspect.getName() + ", " + suspect.getCodeName() + "\n");
		}
		
		thirdPanel.add(partnersLabel);
		thirdPanel.add(partnersTextArea);
		thirdPanel.setMaximumSize(thirdPanel.getPreferredSize());
		thirdPanel.setBorder(greyline);
		
		//Fourth Panel
		
		suggestedPartnersTextArea = new JTextArea(6 ,24);
		suggestedPartnersTextArea.setEditable(false);
		suggestedPartnersLabel = new JLabel("Suggested Partners ------>");
		
		ArrayList<Suspect> suggestedPartners = suspect.getSuggestedPartners();
		Collections.sort(suggestedPartners, new SuspectNameComparator());
		
		for (Suspect suspect: suggestedPartners) {
			suggestedPartnersTextArea.append(suspect.getName() + ", " + suspect.getCodeName() + "\n");
		}
		
		fourthPanel.add(suggestedPartnersLabel);
		fourthPanel.add(suggestedPartnersTextArea);
		fourthPanel.setMaximumSize(fourthPanel.getPreferredSize());
		fourthPanel.setBorder(greyline);
		
		//Last Panel - Back to Search
		backToSearchButton = new JButton("Back to Search Screen");
		lastPanel.add(backToSearchButton);
		
		panel.add(firstPanel);
		panel.add(secondPanel);
		panel.add(thirdPanel);
		panel.add(fourthPanel);
		panel.add(lastPanel);
		
		this.setContentPane(panel);
		
		Image icon = Toolkit.getDefaultToolkit().getImage("res/icon.png");		// Sets the requested image
		this.setIconImage(icon);												// as the window icon
		//this.setSize(550, 620);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("Suspect Page");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ButtonListener listener = new ButtonListener();
		findSMSButton.addActionListener(listener);
		backToSearchButton.addActionListener(listener);
	}
	
	class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource().equals(findSMSButton)) {
				boolean numberFound = false;
				String selectNumber = phoneNumberTextField.getText();	// Assigns what the user typed in the text area to the object selectNumber
				ArrayList<Suspect> suspects = registry.getSuspects();
				
				for(Suspect suspect: suspects) {
					ArrayList<String> suspectNumbers = suspect.getListOfNumbers();
					for (String number: suspectNumbers) {
						if (selectNumber.equals(number)) {
							numberFound = true;
							break;
						}
					}
				}
				if (numberFound) {
					messagesTextArea.setText("");
					ArrayList<String> suspectNumbers = suspect.getListOfNumbers();
					for (String number: suspectNumbers) {
						ArrayList<SMS> listOfMessages = registry.getMessagesBetween(selectNumber, number);
						for (SMS sms: listOfMessages) {
							messagesTextArea.append(sms.getContent() + "\n");
						}
					}
				}
				else
					messagesTextArea.setText("Phone Number not in Registry.");		// If the phone number is not in the Registry, a message is printed
			}																		// after deleting all previous text in the text area
			
			else if (e.getSource().equals(backToSearchButton)) {
				new FindSuspectFrame(registry);
				dispose(); // Closes old window before opening the next one
			}
		}
	}
	
	
}
