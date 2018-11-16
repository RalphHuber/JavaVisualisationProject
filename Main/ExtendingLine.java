package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**animmations on GUIConnections
 * 
 * @author Ralph Sinhuber
 *
 */
public class ExtendingLine extends JPanel implements ActionListener  {
	
	private Point2D.Double start;
	private Point2D.Double current;
	private Point2D.Double end;
	private Color colour;
	double dx;
	double dy;
	private double gradient;
	boolean xPos;
	boolean yPos;
	private Timer timer;
	boolean finished;
	
	/**Constructor
	 * 
	 * @param x x coordinate of starting point
	 * @param y y coordinate of starting point
	 * @param x2 x coordinate of finishing point
	 * @param y2 y coordinate of finishing point
	 * @param c color of the line
	 */
	public ExtendingLine(int x, int y, int x2, int y2, Color c){
		this.start = new Point2D.Double(x,y);
		this.current = new Point2D.Double(x,y);
		this.end = new Point2D.Double(x2,y2);
		this.dx = x2 - x;
		xPos = dx > 0;
		this.dy = y2 - y;
		yPos = dy>0;
		this.colour = c;
		/*System.out.println(dx);
		System.out.println(dy);
		System.out.println(dy/dx);*/
		finished = false;
		timer = new Timer(1, this);
		//timer.start();
		
	}

	/**constructor
	 * 
	 * @param gc a visual connection
	 * @param c
	 */
	public ExtendingLine(GUIConnection gc, Color c) {
		int x = gc.getStart().getX();
		int y = gc.getStart().getY();
		int x2 = gc.getFinish().getX(); 
		int y2 = gc.getFinish().getY();
		this.start = new Point2D.Double(x,y);
		this.current = new Point2D.Double(x,y);
		this.end = new Point2D.Double(x2,y2);
		this.dx = x2 - x;
		xPos = dx > 0;
		this.dy = y2 - y;
		yPos = dy>0;
		this.colour = c;
		/*System.out.println(dx);
		System.out.println(dy);
		System.out.println(dy/dx);*/
		finished = false;
		timer = new Timer(10, this);
		//timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//round
		//if d
		//if gradient is high then reduce the addition depending on that.
		if(!(Math.round(current.getX()) == end.getX() && Math.round(current.getY()) == end.getY())){
		
		
			if(dx == 0){
				if(yPos){
					current.setLocation(current.getX(), current.getY() +1);
				}
				else{
					current.setLocation(current.getX(), current.getY() -1);
				}
				
			}
			else if(dy ==0){
				if(xPos){
					current.setLocation(current.getX() +1, current.getY());
				}
				else{
					current.setLocation(current.getX() -1, current.getY());
				}
				
			}
			else if(Math.abs(dy/dx)<1){
				if(yPos && xPos){
					current.setLocation(current.getX() +1, current.getY() + (dy/dx));
				}
				else if(yPos){
					current.setLocation(current.getX() - 1, current.getY() - (dy/dx));
				}
				else if(xPos){
					current.setLocation(current.getX() +1, current.getY() + (dy/dx));
				}
				else{
					current.setLocation(current.getX() -1, current.getY() - (dy/dx));
				}
				
			}
			else{
				if(yPos && xPos){
					current.setLocation(current.getX() +(dx/dy), current.getY() + 1);
				}
				else if(yPos){
					current.setLocation(current.getX() + (dx/dy), current.getY() + 1);
				}
				else if(xPos){
					current.setLocation(current.getX() - (dx/dy), current.getY() - 1);
				}
				else{
					current.setLocation(current.getX() - (dx/dy), current.getY() - 1);
				}
			}
		
		}
		else{
			finished = true;
			timer.stop();
		}
		//System.out.println(dx/dy);
		/*System.out.println(current.getX());
		System.out.println(current.getY());*/
		//repaint();
	}
	
	/**perform line animation
	 * 
	 * @param g renderer
	 */
	public void draw(Graphics2D g){
		
		g.setColor(colour);
		g.setStroke(new BasicStroke(5));
		g.draw(new Line2D.Float((int) start.getX(), (int)start.getY(), (int)current.getX(), (int)current.getY()));
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		
	 
		draw(g2);
		 
	        
	} 
	
	 public static void main(String[] args) {

	       JFrame frame = new JFrame();  
	        frame.setTitle("Basic shapes");
	        frame.setSize(2000, 500);
	        frame.setLocationRelativeTo(null);        
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	        //ExtendingLine line = new ExtendingLine(100, 100, 50, 200);
	        //frame.add(line);
	        //when one is positive and one is negative doesnt work
	        //doesnt work if x is negative and y is postive
	        
	  }

	/**checks whether this animation is still ongoing
	 * 
	 * @return 
	 */
	public boolean isRunning() {
		return timer.isRunning();
	}
	
	/**
	 * starts the animation
	 */
	public void start() {
		timer.start();
		
	}

	/**checks for animation to be finished
	 * 
	 * @return finished
	 */
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}
	
	/**get current color of animation
	 * 
	 * @return colour
	 */
	public Color getColor(){
		return colour;
	}
	
	/**gets the coordinates of the and of the connection
	 * 
	 * @return coordinates of anding node
	 */
	public Point2D.Double getEnd(){
		return end;
	}
	

}
