package Main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**stores information about a particular step in performing an algorithm on a graph
 * 
 * @author Ralph Sinnhuber
 *
 */
public class Instance {
	
	private ArrayList<Node> visited;
	private Collection<Node> frontier;
	private ArrayList<Connection> vEdges;
	private ArrayList<Connection> fEdges;
	private ArrayList<Node> expandedF;
	private ArrayList<Connection> expandedE;
	private PathPair current;
	private boolean expanded;
	
	/**constructor
	 * 
	 * @param visited list of visited nodes
	 * @param arrayList list of frontier nodes
	 * @param current current path 
	 */
	public Instance(ArrayList<Node> visited, ArrayList<Node> arrayList, PathPair current){
		this.setVisited(visited);
		this.setFrontier(arrayList);
		this.setCurrent(current);
	}

	/**constructor
	 * 
	 * @param visited list of visited nodes
	 * @param frontier list of frontier nodes
	 * @param current current path 
	 * @param vEdges list of visited edges
	 * @param fEdges list of frontier edges
	 * @param e represents whether or not something has been expanded
	 */
	public Instance(ArrayList<Node> visited, ArrayList<Node> frontier, ArrayList<Connection> vEdges,
			ArrayList<Connection> fEdges, PathPair current, boolean e) {
		
		this.setVisited(visited);
		this.setFrontier(frontier);
		this.setCurrent(current);
		this.vEdges = vEdges;
		this.fEdges = fEdges;
		this.expanded = e;
	
	}
	/**constructor
	 * 
	 * @param visited list of visited nodes
	 * @param frontier list of frontier nodes
	 * @param current current path 
	 * @param vEdges list of visited edges
	 * @param fEdges list of frontier edges
	 * @param e represents whether or not something has been expanded
	 * @param currentPair current PathPair
	 */
	public Instance(ArrayList<Node> visited, ArrayList<Node> currentF, ArrayList<Connection> vEdges,
			ArrayList<Connection> fEdges, PathPair currentPair, ArrayList<Node> expandedF,
			ArrayList<Connection> expandedE, boolean b) {
		
		this.visited = visited;
		this.frontier = currentF;
		this.vEdges = vEdges;
		this.fEdges = fEdges;
		this.current = currentPair;
		this.setExpandedF(expandedF);
		this.setExpandedE(expandedE);
		this.expanded = b;
		// TODO Auto-generated constructor stub
	}

	/**get the list of visited nodes
	 * 
	 * @return
	 */
	public ArrayList<Node> getVisited() {
		return visited;
	}

	/**
	 * replace the list of visited nodes
	 * @param visited ne list
	 */
	public void setVisited(ArrayList<Node> visited) {
		this.visited = visited;
	}

	/**get the list of frontier nodes
	 * 
	 * @return
	 */
	public Collection<Node> getFrontier() {
		return frontier;
	}

	/**replace the list of frontier nodes
	 * 
	 * @param frontier new list
	 */
	public void setFrontier(Collection<Node> frontier) {
		this.frontier = frontier;
	}

	/**get the current pathPair
	 * 
	 * @return
	 */
	public PathPair getCurrent() {
		return current;
	}

	/**
	 * set the current path pair
	 * @param current new PathPair
	 */
	public void setCurrent(PathPair current) {
		this.current = current;
	}
	
	/**get the list of visited edges
	 * 
	 * @return
	 */
	public Collection<Connection> getVisitedE() {
		return vEdges;
	}
	
	/**get the list of frontier edges
	 * 
	 * @return
	 */
	public Collection<Connection> getFrontierE() {
		return fEdges;
	}

	/**get whether the current PathPair has been expanded 
	 * 
	 * @return
	 */
	public boolean isExpanded() {
		return expanded;
	}

	/**set the current to be xpanded 
	 * 
	 * @param expanded
	 */
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	/**get the lsit of expanded connections
	 * 
	 * @return
	 */
	public ArrayList<Connection> getExpandedE() {
		return expandedE;
	}

	/**set a list of expanded connections
	 * 
	 * @param expandedE new list
	 */
	public void setExpandedE(ArrayList<Connection> expandedE) {
		this.expandedE = expandedE;
	}

	/**get the list of frontier nodes
	 * 
	 * @return
	 */
	public ArrayList<Node> getExpandedF() {
		return expandedF;
	}

	/**set a new expanded list
	 * 
	 * @param expandedF new expanded nodes
	 */
	public void setExpandedF(ArrayList<Node> expandedF) {
		this.expandedF = expandedF;
	}
}
