import java.awt.*;
import javax.swing.*;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SuspectsNetworkFrame extends JFrame {
	
	private JPanel panel = new JPanel();
	private Registry registry;
	private UndirectedSparseGraph<String, String> graph;
	private JTextArea diameterArea;
	
	public SuspectsNetworkFrame(Registry aRegistry) {
		
		registry = aRegistry;
		graph = new UndirectedSparseGraph<>(); // Creating new Non-directed graph
		panel.setLayout(new BorderLayout()); // Setting the JPanel layout
		Image icon = Toolkit.getDefaultToolkit().getImage("res/network.png");
		
		ArrayList<Suspect> suspects = registry.getSuspects(); // For every Suspect in the Registry, create a new verticle
		for (Suspect suspect: suspects) {
			graph.addVertex(suspect.getCodeName());
			
		}
		int edgecount = 0; // Initialise number of edges to 0
		for (Suspect suspect1: suspects) {
			for (Suspect suspect2: suspects) {
				if (suspect1.isConnectedTo(suspect2)) {
					if (!(graph.isNeighbor(suspect1.getCodeName(), suspect2.getCodeName()))) { // For every Suspect, check if he is connected to every other Suspect and
						edgecount++;														   // if he is, connect them with a new Edge
						graph.addEdge("Edge" + edgecount, suspect1.getCodeName(), suspect2.getCodeName()); //Edge's name is set to "Edge x" where x is the number of the current edge
					}
				}	
			}
		}
		
        Layout<String, String> layout = new CircleLayout<>(graph);
        layout.setSize(new Dimension(350, 350));
        BasicVisualizationServer<String, String> visualServer = new BasicVisualizationServer<>(layout);
        visualServer.setMaximumSize(visualServer.getPreferredSize());
        visualServer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>()); // Sets the label of the verticles on the graph
        
        double diameter = DistanceStatistics.diameter(graph); // Calls the diameter method from the DistanceStatistics class
		diameterArea = new JTextArea("Diameter = " + diameter); // Sets the text for the bottom text area
		diameterArea.setEditable(false);

        panel.add(visualServer, BorderLayout.CENTER);
        panel.add(diameterArea, BorderLayout.PAGE_END);
        this.setContentPane(panel);
        
		this.setIconImage(icon);
		this.setSize(450, 450);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("Suspects Network");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
