package Main;

import java.util.ArrayList;
import java.util.Stack;

/**allows a depth first search through a map
 * 
 * @author Ralph Sinnuber
 *
 */
public class DFSPath {

		
		Stack<PathPair> stack = new Stack<PathPair>(); //holds the next node to be explored at the front of the queue 
		 // holds the path to the current node in nodeQueue as its first value
		ArrayList<Node> visited = new ArrayList<Node>();// Holds all the nodes that have been explored previously
		ArrayList<Connection> visitedEdges = new ArrayList<Connection>();
		ArrayList<Connection> frontierEdges = new ArrayList<Connection>();
		Node start;
		Node finish;
		Stack<Node> nodeStack = new Stack<Node>();
		
			/**Constructor
			 * 
			 * @param m map to be searched on
			 * @param startInt starting node of search
			 * @param finishInt finishing node
			 */
	        public DFSPath(Map m, int startInt, int finishInt)
	        {
	            start = m.list[startInt];
	            finish = m.list[finishInt];
	            
	        }

	        /**performs a depth first search on a given map
	         * 
	         * @param m map to be searched through
	         * @return a list of instances for the visualisation
	         */
			public ArrayList<Instance> Search(Map m) {
				
				ArrayList<Instance> instances = new ArrayList<Instance>();
				
				PathPair startPair = new PathPair(start, null);
		
				stack.push(startPair);
				nodeStack.push(start);
				//return steps that dfs took (every if and while)
				
				while(!stack.isEmpty()){
					
					PathPair currentPair = stack.pop();
					Node current = currentPair.getNode();
					if(!(currentPair.getPrevious()==null)){
					Connection conn = findConnection(currentPair, m);
					visitedEdges.add(conn);
					}
					
					
						
						if(!(current == finish)){
							
							if(!visited.contains(current)){
								visited.add(current);
							
								ArrayList<Node> currentF = new ArrayList<Node>(nodeStack);
								ArrayList<Connection> currentFE = new ArrayList<Connection>(frontierEdges);
								ArrayList<Node> expandedF = new ArrayList<Node>();
								ArrayList<Connection> expandedE = new ArrayList<Connection>();
								
							for(int i = 0; i<current.connectionList.length; i++){
								Connection x = current.connectionList[i];
								int connects = x.connectsTo;
								if(!(connects==-1)){
									frontierEdges.add(x);
									expandedE.add(x);
									Node next = m.list[connects];
									PathPair nextPath = new PathPair(next, currentPair);
									stack.push(nextPath);
									nodeStack.push(next);
									expandedF.add(next);
								}
							}
							Instance instance = new Instance(new ArrayList<Node>(visited), currentF, new ArrayList<Connection>(visitedEdges),currentFE, currentPair, expandedF, expandedE, true);
							instances.add(instance);	
							}
							else{
								//just have a boolean saying it has been expanded
								Instance instance = new Instance(new ArrayList<Node>(visited), new ArrayList<Node>(nodeStack), new ArrayList<Connection>(visitedEdges),new ArrayList<Connection>(frontierEdges), currentPair, false);							//Instance instance = new Instance(visited, stack, currentPair);
								instances.add(instance);
							}
							
						}
						else{
							Instance instance = new Instance(new ArrayList<Node>(visited), new ArrayList<Node>(nodeStack), new ArrayList<Connection>(visitedEdges),new ArrayList<Connection>(frontierEdges), currentPair, false);							//Instance instance = new Instance(visited, stack, currentPair);
							instances.add(instance);
							//System.out.println(currentPair.toString(m));
							return instances;
						}
					}
				
				return instances;	
			}

			/**get a connection referenced be a path pair
			 * 
			 * @param cp referenced path pair
			 * @param map map where connection is
			 * @return Connection referenced by the path pair
			 */
			private Connection findConnection(PathPair cp, Map map) {
				
				Node current = cp.getNode();
			
				Node previous = cp.getPrevious().getNode();
				int index = map.findNode(current);
				Connection[] conns = previous.getConnections();
				Connection conn = null;
				
				for(Connection c: conns){
					if(index == c.getConnect() ){
						conn = c;
						
					}
				}
				return conn;
			}
	
}
