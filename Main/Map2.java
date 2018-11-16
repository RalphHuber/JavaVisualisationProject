package Main;
import java.util.ArrayList;
/**written as a replacement to Map, not enough time to implement properly
 * 
 * @author Ralph Sinnhuber
 *
 */
public class Map2 {
	
private ArrayList<Vertex> vertices ;
	
	
	public Map2()
	{
		
		vertices = new ArrayList<Vertex>();
	}
	
	public Map2(ArrayList<Vertex> vertices){
		this.vertices = vertices;
	}
	
	public void addNode(Vertex v){
		if(!(vertices.contains(v))){
			vertices.add(v);
		}
	}
	
	
	public void addEdge(Node a, Node b, boolean isTwoWay)
	{
		Edge e = new Edge();
	}
	
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
	
	public Node[] getList(){
		return list;
	}


	public void setCoord(int index, int x, int y) {
		
		list[index].setCoord(x, y);
		
	}
	
	public int findNode(Node n){
		for(int i = 0; i<list.length; i++){
			if(n == list[i]){
				return i;
			}
		}
		return (Integer) null;
	}

}
