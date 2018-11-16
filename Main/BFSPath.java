package Main;

import java.util.ArrayList;
import java.util.Stack;

/**creates a class capable of performing BreadthFirst search 
 * 
 * @author Ralph Sinnhuber
 *
 */
public class BFSPath {

		
		ArrayList<PathPair> list = new ArrayList<PathPair>(); //holds the next node to be explored at the front of the queue 
		 // holds the path to the current node in nodeQueue as its first value
		ArrayList<Node> visited = new ArrayList<Node>();// Holds all the nodes that have been explored previously
		ArrayList<Connection> visitedEdges = new ArrayList<Connection>();
		ArrayList<Connection> frontierEdges = new ArrayList<Connection>();
		Node start;
		Node finish;
		Stack<Node> nodeStack = new Stack<Node>();
		
			/**Constructor
			 * 
			 * @param m Map for search to be performed on
			 * @param startInt starting node
			 * @param finishInt goal node
			 */
	        public BFSPath(Map m, int startInt, int finishInt)
	        {
	            start = m.list[startInt];
	            finish = m.list[finishInt];
	            
	        }

	        /**perform depth first search on a given grapgh
	         * 
	         * @param m map search is performed on
	         * @return an array of instances for the visualisation class
	         */
			public ArrayList<Instance> Search(Map m) {
				
				ArrayList<Instance> instances = new ArrayList<Instance>();
				
				PathPair startPair = new PathPair(start, null);
				
				list.add(startPair);
				//return steps that dfs took (every if and while)
				
				while(!list.isEmpty()){
					PathPair currentPair = list.get(0);
					Node current = currentPair.getNode();
					list.remove(0);
					
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
									list.add(nextPath);
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
			
			
			/**get a connection from a map referenced by a pathPair
			 * 
			 * @param cp PathPair that references desired connection
			 * @param map 
			 * @return
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
