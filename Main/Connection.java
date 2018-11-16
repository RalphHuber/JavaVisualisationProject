package Main;

import java.io.Serializable;

/** represents a a weighted edge between two nodes
 * 
 * @author Steven Cook
 *
 */
public class Connection implements Serializable {

	boolean isEmpty;
	int connectionLength;
	boolean connectionVisited;
	int connectsTo;
	int connectsFrom;
	boolean _isTwoWay;
	
	/**Constructor
	 * 
	 * @param to destination node of path
	 * @param length weight of edge
	 * @param isTwoWay boolean describing if it is a non directed edge
	 */
	public Connection(int to, int length, boolean isTwoWay)
	{
		connectionLength = length;
		connectionVisited = false;
		connectsTo = to;
		isEmpty = false;
		_isTwoWay = isTwoWay;
	}
	
	/**Constructor
	 * 
	 * 
	 * @param to destination node of path
	 * @param from start node of connection
	 * @param length weight of edge
	 * @param isTwoWay boolean describing if it is a non directed edge
	 * 
	 */
	public Connection(int to, int from,  int length, boolean isTwoWay)
	{
		connectionLength = length;
		connectionVisited = false;
		connectsTo = to;
		connectsFrom = from;
		isEmpty = false;
		_isTwoWay = isTwoWay;
	}
	
	/**create empty connection
	 * 
	 */
	public Connection()
	{
		isEmpty = true;
		connectionLength = -1;
		connectionVisited = true;
		connectsTo = -1; 
		_isTwoWay = false;
	}
	
	/**sets connection as visited
	 * 
	 * @param visited
	 */
	public void setVisited(boolean visited)
	{
		connectionVisited = visited;
	}
	
	/**checks connection is visited
	 * 
	 * @return connectionVisited
	 */
	public boolean getVisited()
	{
		return connectionVisited;
	}
	
	/** get length of the connection
	 * 
	 * @return connectionLength
	 */
	public int getLength()
	{
		return connectionLength;
	}
	
	/**gets the destination node
	 * 
	 * @return connectsTo
	 */
	public int getConnect()
	{
		return connectsTo;
	}
	
	/**find out whether the connection is directed
	 * 
	 * @return _isTwoWay
	 */
	public boolean getIsTwoWay()
	{
		return _isTwoWay;
	}
	
	/**check if this connection is empty
	 * 
	 * @return isEmpty
	 */
	public boolean getIsEmpty()
	{
		return isEmpty;
	}
	
	/** find the starting node of this connection
	 * 
	 * @returnconnectsFrom
	 */
	public int getFrom()
	{
		return connectsFrom;
	}
	
	/**change the starting node of this connection
	 * 
	 * @param from new starting node
	 */
	public void setFrom(int from)
	{
		connectsFrom = from;
	}
	
	public String toString(){
		return "(" + connectsFrom +", " + connectsTo + ")";
	}
	
	@Override
	public boolean equals(Object o){
		
		return (connectsTo == ((Connection) o).getConnect() && connectsFrom == ((Connection) o).getFrom());
			
		
	}
}
