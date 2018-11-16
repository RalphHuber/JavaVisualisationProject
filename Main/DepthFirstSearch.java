package Main;

import java.util.ArrayList;
import java.util.Stack;

/**initial implementation of Depth first search, not in use anymore
 * 
 * @author Ralph Sinnhuber
 *
 */
public class DepthFirstSearch {
	
	Stack<Node> stack = new Stack<Node>(); //holds the next node to be explored at the front of the queue 
	ArrayList<Node> paths = new ArrayList<Node>(); // holds the path to the current node in nodeQueue as its first value
	ArrayList<Node> visited = new ArrayList<Node>(); // Holds all the nodes that have been explored previously
	
	Node start;
	Node finish;
	
        public DepthFirstSearch(Map m, int startInt, int finishInt)
        {
            start = m.list[startInt];
            finish = m.list[finishInt];
            path.add(start);
            Search(m);
        }

		private ArrayList<Node> Search(Map m) {
	
			stack.push(start);
			
			//create node, previous pair where previous is current pair
			//with current as previous and
			
			while(!stack.isEmpty()){
				Node current = stack.pop();
				
				if(!visited.contains(current)){
					visited.add(current);
			
					
					if(!(current == finish)){
					
						for(int i = 0; i<current.connectionList.length; i++){
							Connection x = current.connectionList[i];
							int connects = x.connectsTo;
							Node next = m.list[connects];
							stack.push(next);
						}
					}
					else{
						return;
					}
				}
			}
			return null;	
		}
		
}