package Main;

import java.util.ArrayList;
import java.util.Stack;

/**implementation of the Greedy algorithm
 * 
 * @author Ralph SInnhuber
 *
 */
public class GreedyPath {
	ArrayList<PathPair> list = new ArrayList<PathPair>();//holds the next node to be explored at the front of the queue 
 // holds the path to the current node in nodeQueue as its first value
	ArrayList<Node> visited = new ArrayList<Node>(); // Holds all the nodes that have been explored previously
	Node start;
	Node finish;
	ArrayList<Connection> visitedEdges = new ArrayList<Connection>();
	ArrayList<Connection> frontierEdges = new ArrayList<Connection>();
	Stack<Node> nodeStack = new Stack<Node>();
	
		/**constructor
		 * 
		 * @param m map the algorithm will run on
		 * @param startInt starting node
		 * @param finishInt ending node
		 */
        public GreedyPath(Map m, int startInt, int finishInt)
        {
            start = m.list[startInt];
            finish = m.list[finishInt];
        }

        /**method for running the search algorithm on the graph
         * 
         * @param m map to be searched through
         * @return list of instances used for visualisation
         */
		public ArrayList<Instance> Search(Map m) {
			ArrayList<Instance> instances = new ArrayList<Instance>();
			
			PathPair startPair = new PathPair(start, null);
	
			list.add(startPair);
			
			while(!list.isEmpty()){
				PathPair currentPair = list.get(0);
				Node current = currentPair.getNode();
				list.remove(0);
				if(!(currentPair.getPrevious()==null)){
					Connection conn = findConnection(currentPair, m);
					visitedEdges.add(conn);
				}
				
				if(!visited.contains(current)){
					visited.add(current);
					
					ArrayList<Node> currentF = new ArrayList<Node>(nodeStack);
					ArrayList<Connection> currentFE = new ArrayList<Connection>(frontierEdges);
					ArrayList<Node> expandedF = new ArrayList<Node>();
					ArrayList<Connection> expandedE = new ArrayList<Connection>();
				
					
					if(!(current == finish)){
					
						for(int i = 0; i<current.connectionList.length; i++){
							Connection x = current.connectionList[i];
							int connects = x.connectsTo;
							
							if(!(connects == -1)){
								
							Node next = m.list[connects];
							PathPair nextPath = new PathPair(next, currentPair);
							addToList(nextPath);
							frontierEdges.add(x);			
							nodeStack.push(next);
							expandedF.add(next);
							expandedE.add(x);
							}
						}
						Instance instance = new Instance(new ArrayList<Node>(visited), currentF, new ArrayList<Connection>(visitedEdges),currentFE, currentPair, expandedF, expandedE, true);
						instances.add(instance);	
					}
					else{
						Instance instance = new Instance(new ArrayList<Node>(visited), new ArrayList<Node>(nodeStack), new ArrayList<Connection>(visitedEdges),new ArrayList<Connection>(frontierEdges), currentPair, false);							//Instance instance = new Instance(visited, stack, currentPair);
						instances.add(instance);
						//System.out.println(currentPair.toString(m));
						return instances;
						/*Instance instance = new Instance(new ArrayList<Node>(visited), new ArrayList<Node>(nodeStack), new ArrayList<Connection>(visitedEdges),new ArrayList<Connection>(frontierEdges), currentPair, false);
						instances.add(instance);	
						return instances;*/
					}
				
					
				}
				else{
					Instance instance = new Instance(new ArrayList<Node>(visited), new ArrayList<Node>(nodeStack), new ArrayList<Connection>(visitedEdges),new ArrayList<Connection>(frontierEdges), currentPair, false);							//Instance instance = new Instance(visited, stack, currentPair);
					instances.add(instance);
				}
			}
			return instances;	
		
		}
		
		/**extend a path
		 * 
		 * @param nextPath next stage in the path
		 */
		private void addToList(PathPair nextPath){
		
			Coord coord = nextPath.getNode().getCoord();
		
			for(int i = 0; i<list.size(); i++){
				Node y = list.get(i).getNode();
				Coord listCoord = y.getCoord();
			
				if(DistanceToGoal(coord, finish.getCoord())<DistanceToGoal(listCoord, finish.getCoord())){
					list.add(i, nextPath);
					break;
				}
			
			}
		
			if(!list.contains(nextPath)){
			list.add(nextPath);
			}
		}

		/**find the distance between 2 nodes
		 * 
		 * @param x 1st coordinate
		 * @param finish 2nd coordinate
		 * @return the absolute distance from one coordinate to another
		 */
		private int DistanceToGoal(Coord x, Coord finish){
			int xLength = (x.getX()) - (finish.getX());
			int yLength = (x.getY()) - (finish.getY());
	
			int length = (int) Math.sqrt((xLength*xLength)+(yLength*yLength));
	
			return length;
		}
		
		/**find a connection referenced by a path pair
		 * 
		 * @param cp path pair to be referenced
		 * @param map map connection is in
		 * @return the valid connection
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
