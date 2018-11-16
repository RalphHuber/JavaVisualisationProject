package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**jpanel that runs all the visualisation of algorithms
 * 
 * @author Ralph Sinhuber
 *
 */
public class GraphGUI extends JPanel implements ActionListener{
	
	/*presentation:
	 * introduction: what it is, 
	 * roles assigned,
	 * demo
	 */
	
	
	//speed change
	//heuristics printed
	//print words
	//add arrow
	//make A* work
	//iterative deepening and best first
	//quiz
	//sort out line lengths
	
	//show visited and frontier
	//expand frontier
	//make everything as it should be
	//do one half of line as one way, and the other as the other way
	
	
	
	//make it so guiconnection has two connections or one then base colour on that, if one has been visited then red else blue else black
	
	/*still to do - dont expand frontier when visited (or have instance return a looked at frontier (preferred)), time everything right, colour nodes gold when path is passed through
	*/
	//private JFrame frame;
	private boolean paused;
	private Color grey = new Color(111,111,111);
	private Color green = new Color(142,230,129);
	private Color red = new Color(228,89,89);
	private Color blue = new Color(89,176,228);
	private Color gold = new Color(246,203,101);
	private Map map;
	private int startInt;
	private int finishInt;
	private ArrayList<Instance> instances;
	private ArrayList<GUINode> nodes;
	private GUINode start = null;
	private GUINode finish = null;
	private ArrayList<GUIConnection> conns;
	private ArrayList<ExtendingLine> pathLines = null;
	private ArrayList<ExtendingLine> fLines = null;
	private ExtendingLine vLine = null;
	private MovingCircle circle = null;
	private int index;
	boolean searched = false;
	private Timer timer;
	
	//ExtendingLine line = new ExtendingLine(30,30,500,500);
	
	
	private MouseAdapter mouseListener = new MouseAdapter(){ 
		
		//have a default colour saved
		 public void mouseClicked(java.awt.event.MouseEvent e) {
			 
             if (SwingUtilities.isLeftMouseButton(e)) {
            	 	for(GUINode guiNode: nodes){
            	 		if(guiNode.getNode().isClose(e.getPoint())){
            	 			if(!(start ==null)){
            	 				start.setOutColour(Color.BLACK);
            	 				
            	 			}
            	 			guiNode.setOutColour(gold);
            	 			start = guiNode;
            	 			System.out.println("done");
            	 			startInt = map.findNode(guiNode.getNode());
            	 			System.out.println(""+startInt);
            	 			repaint();
            	 			break;
            	 		}
            	 	}
             }
             else if (SwingUtilities.isRightMouseButton(e)) {
            	 for(GUINode guiNode: nodes){
         	 		if(guiNode.getNode().isClose(e.getPoint())){
         	 			if(!(finish ==null)){
         	 				finish.setOutColour(Color.BLACK);
         	 				
         	 			}
         	 			guiNode.setOutColour(green);
         	 			finish = guiNode;
         	 			System.out.println("done");
         	 			finishInt = map.findNode(guiNode.getNode());
         	 			System.out.println(""+finishInt);
         	 			repaint();
         	 			break;
         	 		}
         	 	}
                 
             }
         }
	};
	
	
	//private ArrayList<GUIConnection> Connection;
	
	//create search interface
	/**constructor
	 * 
	 * @param map map to be visualised
	 */
	public GraphGUI(Map map){
		addMouseListener(mouseListener);
		this.map=map;
		this.setIndex(0);
		buildGraph();
	}
	
