package Main;

/**a linked list of the path from the start node to the current node
 * 
 * @author Ralph Sinnhuber
 *
 */
public class PathPair {
	
	private Node node;
	private PathPair previous;
	
	/**constructor
	 * 
	 * @param node next node in path
	 * @param previous previous element of the path
	 */
	public PathPair(Node node, PathPair previous){
		
		this.setNode(node);
		this.setPrevious(previous);
	}


	/**get teh previous node in the current path
	 * 
	 * @return
	 */
	public PathPair getPrevious() {
		return previous;
	}

	/**set the previous node in a path
	 * 
	 * @param previous new previous node
	 */
	public void setPrevious(PathPair previous) {
		this.previous = previous;
	}


	/**get the Node accociated with this PathPair
	 * 
	 * @return
	 */
	public Node getNode() {
		return node;
	}

	/**set the Node accociated with this PathPair
	 * 
	 * @param node
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/** convert this type to as string
	 * 
	 * @param map map that this path is associated with
	 * @return string representation of a PathPair
	 */
	public String toString(Map map) {
		//take current and compare with map
		//recurse
		int index = 0;
		Node[] nodes = map.getList();
		
		for(int i=0; i<(nodes.length); i++){
			if(nodes[i]==node){
				index = i;
				break;
			}
		}
		
		if(!(previous==null)){
			return "" + index + (previous.toString(map));
		}
		else{
			return "" + index;
		}
		
	}
	
}
