package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//if only one draw in middle with arrow
//if two then change that
//when building a graph check if there is a reverse, if there is then update both guiConnections
//have connections draw arrow in the middle

/**Environment for creating graphs for visualisation
 *
 * @author Ralph Sinnhuber
 *
 */
public class CreateGraph extends JPanel {

		//private ArrayList<Node> nodes = new ArrayList<Node>();

		Map map = new Map(0);

		private static final Font FONT = new Font("Arial", Font.PLAIN, 12);

		int nodeNumber;



		//private boolean clicked = true;

		private ArrayList<GUINode> guiNodes;
		private ArrayList<GUIConnection> guiConns;
		private ArrayList<Triangle> arrows;

    	private MouseAdapter mouseListener = new MouseAdapter(){

    		private Node dragged;
    		private Point lastLocation;
    		private Node selected;
    		int clickNumber;


    		  @Override
              public void mousePressed(java.awt.event.MouseEvent e) {
                  for (Node node : map.getList()) {
                      if (node.isClose(e.getPoint())) {
                          dragged = node;
                          lastLocation = e.getPoint();
                          break;

                      }
                  }
              }

              @Override
              public void mouseDragged(java.awt.event.MouseEvent e) {
                  if (dragged != null) {
                      dragged.translate(e.getX() - lastLocation.x, e.getY() - lastLocation.y);
                      lastLocation = e.getPoint();
                      buildGraph();
                  }
              }

              @Override
              public void mouseReleased(java.awt.event.MouseEvent e) {
                  dragged = null;
                  lastLocation = null;
              }

              @Override
              public void mouseClicked(java.awt.event.MouseEvent e) {
                  if (SwingUtilities.isLeftMouseButton(e)) {

                      if (e.getClickCount() == 1) {
                    	 Node n = clickNode(e.getPoint());
                    	  if(n == null){
                    		  if(clickNumber==0){
                    			  addNode(e.getX(), e.getY());
                    		  }
                    		  clickNumber = 0;

                    	  }
                    	  else{
                    		  if(clickNumber==0){
                    			  selected = n;
                    			  clickNumber = 1;
                    		  }
                    		  else if(clickNumber == 1){
                    			  map.addConnectionMap(map.findNode(selected), map.findNode(n), 0, false);
                    			  clickNumber = 0;
                    			  buildGraph();
                    			  //repaint();
                    		  }
                    	  }



                      }

                      /*else if (e.getClickCount() == 2) {

                      }
                  } else if (SwingUtilities.isRightMouseButton(e)) {

                  }*/
              }
              }

			private Node clickNode(Point point) {
				for (Node node : map.getList()) {
                    if (node.isClose(point)){
                        return node;

                    }
                }
				return null;
			}


    	};

    	/**add a node to the map
    	 *
    	 * @param x x coordinate of new node
    	 * @param y y coordinate of new node
    	 */
    	private void addNode(int x, int y) {
    		//update all nodes with an extra connection;
    		//create new map with nodes
			nodeNumber = nodeNumber +1;
    		Node[] newN = updateNodes(map.getList(), x, y);

    		//have to change other nodes connections aswell
    		map = new Map(newN);
    		buildGraph();
    		//repaint();

		}

    	/**
    	 * create a graph visually
    	 */
    	private void buildGraph() {

    		guiNodes = new ArrayList<GUINode>();
			guiConns = new ArrayList<GUIConnection>();

    		Node[] list = map.getList();

    		for(int i = 0; i<(list.length); i++){

    			String index = "" + i;

    			GUINode guiNode = new GUINode(list[i], index);

    			guiNodes.add(guiNode);

    			Connection[] listc = list[i].getConnections();

    				for(Connection conn: listc){
    					if(!(conn.getConnect()==-1)){
    					GUIConnection guiConn = new GUIConnection(conn, map);

    					guiConns.add(guiConn);
    					}
    				}
    		}
    		//for every guiconnection if theres a reverse, of it and the reverse isnt positvie, make it positve,else negative
    		for(GUIConnection gc: guiConns){
    				//System.out.println("here");
    				LineLayout gcLay = gc.getLayout();

    			if(gcLay==LineLayout.NONE){
    				GUIConnection reverse = getReverse(gc);
        			if(!(reverse==null)){
        				//System.out.println("here");
        				gc.setLayout(LineLayout.POSITIVE);
        				reverse.setLayout(LineLayout.NEGATIVE);
        				}
        			}
    			//System.out.println(gc.getLayout().toString());
    			}

    		//System.out.println(guiConns.toString());
    		repaint();

    	}