	/**
	 * create a graph based on the map information
	 */
	private void buildGraph() {
		nodes = new ArrayList<GUINode>();
		conns = new ArrayList<GUIConnection>();
		Node[] list = map.getList();
		
		for(int i = 0; i<(list.length); i++){
			
			String index = "" + i;
			
			GUINode guiNode = new GUINode(list[i], index);
			
			nodes.add(guiNode);
			
			Connection[] listc = list[i].getConnections();
			
				for(Connection conn: listc){
					if(!(conn.getConnect()==-1)){
					GUIConnection guiConn = new GUIConnection(conn, map);
					conns.add(guiConn);
	
					}
				}
		}
		
		
		
		/*for(GUIConnection gc: conns){
		
			GUIConnection reverse = getReverse(gc);
			if(!(reverse==null)){
				//System.out.println("here");
				gc.setConn2(reverse.getConn());
				}
		}*/
		
		//deleteDuplicates(conns);
		
		for(GUIConnection gc: conns){
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
		
		
		drawing();
		
		
		
	}
	
	/**
	 * set the current graph to its default state
	 */
	public void resetGraph(){
		instances = null;
		start = null;
		finish = null;
		timer.stop();
		timer = null;
		pathLines = null;
		fLines = null;
		vLine = null;
		circle = null;
		index = 0;
		buildGraph();
	}

	/**get the reverse connection
	 * 
	 * @param gc two way connection
	 * @return reverse connection
	 */
	private GUIConnection getReverse(GUIConnection gc) {
		for(GUIConnection gConn: conns){
			if(gc.isReverse(gConn)){
				return gConn;
			}
		}
		return null;
	}


	/**
	 * update the panel after an action
	 */
	public void drawing(){
		repaint();
	}
	
	/**run animations
	 * 
	 * @param g
	 */
	public void draw(Graphics2D g){
		

		for(GUIConnection conn: conns){
			
			conn.draw(g, map);
			
		}
		
		if(!(fLines==null)){
			for(ExtendingLine line: fLines){
				line.draw(g);
			}
		}
		
		if(!(pathLines==null)){
			for(ExtendingLine line: pathLines){
				line.draw(g);
			}
		}
		
		
		
		if(!(vLine==null)){
			
				vLine.draw(g);
			
		}
		
		for(GUINode guiNode: nodes){
			
			guiNode.draw(g);
			
		}
		
		if(!(circle == null)){
			circle.draw(g);;
		}
				
		//repaint();
		
		//change already coloured frontiers
		//after circle moved have final node coloured red
		
		
		//recursively call until done
		
		
		
		
		
		
		//everything thats visited will have a frontier connection
		
		//for loop for conection array list
		 
	}
	/**
	 * use depth first search
	 */
	public void SearchDFS(){
		DFSPath dfs = new DFSPath(map, startInt, finishInt);
		setInstances(dfs.Search(map));
		searched = true;
		updateGraph();	
	}
	
	/**
	 * use breadth first search
	 */
	public void SearchBFS(){
		BFSPath bfs = new BFSPath(map, startInt, finishInt);
		setInstances(bfs.Search(map));
		searched = true;
		updateGraph();	
	}
	
	/**
	 * use greedy algorithms
	 */
	public void SearchGreedy(){
		GreedyPath gfs = new GreedyPath(map, startInt, finishInt);
		setInstances(gfs.Search(map));
		searched = true;
		updateGraph();	
	}
	
	/**
	 * use A* search algorithm
	 */
	public void SearchAStar(){
		ASPath as = new ASPath(map, startInt, finishInt);
		setInstances(as.Search(map));
		searched = true;
		updateGraph();	
	}
	
	/**
	 * runs through a list of instances and runs the visualisation
	 */
	public void updateGraph(){
		//find all lines to be extended
		//add to list
		//create all extending lines
		//create timers for each
		//maintain show as false, until a period of time, then true, and start updating 
		//after delay call draw
		//needs to update extending lines and moving circle aswell
		
		//if expanded, get expanded list
		
		paused = false;

		Instance instance = getInstances().get(getIndex());
		pathLines = new ArrayList<ExtendingLine>();
		fLines = new ArrayList<ExtendingLine>();
		Node current = instance.getCurrent().getNode();
		int x = current.getCoord().getX();
		int y = current.getCoord().getY();
		Node[] n = map.getList();
		
		if(instance.isExpanded()){
			
			for(Connection c: current.getConnections()){
				for(GUIConnection gc: conns){
					if(c==gc.getConn()){
						ExtendingLine e = new ExtendingLine(gc, blue);
						fLines.add(e);
					}
				}
			}
			//get connection list of current
			//find connectionGUI for each connection
			//create extending line for each connection
			//change guiconnection so it has stored coordinates based on layout
			/*for(Connection conn: instance.getCurrent().getNode().getConnections()){
				if(!(conn.getConnect()==-1)){
					int x2 = n[conn.getConnect()].getCoord().getX();
					int y2 = n[conn.getConnect()].getCoord().getY();
					
					ExtendingLine e = new ExtendingLine(x,y,x2,y2, Color.blue);
					
					fLines.add(e);
					
				}
				
			}*/
		}
		
		PathPair p = instance.getCurrent();
		Color pathColour;
		
		if(p.getNode()==finish.getNode()){
			pathColour = green;
		}
		else{
			pathColour = gold;
			System.out.println(p.toString(map) + "path");
		}
		while(!(p.getPrevious() == null)){
			//find connection between previous and current
			//find connection gui of it 
			//create extending line 
			//System.out.println("path");
			Connection temp = map.findConnection(p.getPrevious().getNode(), p.getNode());
			//System.out.println(temp.toString());
			for(GUIConnection gc: conns){
				if(temp == gc.getConn()){
					ExtendingLine e = new ExtendingLine(gc, pathColour);
					pathLines.add(e);
					break;
				}
			}
		
			/*ExtendingLine e = new ExtendingLine(p.getNode().getCoord().getX(),p.getNode().getCoord().getY(), p.getPrevious().getNode().getCoord().getX(), p.getPrevious().getNode().getCoord().getY(), Color.yellow);
			pathLines.add(e);*/
			p = p.getPrevious();
		}
		
		
	
		if((index < instances.size()-1)){
		
		PathPair drawRed = instances.get(index+1).getCurrent();
		
		if(!(drawRed.getPrevious()==null)){
			//find connection between drawRed and previous
			//find conenction gui
			//add connection gui to extending line
			Node prev = drawRed.getPrevious().getNode();
			Connection c = map.findConnection(prev, drawRed.getNode());
			for(GUIConnection conn: conns){
				if(conn.getConn() == c){
					ExtendingLine e = new ExtendingLine(conn, red);
					vLine = e;
				}
			}
			/*ExtendingLine e = new ExtendingLine(drawRed.getNode().getCoord().getX(),drawRed.getNode().getCoord().getY(), drawRed.getPrevious().getNode().getCoord().getX(), drawRed.getPrevious().getNode().getCoord().getY(), Color.red);
			vLine = e;*/
		}
		}
		if(instance.getCurrent().getNode() == finish.getNode()){
			vLine = null;
		}
		
		for(GUINode guiNode: nodes){
			/*if(guiNode.getNode() == instance.getCurrent().getNode()){
				guiNode.setOutColour(Color.yellow);
			}*/
			if(instance.getVisited().contains(guiNode.getNode())){
				guiNode.setOutColour(red);
			}
			else if(instance.getFrontier().contains(guiNode.getNode())){
				guiNode.setOutColour(blue);
			}
			else{
				guiNode.setOutColour(Color.BLACK);
			}
			
		}
		
		//PathPair current = instance.getCurrent();
		/*while(!(current == null)){
			
			for (GUINode guiNode: nodes){
				if(current.getNode() == guiNode.getNode()){
					guiNode.setOutColour(Color.YELLOW);
					break;
				}
			}
			
			current = current.getPrevious();
		}*/
		
		for(GUIConnection conn: conns){
			if(instance.getVisitedE().contains(conn.getConn())/*||instance.getVisitedE().contains(conn.getConn2())*/){
				conn.setColour(red);
			}
			/*else if(instance.getCurrent().getNode().connectionIn(conn.getConn()))){
				conn.setColour(Color.black);
				
			}*/
			else if(instance.getFrontierE().contains(conn.getConn())/*||instance.getFrontierE().contains(conn.getConn2())*/){
				conn.setColour(blue);
			}
			else{
				conn.setColour(Color.BLACK);
			}
			
		}
		if((index < instances.size()-1)){
		
		circle = new MovingCircle(current.getCoord().getX(), current.getCoord().getY(), 
				getInstances().get(getIndex()+1).getCurrent().getNode().getCoord().getX(), getInstances().get(getIndex()+1).getCurrent().getNode().getCoord().getY(), gold);
		}
		else{
			circle = null;
			finish.setOutColour(green);
		}
		
		timer = new Timer(1, this);
		startLines();
		timer.start();
		
		//find frontier from current
		//create extending line for each
		drawing();
		
		
	}
	/**
	 * start drawing line animations
	 */
	private void startLines() {
		for(ExtendingLine l: pathLines){
			l.start();
		}
		for(ExtendingLine l: fLines){
			l.start();
		}
		
	}

	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		
	 
		draw(g2);
		 
	        
	}  
	
	


