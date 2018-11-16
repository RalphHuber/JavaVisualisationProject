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

import javax.swing.JPanel;
import javax.swing.Timer;

/**animation for the current node pointer
 * 
 * @author Ralph Sinnhuber
 *
 */
public class MovingCircle implements ActionListener{
	
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
	
	/**constructor
	 * 
	 * @param x starting x coordinate
	 * @param y starting y coordinate
	 * @param x2 ending x coordinate
	 * @param y2 ending y coordinate
	 * @param c color of circle
	 */
	public MovingCircle(int x, int y, int x2, int y2, Color c){
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
	
	/**draw the circle
	 * 
	 * @param g
	 */
	public void draw(Graphics2D g){
		
		g.setColor(colour);
		g.setStroke(new BasicStroke(5));
		g.drawOval((int)current.getX() - 50/2, (int)current.getY() - 50/2, 50, 50);
		//g.draw(new Line2D.Float((int) start.getX(), (int)start.getY(), (int)current.getX(), (int)current.getY()));
		
		
	}

	/**check if the animation is running
	 * 
	 * @return
	 */
	public boolean isRunning() {
		return timer.isRunning();
	}

	/**
	 * start the animation
	 */
	public void start() {
		timer.start();
		
	}
	      

}
