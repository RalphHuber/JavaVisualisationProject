package Main;

import java.util.ArrayList;

/**Instance class for the A* algorithm, was deemed not necessary 
 * 
 * @author Ralph Sinnhuber
 *
 */
public class AInstance extends Instance {
	
	ArrayList<NodeHue> heur; 

	public AInstance(ArrayList<Node> visited, ArrayList<Node> arrayList, PathPair current) {
		super(visited, arrayList, current);
		// TODO Auto-generated constructor stub
	}

	public AInstance(ArrayList<Node> visited, ArrayList<Node> frontier, ArrayList<Connection> vEdges,
			ArrayList<Connection> fEdges, PathPair current, boolean e) {
		super(visited, frontier, vEdges, fEdges, current, e);
		// TODO Auto-generated constructor stub
	}

	public AInstance(ArrayList<Node> visited, ArrayList<Node> currentF, ArrayList<Connection> vEdges,
			ArrayList<Connection> fEdges, PathPair currentPair, ArrayList<Node> expandedF,
			ArrayList<Connection> expandedE, boolean b) {
		super(visited, currentF, vEdges, fEdges, currentPair, expandedF, expandedE, b);
		// TODO Auto-generated constructor stub
	}
	
	/**set a new heuristics array
	 * 
	 * @param n new heuristics array
	 */
	public void setNodeHeus(ArrayList<NodeHue> n){
		heur = n;
	}

}