	   /* public static void main(String[] args) {

	       JFrame frame = new JFrame();  
	        frame.setTitle("Basic shapes");
	        frame.setSize(2000, 500);
	        frame.setLocationRelativeTo(null);        
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	        
	        Map map = new Map(15);
	    	map.addConnectionMap(0, 2, 0, true);
			map.addConnectionMap(0, 1, 0, true);
			map.addConnectionMap(1, 3, 0, true);
			map.addConnectionMap(1, 4, 0, true);
			map.addConnectionMap(2, 5, 0, true);
			map.addConnectionMap(2, 6, 0, true);
			map.addConnectionMap(3, 7, 0, true);
			map.addConnectionMap(3, 8, 0, true);
			map.addConnectionMap(4, 9, 0, true);
			map.addConnectionMap(4, 10, 0, true);
			map.addConnectionMap(5, 11, 0, true);
			map.addConnectionMap(5, 12, 0, true);
			map.addConnectionMap(6, 13, 0, true);
			map.addConnectionMap(6, 14, 0, true);
			//map.addConnectionMap(2, 7, 0, false);
			//map.addConnectionMap(14, 13, 0, true);
			//map.addConnectionMap(13, 7, 0, true);
			map.addConnectionMap(10, 11, 0, true);
			map.setCoord(0, 1000, 100);
			map.setCoord(1, 900, 200 );
			map.setCoord(2, 1100, 200);
			map.setCoord(3, 800, 300);
			map.setCoord(4, 900, 300);
			map.setCoord(5, 1100, 300);
			map.setCoord(6, 1200, 300);
			map.setCoord(7, 500, 400);
			map.setCoord(8, 600, 400);
			map.setCoord(9, 700, 400);
			map.setCoord(10, 800, 400);
			map.setCoord(11, 900, 400);
			map.setCoord(12, 1000, 400);
			map.setCoord(13, 1100, 400);
			map.setCoord(14, 1200, 400);
			
			GraphGUI gui = new GraphGUI(map);
			
			JButton prevButton = new JButton("<");
			prevButton.setBounds(0, 0, 29, 29);
			prevButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					
					if(!(gui.getIndex()==0)){
						gui.setIndex(gui.getIndex() -1);
						System.out.println(gui.getIndex());
						gui.updateGraph();
					}
					
					
				}
			});
			frame.add(prevButton);
			
			
			
			JButton nextButton = new JButton(">");
			nextButton.setBounds(30, 0, 29, 29);
			nextButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					
					if(!(gui.getIndex()== (gui.getInstances().size())-1)){
						gui.setIndex(gui.getIndex() +1);
						System.out.println(gui.getIndex());
						gui.updateGraph();
					}
				}
			});
			
			frame.add(nextButton);
			
			JButton search = new JButton("search");
			search.setBounds(0, 40, 100, 29);
			
			search.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
						
						gui.Search();
						
					}
				});
			
			
			frame.add(search);
	        
	        
	        
	        
	        frame.add(gui);
	        gui.drawing();
	        
	        
	    }*/
		/**
		 * pause the visualisation
		 */
		public void pause(){
			paused = true;
		}
		/**get the value of index
		 * 
		 * @return index
		 */
		public int getIndex() {
			return index;
		}
		
