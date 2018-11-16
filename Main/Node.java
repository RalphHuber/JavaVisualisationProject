package Main;

import java.awt.Point;
import java.io.Serializable;

/**
 * 
 * @author Steven Cook
 *
 */
public class Node implements Serializable {
	
	Connection[] connectionList;
	boolean nodeVisited;
	Coord coord = new Coord();
	
	/**constructor
	 * 
	 * @param numNodes number of possible connections
	 */
	public Node(int numNodes) {
		connectionList = new Connection[numNodes];
		nodeVisited = false;
		for(int i = 0; i < numNodes; i++)
		{
			connectionList[i] = new Connection();
		}
	}
	
	/**constructor
	 * 
	 * @param numNodes number of possible nodes
	 * @param x x coordinate of node 
	 * @param y y coordinate of node
	 */
	public Node(int numNodes, int x, int y) {
		connectionList = new Connection[numNodes];
		nodeVisited = false;
		for(int i = 0; i < numNodes; i++)
		{
			connectionList[i] = new Connection();
		}
		
		coord = new Coord(x,y);
		
	}
	/**constructor
	 * 
	 * @param c list of connections from this node
	 * @param coord coordinates of this node
	 */
	public Node(Connection[] c, Coord coord){
		connectionList = c;
		this.coord = coord;
	}
	
	/**construct empty node
	 * 
	 */
	public Node() {
		
	}

	/**add a connection to another node
	 * 
	 * @param to
	 * @param length
	 * @param isTwoWay
	 */
	public void addConnectionNode(int to, int length, boolean isTwoWay)
	{
			connectionList[to] = new Connection(to, length, isTwoWay);
			//perhaps this should be changed
	}
	
	/**set this node as visited
	 * 
	 * @param isVisited
	 */
	public void setVisited(boolean isVisited)
	{
		nodeVisited = isVisited;
	}
	
	/**
	 * get whether this node is visited
	 * @return
	 */
	public boolean getVisited()
	{
		return nodeVisited;
	}
	
	/**create an empty connection
	 * 
	 * @param to destination node
	 */
	public void emptyConnectionNode(int to)
	{
		connectionList[to-1] = new Connection();
	}
	
	/**set the coordinates of this node
	 * 
	 * @param x new x coordinate
	 * @param y new y coordinate
	 */
	public void setCoord(int x, int y){
		coord.setX(x);
		coord.setY(y);
	}
	
	/**get the current position of this node
	 * 
	 * @return
	 */
	public Coord getCoord(){
		return coord;
	}
	
	/**get the list of connections from this node
	 * 
	 * @return
	 */
	public Connection[] getConnections(){
		return connectionList;
	}

	/**move this node 
	 * 
	 * @param i distance in x direction
	 * @param j distance in y direction
	 */
	public void translate(int i, int j) {
		
		setCoord(coord.getX()+i, coord.getY()+j);
		
	}

	/**chack if a node is close to the given coordinate
	 * 
	 * @param point given coordinate
	 * @return
	 */
	public boolean isClose(Point point) {
		int xLength = (coord.getX()) - ((int)point.getX());
		int yLength = (coord.getY()) - ((int)point.getY());

		int length = (int) Math.sqrt((xLength*xLength)+(yLength*yLength));
		
		return (length<50);
		
	}

	/**add a connection to this node
	 * 
	 * @param to destination node
	 * @param from index of this node
	 * @param length weight of connection
	 * @param isTwoWay whether the connection is two way
	 */
	public void addConnectionNode(int to, int from, int length, boolean isTwoWay) {
		connectionList[to] = new Connection(to, from, length, isTwoWay);
		
	}

	/**
	 * check for a specific connection
	 * @param conn connection to be found
	 * @return
	 */
	public boolean connectionIn(Connection conn) {
		for(Connection c: connectionList){
			if(conn==c){
				return true;
			}
		}
		return false;
	}
	

	@Override  
	public boolean equals(Object o){
		
		Connection[] conn2 = ((Node) o).getConnections();
		
		if(!(connectionList.length == (conn2.length))){
			return false;
		}
		else{
			for(int i = 0; i<connectionList.length; i++){
				if(!(connectionList[i].equals(conn2[i]))){
					return false;
				}
			}
		}
		
		return true;
		
		
	}
}