    	/** checks if a GUIconnection is reversed
    	 *
    	 * @param gc GUIconnectiion to be checked
    	 * @return the same connection if it is reversed and null if not
    	 */
    	private GUIConnection getReverse(GUIConnection gc) {
			for(GUIConnection gConn: guiConns){
				if(gc.isReverse(gConn)){
					return gConn;
				}
			}
			return null;
		}

    	/**create a new node for the node list
    	 *
    	 * @param list list of nodes to be updated
    	 * @param x x coordinate of new node
    	 * @param y y coordinate of new node
    	 * @return updated node list
    	 */
		private Node[] updateNodes(Node[] list, int x, int y) {
			Node[] n = new Node[nodeNumber];
			for(int i = 0; i<list.length; i++){
				n[i] = new Node(updateConn(list[i].getConnections()), list[i].getCoord());
			}
			n[nodeNumber-1] = new Node(nodeNumber, x, y);
			return n;
		}

		/** add a new connection to the graph
		 *
		 * @param conns old connection list
		 * @return updated connection list
		 */
		private Connection[] updateConn(Connection[] conns){
			Connection[] c = new Connection[nodeNumber];

			for(int i = 0; i<conns.length; i++){
				c[i] = (conns[i]);
			}
			c[nodeNumber - 1] = new Connection();

			return c;




		}

		/**draw the map on to the panel
		 *
		 * @param g the graphics renderer
		 */
		public void draw(Graphics2D g){

			Node[] nodes = map.getList();

			System.out.println(guiConns.toString());

			for(GUIConnection conn: guiConns){
				//System.out.println("here");
				conn.draw(g, map);

			}
			System.out.println("done");

			for(GUINode guiNode: guiNodes){

				guiNode.draw(g);

			}

			/*for(Node node: nodes){

				int nodeX = node.getCoord().getX();
				int nodeY = node.getCoord().getY();

				 g.fillOval(nodeX-(50/2), nodeY-(50/2), 50, 50);

				 Connection[] connections = node.getConnections();

				 for(Connection connection: connections){
					 int connectsTo = connection.getConnect();

					 if(!(connectsTo==-1)){

						 Coord to = nodes[connectsTo].getCoord();


			                g.setStroke(new BasicStroke(5));
			                g.draw(new Line2D.Float(nodeX, nodeY, to.getX(), to.getY()));
					 }

				 }


			}*/
    	}


    		public CreateGraph() {
    			guiNodes = new ArrayList<GUINode>();
				guiConns = new ArrayList<GUIConnection>();
    				addMouseListener(mouseListener);
    	            addMouseMotionListener(mouseListener);
    	            nodeNumber = 0;
    	        }

    		public void setMap(Map m){
    			this.map = m;
    			buildGraph();
    		}

    		 @Override
    	        protected void paintComponent(Graphics g) {
    	            super.paintComponent(g);

    	            /*g.setColor(Color.GRAY);
    	            for(Node node: nodes){
    	    			int nodeX = node.getCoord().getX();
    	    			int nodeY = node.getCoord().getY();
    	    			g.fillOval(nodeX - 50/2, nodeY - 50/2, 50, 50);
    	    		}*/


    	    		Graphics2D g2 = (Graphics2D) g;
    	    		//super.paintComponent(g2);


    	    		draw(g2);

    	        }





        

    	/**
    	 * resets the frame to nothing
    	 */
		public void reset() {
			map = new Map(0);
	        nodeNumber = 0;
	        buildGraph();

		}
    	}
