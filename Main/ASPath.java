package Main;

import java.util.ArrayList;

/**A class that Performs the A* search algorithm on a selected map
 * 
 * @author Ralph Sinnhuber
 *
 */
public class ASPath {
	ArrayList<NodeHue> list = new ArrayList<NodeHue>(); //holds the next node to be explored at the front of the queue 

	ArrayList<NodeHue> visited = new ArrayList<NodeHue>(); // Holds all the nodes that have been explored previously
	ArrayList<Connection> visitedEdges = new ArrayList<Connection>();
	ArrayList<Connection> frontierEdges = new ArrayList<Connection>();
	
	NodeHue start = new NodeHue();
	Node finish;
	
		/**The class constructor
		 * 
		 * @param m Map to perform the search on
		 * @param startInt Starting node for the search
		 * @param finishInt Goal node for the search
		 */
        public ASPath(Map m, int startInt, int finishInt){
        	
        	finish = m.list[finishInt];
        	Node startNode = (m.list[startInt]);
        	PathPair startPair = new PathPair(startNode, null);
            start.setPathPair(startPair);
            start.setFromStart(0);
            start.setFromGoal(distanceBetween(start.getNode(),finish));
        }

        /**Performs an A* search on a given map
         * 
         * @param m Map to perform the search on
         * @return An array of instances to be used by the visualisation classes
         */
        public ArrayList<Instance> Search(Map m) {
        	ArrayList<Instance> instances = new ArrayList<Instance>();
			list.add(start);
			
			while(!list.isEmpty()){
				NodeHue current = list.get(0);
				list.remove(0);
				PathPair currentPair = current.getPathPair();
				Node currentNode = currentPair.getNode();
				if(!(currentPair.getPrevious()==null)){
					Connection conn = m.findConnection(currentPair.getPrevious().getNode(), currentNode);
					visitedEdges.add(conn);
					}
				
				
				if(useCurrent(current)){
					visited.add(current);
					
					ArrayList<Node> currentF = toNode(list);
					ArrayList<Connection> currentFE = new ArrayList<Connection>(frontierEdges);
					ArrayList<NodeHue> expandedF = new ArrayList<NodeHue>();
					ArrayList<Connection> expandedE = new ArrayList<Connection>();
					
					if(!(current.getNode() == finish)){ 
					
						for(int i = 0; i<current.getNode().connectionList.length; i++){
							Connection x = current.getNode().connectionList[i];
							int connects = x.connectsTo;
							
							if(!(connects==-1)){
								Node next = m.list[connects];
								PathPair nextPath = new PathPair(next, currentPair);
								//make into pair using parent then add pair to list
								NodeHue nextP = new NodeHue();
								nextP.setPathPair(nextPath);
								int lFromSource = current.getFromStart() + distanceBetween(current.getNode(), next);
								nextP.setFromStart(lFromSource);
								int lToGoal = distanceBetween(next, finish);
								nextP.setFromGoal(lToGoal);
								nextP.setFLength(lFromSource + lToGoal);
								System.out.println(nextP.getFromGoal()+"goal");
								System.out.println(nextP.getFromStart()+"start");
								System.out.println(nextP.getFLength()+"hue");
								
								addToList(nextP);
								
								frontierEdges.add(x);			
								expandedF.add(nextP);
								expandedE.add(x);
							}
		
						}
						Instance instance = new Instance(new ArrayList<Node>(toNode(visited)), currentF, new ArrayList<Connection>(visitedEdges), currentFE, currentPair, toNode(expandedF), expandedE, true);
						instances.add(instance);
					}
					else{
						Instance instance = new Instance(new ArrayList<Node>(toNode(visited)), currentF, new ArrayList<Connection>(visitedEdges),new ArrayList<Connection>(frontierEdges), currentPair, false);							//Instance instance = new Instance(visited, stack, currentPair);
						instances.add(instance);
						System.out.println(currentPair.toString(m));
						return instances;
						
					}
				}
				else{
					Instance instance = new Instance(new ArrayList<Node>(toNode(visited)), new ArrayList<Node>(toNode(list)), new ArrayList<Connection>(visitedEdges),new ArrayList<Connection>(frontierEdges), currentPair, false);							//Instance instance = new Instance(visited, stack, currentPair);
					instances.add(instance);
				}
			}
			return instances;	
		
		}
		
        /**converts a list of hueristics into a list of nodes
         * 
         * @param toTurn list of type Hue
         * @return A List of Nodes
         */
private ArrayList<Node> toNode(ArrayList<NodeHue> toTurn) {
		ArrayList<Node> n = new ArrayList<Node>();
		
		for(NodeHue nh: toTurn){
			n.add(nh.getNode());
		}
			
			
			return n;
		}


/**Compares The heuristic values of a node and the the current node
 * 
 * @param current the current node in the search
 * @return whether the current node should be used as the next node in the algorithm
 */
private boolean useCurrent(NodeHue current) {
			for(NodeHue pair: visited){
				if(current.getNode()==pair.getNode()){
					if(current.getFLength()<pair.getFLength()){
						visited.remove(pair);
						return true;
					}
					return false;
				}
			}
			return true;
		}

/**Adds a pair to list
 * 
 * @param pair
 */
private void addToList(NodeHue pair){
		
		for(int i = 0; i<list.size(); i++){
			NodeHue p = list.get(i);
			
			if(pair.getFLength()<p.getFLength()){
				list.add(i, pair);
				break;
			}
			
		}
		
		if(!list.contains(pair)){
			list.add(pair);
		}
}
	

/**find the distance between two nodes
 * 
 * @param x 1st node
 * @param y 2nd node
 * @return the absolute value of the distance between the two input nodes
 */
private int distanceBetween(Node x, Node y){
	int xLength = (x.getCoord().getX()) - (y.getCoord().getX());
	int yLength = (x.getCoord().getY()) - (y.getCoord().getY());
	
	int length = (int) Math.sqrt((xLength*xLength)+(yLength*yLength));
	
	return length;
}

}