		/**
		 * replace the value of index
		 * @param index new index
		 */
		public void setIndex(int index) {
			this.index = index;
		}

		/**
		 * gets the list of instances after running a search
		 * @return the array of instances output by the last search algorithm
		 */
		public ArrayList<Instance> getInstances() {
			return instances;
		}

		/**update the array of instances with a new array
		 * 
		 * @param instances
		 */
		public void setInstances(ArrayList<Instance> instances) {
			this.instances = instances;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//for evey line if its finished then colour node at coordinates gold or blue(only blue if node isnt already red);
			//check if finished
			//find node
			//check if red 
			//if not change to blue
			
			/*for(ExtendingLine l: pathLines){
				if(l.isFinished()){
					GUINode n = findGUINode(l.getEnd());
					n.setOutColour(Color.yellow);
					
				}
				
			}
			repaint();*/
		if(!(circle==null) && !(vLine==null)){
			
			if(linesFinished()&&!(circle.isRunning())&&!(vLine.isRunning())){
				Instance instance = getInstances().get(getIndex());
				
				if(instance.isExpanded()){
				for(GUINode guiNode: nodes){
					
					if(instance.getExpandedF().contains(guiNode.getNode())){
						guiNode.setOutColour(blue);
					}
				}
				}
				repaint();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				Iterator<ExtendingLine> iter = pathLines.iterator();

				while (iter.hasNext()) {
				    ExtendingLine l = iter.next();

				    if (l.getColor() == gold){
				    	 iter.remove();
				    }
				       
				}
				vLine.start();
				circle.start();
			}
			if(vLine.isFinished()&&circle.finished){
				if(index<instances.size()){
					timer.stop();
					if(!paused){
						index = index +1;
						updateGraph();
					}
					
					
				}
				else{
					timer.stop();
				}

			}
			
			
			
		}
			repaint();
		}
			
		/**find the GUI node at a given point
		 * 
		 * @param end coordinates of the node
		 * @return the node at the given point
		 */
		private GUINode findGUINode(Point2D.Double end) {
			for(GUINode n: nodes){
				if((n.getNode().getCoord().getX()==end.getX()) && (n.getNode().getCoord().getX()==end.getX())){
					return n;
				}
			}
			return null;
			
		}
		
		/**check if thj elinbe animation has finished
		 * 
		 * @return
		 */
		private boolean linesFinished() {
			for(ExtendingLine l: pathLines){
				if(!(l.isFinished())){
					return false;
				}
			}
			for(ExtendingLine l: fLines){
				if(!(l.isFinished())){
					return false;
				}
			}
			
			return true;
		}
		
		/**
		 * set the default map as a new map
		 * @param map2 map to be updated
		 */
		public void setMap(Map map2) {
			this.map = map2;
			//this.resetGraph();
		}

}
