package Main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**Written to convert a Map type into A visualisation graph, replaced by ralph's code
 * 
 * @author Steven Cook
 *
 */
public class MapImage extends JPanel{
	Map m;

	MapImage(Map m1){
		setPreferredSize( new Dimension(400,400));
		m = m1;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString("hello", 20, 20);
		buildVisPanel(g);
	}

	private void buildVisPanel(Graphics g) {
		
		addCons(g);
		addNodes(g);
		
		
	}

	private void addCons(Graphics g) {
		for(int i = 0; i<m.list.length; i++){
			for(int j = 0; j<m.list[i].connectionList.length ; j++){
				if(!m.list[i].connectionList[j].isEmpty && !m.list[i].connectionList[j].getVisited()){
					g.setColor(Color.gray);
					int x = m.list[m.list[i].connectionList[j].getConnect()].getX();
					int y = m.list[m.list[i].connectionList[j].getConnect()].getY();
					int midX = (x+m.list[i].getX())/2;
					int midY = (y+m.list[i].getY())/2;
					g.drawLine(m.list[i].getX(), m.list[i].getY(),x, y);
					g.drawString(""+m.list[i].connectionList[j].connectionLength, midX, midY);
				}
			}
		}
		
	}

	private void addNodes(Graphics g) {
		for(int i =0; i<m.list.length; i++){
			g.setColor(Color.black);
			g.drawRect(m.list[i].getX(), m.list[i].getY(), 10, 10);
			g.drawString(""+i, m.list[i].getX(), m.list[i].getY());
			m.list[i].setVisited(true);
		}
	}
	
	/*public MapImage(Map m1){
		m = m1;
		nodes = new JButton[m.list.length];
		// create a list of buttons
		for(int i = 0; i<m.list.length; i++){
			nodes[i] = new JButton();
		}
		p = this;
		
	}
	public JPanel buildVisPanel(){
		p = new MapImage(m){
			 public void paintComponent(Graphics g){
				 p.setSize(650, 650);
				 g.setColor(Color.black);
				// add map nodes to panel
				for(int i = 0; i<m.list.length; i++){
					drawNode(i,p,m,g);
					m.list[i].setVisited(true);
				}
				
				// add connections to panel
				
				for(int i = 0; i<m.list.length; i++){
					for(int j =0; j<m.list[i].connectionList.length;j++){
						addConnection(i,m.list[i].connectionList[j],p,m,g);
						m.setConnectionsVisited(j, i, true);
					}
				}
			}
		};
		
		return p;
	}
	
	
	
	private void drawNode(int i, JPanel p, Map m, Graphics g) {
		if(!m.list[i].getVisited()){
			 g.drawRect(m.list[i].getX(), m.list[i].getY(), 20, 20);
			 JLabel indexEntry = new JLabel("" +i);
			 p.add(indexEntry ,m.list[i].getX(),m.list[i].getY());
		}
	}
	
	private void addNode(int i, JPanel p,Map m){
		if(!m.list[i].getVisited()){
			//create a new node in the nodes array 
			String x = "" + i ;
			nodes[i] = new JButton(x);
			
			//create menu objects and listeners
			JMenu popup = new JMenu("");
			
			
			JMenuItem setStart = new JMenuItem("Set as start node");
			setStart.addActionListener(new ActionListener()
			{
			    public void actionPerformed(ActionEvent e)
			    {
			        // set start node to current node
			    }
			});
			
			JMenuItem setFinish = new JMenuItem("Set as finish node");
			setFinish.addActionListener(new ActionListener()
			{
			    public void actionPerformed(ActionEvent e)
			    {
			       // set finish node to current node
			    }
			});
			
			//construct menu
			popup.add(setStart);
			popup.add(setFinish);
			//attach menu to button
			nodes[i].add(popup);
			
			//add a listener to the button, on click set menu visibility true
			nodes[i].addActionListener(new ActionListener()
			{
			    public void actionPerformed(ActionEvent e)
			    {
			        popup.setVisible(true);
			    }
			});
		
			
			//place node on the panel at corresponding pixels as long as its not blocked
			p.add(nodes[i],m.list[i].getX(), m.list[i].getY());
		}
	}
	
	void addConnection(int n, Connection c,JPanel p,Map m,Graphics g){
		if(!c.isEmpty&&!c.getVisited()){
			// find the starting node
			int start = n;
			int finish;
			// find ending node
			
				finish =c.connectsTo;
				//System.out.println(""+finish);
			
			
			// calculate midpoint 
			int midx =(m.list[start].getX()+m.list[finish].getX())/2;
			int midy =(m.list[start].getY()+m.list[finish].getY())/2;
			
			// find direction
			if(c._isTwoWay){
				// draw line to end node
				g.drawLine(m.list[start].getX(), m.list[start].getY(), m.list[finish].getX(), m.list[finish].getY());
			}
			else{
				// draw line to end node
				g.drawLine(m.list[start].getX(), m.list[start].getY(), m.list[finish].getX(), m.list[finish].getY());
				//draw arrowhead
			}
			
			 JLabel indexEntry = new JLabel("" + c.connectionLength);
			 p.add(indexEntry ,midx, midy);
			// add length to midpoint if not obstructed
			//draw box
			//add length into box
		}
	}*/
}

