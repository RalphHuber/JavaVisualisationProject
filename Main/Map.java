package Main;

import java.io.Serializable;

/**BAsic class that acts as a list of nodes
 * 
 * @author Steven Cook
 *
 */
public class Map implements Serializable {
	
	Node[] list;
	
	/**constructor
	 * 
	 * @param numNodes number of nodes in the map
	 */
	public Map(int numNodes)
	{
		
		list = new Node[numNodes];
		
		for(int x = 0; x<numNodes; x++)
		{
			list[x] = new Node(numNodes);
		}
	}
	
	/**constructor
	 * 
	 * @param list list of  nodes
	 */
	public Map(Node[] list){
		this.list = list;
	}
	
	/**add a connection between 2 nodes
	 * 
	 * @param from starting node
	 * @param to ending node
	 * @param length weight of line
	 * @param isTwoWay set two way
	 */
	public void addConnectionMap(int from, int to, int length, boolean isTwoWay)
	{
		list[from].addConnectionNode(to, from, length, isTwoWay);
		if(isTwoWay)
		{
			list[to].addConnectionNode(from, to, length, isTwoWay);
		}
	}
	
	/**remove a connection between 2 nodes
	 * 
	 * @param to destination node of connection
	 * @param from starting node of connection
	 */
	public void emptyConnectionMap(int to, int from)
	{
		if(list[from-1].connectionList[to-1].getIsTwoWay())
		{
			list[from-1].emptyConnectionNode(to);
			list[to-1].emptyConnectionNode(from);
		}
		else
		{
			list[from-1].emptyConnectionNode(to);
		}
	}
	
	/**get the list of nodes
	 * 
	 * @return
	 */
	public Node[] getList(){
		return list;
	}

	/**set the coordinates of a node
	 * 
	 * @param index node number
	 * @param x new x coordinate
	 * @param y new y coordinate
	 */
	public void setCoord(int index, int x, int y) {
		
		list[index].setCoord(x, y);
		
	}
	
	/**find a nodes position in list
	 * 
	 * @param n node to be found
	 * @return index in list of the node
	 */
	public int findNode(Node n){
		for(int i = 0; i<list.length; i++){
			if(n == list[i]){
				return i;
			}
		}
		return (Integer) null;
	}
	
	/**find a connection in the map
	 * 
	 * @param from starting node
	 * @param to finishing node
	 * @return wanted connection
	 */
	public Connection findConnection(Node from, Node to){
		Connection[] conns = from.getConnections();
		
		int x = findNode(to);
		//System.out.println(x+" h");
		for(Connection conn: conns){
			if(conn.getConnect() == x){
				//System.out.println(conn.getConnect()+"connectsto");
				//System.out.println(conn.toString());
				return conn;
			}
			
		}
		return null;
	}
	
	@Override
	public boolean equals(Object o){
		
		return list.equals(((Map) o).getList());
	}
}
