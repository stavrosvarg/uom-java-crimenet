import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class NotFoundFrame extends JFrame {

	private JPanel panel = new JPanel();
	private JLabel suspectLabel;
	private JButton okButton = new JButton("OK");
	
	public NotFoundFrame(String suspectName) {
		
		suspectLabel = new JLabel("Suspect " + suspectName + " Not Found");
		JLabel picLabel = new JLabel(new ImageIcon("res/system.png"));
		
		panel.add(picLabel);
		panel.add(suspectLabel);
		panel.add(okButton);

		this.setContentPane(panel);
		
		Image icon = Toolkit.getDefaultToolkit().getImage("res/close.png");    
		this.setIconImage(icon); 
		this.setVisible(true);
		//this.setSize(320, 120);
		this.setLocation(1090, 500);
		this.pack();
		this.setTitle("Message");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ButtonListener listener = new ButtonListener();
		okButton.addActionListener(listener);
		
	}
	
	class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource().equals(okButton)) {
				dispose();
			}
		}
	}
}
