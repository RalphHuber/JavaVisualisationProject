package Main;

import java.util.ArrayList;

//you have to store a value for getting to that node
//check if its visited, if it is then check both values
//could do it so it saves the lowest value of each node
//could create a pair

/**Performs a* search but does not return a path, is not being used in the final product
 * 
 * @author Ralph Sinnhuber
 *
 */
public class ASearch {
	ArrayList<NodeHue> list = new ArrayList<NodeHue>(); //holds the next node to be explored at the front of the queue 
	ArrayList<Node> path = new ArrayList<Node>(); // holds the path to the current node in nodeQueue as its first value
	ArrayList<NodeHue> visited = new ArrayList<NodeHue>(); // Holds all the nodes that have been explored previously
	NodeHue start = new NodeHue();
	Node finish;
	
		/**constructor 
		 * 
		 * @param m The map visualisation is to be run on
		 * @param startInt Starting node of search
		 * @param finishInt	Ending node of search
		 */
        public ASearch(Map m, int startInt, int finishInt){
        	
        	finish = m.list[finishInt];
            start.setNode(m.list[startInt]);
            start.setFromStart(0);
            start.setFromGoal(distanceBetween(start.getNode(),finish));
            Search(m);
        }

        /**Performs the A* search algorithm
         * 
         * @param m Map to be searched
         * @return The successful path from the start node to the goal node
         */
		private ArrayList<Node> Search(Map m) {
	
			list.add(start);
			
			while(!list.isEmpty()){
				NodeHue current = list.get(0);
				list.remove(0);
				
				if(useCurrent(current)){
					visited.add(current);
					//path.add(current);
					
					if(!(current.getNode() == finish)){ 
					
						for(int i = 0; i<current.getNode().connectionList.length; i++){
							Connection x = current.getNode().connectionList[i];
							int connects = x.connectsTo;
							
							if(!(connects==-1)){
								Node next = m.list[connects];
								//make into pair using parent then add pair to list
								NodeHue nextP = new NodeHue();
								nextP.setNode(next);
								int lFromSource = current.getFromStart() + distanceBetween(current.getNode(), next);
								nextP.setFromStart(lFromSource);
								int lToGoal = distanceBetween(next, finish);
								nextP.setFromGoal(lToGoal);
								
								addToList(nextP);
							}
		
						}
					}
					else{
						return path;
					}
				}
				else{
					//compare the two and if this one is better then replac use it as values from there will be different
				}
			}
			return null;	
		
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