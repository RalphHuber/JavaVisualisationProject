package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/** a visual representation of a line
 * 
 * @author Ralph Sinnhuber
 *
 */
public class GUIConnection {
	
	private Connection conn;
	//private Connection conn2;
	private String length;
	private Color Colour;
	private  LineLayout layout;
	private Coord start;
	private Coord finish;
	private Map map;
	
		/**constructor
		 * 
		 * @param conn connection to be modelled
		 * @param map map connection is from
		 */
		public GUIConnection(Connection conn, Map map){
			this.conn = conn;
			this.map = map;
			Colour = Colour.BLACK;
			layout = LineLayout.NONE;
			Node nodeFrom = (map.getList())[conn.getFrom()];
			Node nodeTo = (map.getList())[conn.getConnect()];
			start = new Coord(nodeFrom.getCoord().getX(),nodeFrom.getCoord().getY());
			finish = new Coord(nodeTo.getCoord().getX(),nodeTo.getCoord().getY());
			length = ""+start.DistanceBetween(finish);
			
			
			
		}

		/**get the color of the line
		 * 
		 * @return the color
		 */
		public Color getColour() {
			return Colour;
		}

		/**change the color of the line
		 * 
		 * @param colour
		 */
		public void setColour(Color colour) {
			Colour = colour;
		}
		
		/**get the connection accociated with this class
		 * 
		 * @return the connection
		 */
		public Connection getConn(){
			return conn;
		}
		
		/**get the layout of this line
		 * 
		 * @return
		 */
		public LineLayout getLayout(){
			return layout;
		}
		
		/**get the starting point of the animation
		 * 
		 * @return
		 */
		public Coord getStart(){
			return start;
		}
		
		/**get the ending point of the animation
		 * 
		 * @return
		 */
		public Coord getFinish(){
			return finish;
		}
		/**
		 * change the current layout
		 * @param l new layout
		 */
		public void setLayout(LineLayout l){
			this.layout = l;
			
			int changeX;
			int changeY;
			
			double dy = (start.getY())-(finish.getY());
			
			double dx = (start.getX())-(finish.getX());
			
			if(dy == 0){
				changeX = 0;
				changeY = 5;
			}
			else if(dx==0){
				changeX= 5;
				changeY = 0;
			}
			else{
				double gradient = dy/dx;
				//System.out.println(gradient);
				double pGradient = -1/gradient;
				double angle = Math.toDegrees(Math.atan(pGradient));
				//System.out.println(angle);
				changeX = (int) Math.round(5 * Math.sin(angle));
				//System.out.println(changeX);
				changeY = (int) Math.round(5 * Math.cos(angle));
				//System.out.println(changeY);
				
			}
			
			
			
			switch(l){
			case POSITIVE:{
				start.setX(start.getX()+changeX);
				finish.setX(finish.getX()+changeX);
				start.setY(start.getY()+changeY);
				finish.setY(finish.getY()+changeY);
				 break;	}
				
			case NEGATIVE:{
				//find gradient
				//find perpendicular
				//transate length 10 up or down line As + Bs = Cs
				start.setX(start.getX()-changeX);
				finish.setX(finish.getX()-changeX);
				start.setY(start.getY()-changeY);
				finish.setY(finish.getY()-changeY);
				break;}
			
				
			}
		}
		
		/*public void setConn2(Connection c){
			conn2 = c;
		}*/
		/**draw the animation
		 * 
		 * @param g renderer
		 * @param map map to be drawn
		 */
		public void draw(Graphics2D g, Map map){
		//change connection class
			
		
			Node nodeFrom = (map.getList())[conn.getFrom()];
			Node nodeTo = (map.getList())[conn.getConnect()];
			
			//System.out.println(layout.toString());
			Coord coordF = nodeFrom.getCoord();
			Coord coordTo = nodeTo.getCoord();
			
			g.setColor(Colour);
			g.setStroke(new BasicStroke(4));
			
			int stringX = (coordF.getX() + coordTo.getX())/2;

			int stringY = (coordF.getY() + coordTo.getY())/2;
			
			g.drawString(length, stringX, stringY);
			
			
			
			g.drawLine(start.getX(), start.getY(), finish.getX(), finish.getY());
			
			
			
			
			/*switch(layout){
			case POSITIVE:{
				//System.out.println("1");
				 g.drawLine(nodeFrom.getCoord().getX(), nodeFrom.getCoord().getY()+5, nodeTo.getCoord().getX(), nodeTo.getCoord().getY()+5);
				 break;	}
				
			case NEGATIVE:{
				//System.out.println("2");
				//System.out.println("here");
				g.drawLine(nodeFrom.getCoord().getX(), nodeFrom.getCoord().getY()-5, nodeTo.getCoord().getX(), nodeTo.getCoord().getY()-5);
				break;}
				//g.draw(new Line2D.Float(nodeFrom.getCoord().getX(), nodeFrom.getCoord().getY(), nodeTo.getCoord().getX(), nodeTo.getCoord().getY()));
			case NONE:{
				//System.out.println("3");
				g.drawLine(nodeFrom.getCoord().getX(), nodeFrom.getCoord().getY(), nodeTo.getCoord().getX(), nodeTo.getCoord().getY());
				
				break;
				//g.draw(new Line2D.Float(nodeFrom.getCoord().getX(), nodeFrom.getCoord().getY(), nodeTo.getCoord().getX(), nodeTo.getCoord().getY()));}
			}
			}*/
			
			//System.out.println(this.toString());
			

			//g.draw(new Line2D.Float(nodeFrom.getCoord().getX(), nodeFrom.getCoord().getY(), nodeTo.getCoord().getX(), nodeTo.getCoord().getY()));
           
		}
		

		/**check if this connection is to be reversed
		 * 
		 * @param gConn
		 * @return
		 */
		public boolean isReverse(GUIConnection gConn) {
			if((conn.getConnect() == gConn.getConn().getFrom())&&(conn.getFrom() == gConn.getConn().getConnect())){
				return true;
			}
			else{
				return false;
			}
			
		}

		/*public Object getConn2() {
			// TODO Auto-generated method stub
			return conn2;
		}*/
		@Override
		public boolean equals(Object o){
			return(conn.equals(((GUIConnection) o).getConn()));
		}

}
