package Main;

/**an improved version of connection, not completed due to lack of time
 * 
 * @author Ralph Sinnhuber
 *
 */
public class Edge {
	
	Vertex a, b;
	int weight;
	
	public Edge(Vertex a, Vertex b){
		this.a = a;
		this.b = b;
		weight = a.distanceFrom(b);
	}
	
	
	
	
}
