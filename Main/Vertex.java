package Main;
import java.util.ArrayList;

public class Vertex {
	
	private ArrayList<Edge> neighbours;
	Coord coord = new Coord();
	
	public Vertex(int x, int y){
		coord = new Coord(x,y);
		neighbours = new ArrayList<Edge>();
		
	}
	public int distanceFrom(Vertex b) {
		int xLength = (coord.getX()) - (b.getCoord().getX());
		int yLength = (coord.getY()) - (b.getCoord().getY());

		int length = (int) Math.sqrt((xLength*xLength)+(yLength*yLength));

		return length;
	}
	
	private Coord getCoord() {
		return coord;
	}

}
