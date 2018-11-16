package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JLabel;

/**graphic representation of a node
 * 
 * @author Ralph Sinnhuber
 *
 */
public class GUINode {
		private Node node;
		private String index;
		private Color outer;
		private Color inner;
		private int size;
		private JLabel text = new JLabel();
		
		/**constructor
		 * 
		 * @param node node to be modelled
		 */
		public GUINode(Node node){
			this.node = node;
			outer = Color.BLACK;
			inner = Color.WHITE;
			size = 50;
	
		}
		
		/**constructor
		 * 
		 * @param node node to be modeled
		 * @param index node index
		 */
		public GUINode(Node node, String index){
			this.node = node;
			outer = Color.BLACK;
			inner = Color.WHITE;
			this.index = index;	
		}
		
		/**set color of highlight
		 * 
		 * @param c new color
		 */
		public void setOutColour(Color c){
			outer = c;
		}

		/**get the current color of the node
		 * 
		 * @return
		 */
		public Color getOutColour() {
			// TODO Auto-generated method stub
			return outer;
		}

		/**get the node this class is modelling
		 * 
		 * @return
		 */
		public Node getNode() {
			// TODO Auto-generated method stub
			return node;
		}
		
		/**
		 * draw the outline
		 * @param g
		 */
		public void draw(Graphics2D g){
			int nodeX =node.getCoord().getX();
			int nodeY = node.getCoord().getY();
			g.setStroke(new BasicStroke(5));
			g.setColor(inner);
			g.fillOval(nodeX - 50/2, nodeY - 50/2, 50, 50);
			g.setColor(outer);
			g.drawOval(nodeX - 50/2, nodeY - 50/2, 50, 50);
			g.drawString(index, nodeX, nodeY);
		
		}
}
