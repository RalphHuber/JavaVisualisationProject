package Main;

import java.util.ArrayList;

/**original implimentation of the greedy search algorithm, not in use
 * 
 * @author Ralph Sinnhuber
 *
 */
public class GreedySearch {
	ArrayList<Node> list = new ArrayList<Node>(); //holds the next node to be explored at the front of the queue 
	ArrayList<Node> path = new ArrayList<Node>(); // holds the path to the current node in nodeQueue as its first value
	ArrayList<Node> visited = new ArrayList<Node>(); // Holds all the nodes that have been explored previously
	Node start;
	Node finish;
	
        public GreedySearch(Map m, int startInt, int finishInt)
        {
            start = m.list[startInt];
            finish = m.list[finishInt];
            path.add(start);
            Search(m);
        }

		private ArrayList<Node> Search(Map m) {
	
			list.add(start);
			
			while(!list.isEmpty()){
				Node current = list.get(0);
				list.remove(0);
				
				if(!visited.contains(current)){
					visited.add(current);
					path.add(current);
					
					if(!(current == finish)){
					
						for(int i = 0; i<current.connectionList.length; i++){
							Connection x = current.connectionList[i];
							int connects = x.connectsTo;
							Node next = m.list[connects];
							addToList(next);
						}
					}
					else{
						return path;
					}
				}
			}
			return null;	
		
		}
		
private void addToList(Node x){
		
		Coord coord = x.getCoord();
		
		for(int i = 0; i<list.size(); i++){
			Node y = list.get(i);
			Coord listCoord = y.getCoord();
			
			if(DistanceToGoal(coord, finish.getCoord())<DistanceToGoal(listCoord, finish.getCoord())){
				list.add(i, x);
				break;
			}
			
		}
		
		if(!list.contains(x)){
			list.add(x);
		}
	}

private int DistanceToGoal(Coord x, Coord finish){
	int xLength = (x.getX()) - (finish.getX());
	int yLength = (x.getY()) - (finish.getY());
	
	int length = (int) Math.sqrt((xLength*xLength)+(yLength*yLength));
	
	return length;
}
	

}
