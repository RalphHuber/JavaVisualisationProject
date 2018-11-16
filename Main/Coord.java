package Main;

import java.io.Serializable;

/**Enumeration classs for node coordinates
 * 
 * @author Ralph Sinhuber
 *
 */
public class Coord implements Serializable{
	private int x;
	private int y;
	
	/**Constructor
	 * 
	 * @param x initial x coordinate
	 * @param y initial y coordinate
	 */
	public Coord(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**create an empty coordinate
	 * 
	 */
	public Coord(){
		this.x = 0;
		this.y = 0;
	}
	
	/**change the x position
	 * 
	 * @param x new x coordinate
	 */
	public void setX(int x){
		this.x = x;
	}
	/**change the y position
	 * 
	 * @param y new y coordinate
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**find the x position of a coordinate
	 * 
	 * @return x position of coordinate
	 */
	public int getX(){
		return x;
	}
	
	/**find the y position of a coordinate
	 * 
	 * @return y position of coordinate
	 */
	public int getY(){
		return y;
	
	}
	
	/**findd the distance between two coordinates
	 * 
	 * @param finish coordinate to be checked against this one
	 * @return the absolute value of the distance
	 */
	public int DistanceBetween(Coord finish){
		int xLength = (getX()) - (finish.getX());
		int yLength = (getY()) - (finish.getY());

		int length = (int) Math.sqrt((xLength*xLength)+(yLength*yLength));

		return length;
	}
	
}
