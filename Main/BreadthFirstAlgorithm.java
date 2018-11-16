package Main;

/**this class was the first implementation of breadth first search, no longer being used
 * 
 * @author Steven Cook
 *
 */
public class BreadthFirstAlgorithm {
	
	
	Node[] nodeQueue; //holds the next node to be explored at the front of the queue 
	Connection[][] pathqueue; // holds the path to the current node in nodeQueue as its first value
	Node[] Visited; // Holds all the nodes that have been explored previously
	Node start;
	Node finish;
	
        public BreadthFirstAlgorithm(Map m, int startInt, int finishInt)
        {
            start = m.list[startInt];
            finish = m.list[finishInt];
            
            BreadthFirstSearch(m);
        }
        
	/** This method performs the breadth first search algorithm on a given map
	 * it will change the values for nodeQueue, pathqueue and Visited
	 * 
	 * @param m An object of type map with a starting node, a finishing node and an 
	 * array of all nodes in the map
	 *  
	 */
	
	public void BreadthFirstSearch(Map m){
		
		// Initialize the arrays
		Node[] nodeQueue = {};
		Connection[][] pathqueue = {{}};
		Node[] Visited ={};
		
		//set the current node to the maps stating node 
		Node current = start;
		nodeQueue[0] = current;
		
		//loop until you reach the goal node
		while(current != finish && nodeQueue.length != 0 ){
			//set the current node as visited and add it to Visited[] at the end
			current.nodeVisited = true;
			Visited[Visited.length]=current;
			
			//loop through the current node's list of connections 
			for(int x = 0; x<current.connectionList.length; x++ ){
				// store the values of the connections destination node and the path of the current node
				int nodeN = current.connectionList[x].connectsTo;
				Connection path = current.connectionList[x];
				// check whether the destination node is already visited and if it is already in the queue
				if(!m.list[nodeN].nodeVisited && !contains(m.list, m.list[nodeN])){
					// add the destination to the queue and add the path to it
					addToQueue(nodeQueue, m.list[nodeN]);
					appendPath(pathqueue, path);
					
				}
			}
			// select the next item in the queue
			current = nodeQueue[0];
			
			// delete the selected node node from the queue
			delete(nodeQueue);
			delete(pathqueue);
		}
		if(current == finish){
			System.out.println("the path from "+start+" to "+finish+"is /n"+pathqueue[0].toString()
					+"and the nodes visited were "+Visited.toString());
		}
		else{
			System.out.println("finish node is not reachable");
		}
	}
	
	public void setStart(Map m, int x){
		start = m.list[x-1];
	}
	
	public void setFinish(Map m,int x){
		finish = m.list[x-1];
	}

	/**deletes the first item of the queue and moves the elements down 1
	 * 
	 * @param p a 2d array of connections, otherwise known as an array of paths
	 */
	private void delete(Connection[][] p) {
		//delete the first item
		p[0]= null;
		//move all elements in the array down 1
		for (int i = 0;i<p.length;i++){
			p[i]= p[i+1];
		}
		//delete the last item in the list which is a duplicate of the previous entry
		p[p.length]=null;
	}
	
	/**adds a connection to the path
	 * 
	 * @param pathqueue the queue of paths for unexplored nodes
	 * @param currentpath connection to the next node 
	 */
	private void appendPath(Connection[][] pathqueue, Connection currentpath) {
		// get the path to the current node
		Connection[] previouspath = pathqueue[0];
		// add the new connection to the path
		previouspath[previouspath.length] = currentpath;
		//add the full path to the end of the pathqueue
		pathqueue[pathqueue.length] = previouspath;
	}
	
	/**checks whether a Node is in a Node[] 
	 * 
	 * @param list list of nodes to be searched through
	 * @param node node to check for equality
	 * @return whether or not node is in list
	 */
	private boolean contains(Node[] list, Node node) {
		// cycle through the list
		for(int i =0 ; i<list.length;i++){
			//check the current list item against node
			if(list[i]==node){
				//return true when node is already int he list
				return true;
			}
		}
		// return false when nod is not currently in the list
		return false;
	}
	
	/** delete the first item of the list
	 * 
	 * @param n the list where the first item must be deleted
	 * 
	 */
	static void delete(Node[] n) {
		//delete the first node in the array
		n[0]= null;
		// iterate through he array moving every item down 1
		for(int i=0;i<n.length-1;i++){
			n[i] = n[i+1];
		}
		//remove duplicate item
		n[n.length] = null;
		
	}

	/**add a node to the end of the queue
	 * 
	 * @param a the node queue
	 * @param n node to be added to the queue
	 */
	private void addToQueue(Node[] a, Node n){
		// add the given node to the last item in the array
		a[a.length]=n;
	}
	
	public void setStart(Node start,Node x){
		start = x;
	}
	
	public void setFinish(Node finish, Node x){
		finish = x;
	}
	
}
