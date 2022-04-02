import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class FindSuspectFrame extends JFrame {
	
	private JPanel panel = new JPanel();
	private JTextField enterNameField;
	private JButton findButton, visualizeButton;
	private Registry registry;
	
	public FindSuspectFrame(Registry aRegistry) {
		this.registry = aRegistry;
		
		enterNameField = new JTextField("Please enter the suspect's name");
		findButton = new JButton("Find");
		visualizeButton = new JButton("Visualize Network");
		JLabel picLabel = new JLabel(new ImageIcon("res/search.png"));
		
		panel.add(picLabel);
		panel.add(enterNameField);
		panel.add(findButton);
		panel.add(visualizeButton);
		
		this.setContentPane(panel);
		
		Image icon = Toolkit.getDefaultToolkit().getImage("res/home.png");    
		this.setIconImage(icon);  
		this.setSize(350, 100);
		//this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("Find Suspect");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ButtonListener listener = new ButtonListener();
		findButton.addActionListener(listener);
		visualizeButton.addActionListener(listener);
	}
	
	class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource().equals(findButton)) {
				
				String suspectName = enterNameField.getText();
				ArrayList<Suspect> suspects = registry.getSuspects();
				boolean found = false;
				
				for (Suspect suspect: suspects) {
					if (suspect.getName().equals(suspectName)) {
						dispose();
						new SuspectFrame(registry, suspectName);
						found = true;
						break;
					}
				}
				
				if (!found)
					new NotFoundFrame(suspectName);
			}
			else if (e.getSource().equals(visualizeButton)) {
				dispose();
				new SuspectsNetworkFrame(registry);
			}
		}
	}
	
}
