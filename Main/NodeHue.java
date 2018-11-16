package Main;

/**Calculates the heuristics of a given path
 * 
 * @author Ralph Sinnhuber
 *
 */
public class NodeHue {

		
		private PathPair path;
		private int  fromGoal;
		private int  fromStart;
		private int  fullLength;
		
		/**get the path the heuristics are calculated from
		 * 
		 * @return
		 */
		public PathPair getPathPair() {
			return path;
		}
		/**set a new path to calculate heuristics of
		 * 
		 * @param path
		 */
		public void setPathPair(PathPair path) {
			this.path = path;
		}
		/**get the distance from the goal node
		 * 
		 * @return
		 */
		public int getFromGoal() {
			return fromGoal;
		}
		/**
		 * get the distance from the start node
		 * @return
		 */
		public int getFromStart() {
			return fromStart;
		}
		/**get the full length
		 * 
		 * @return
		 */
		public int getFLength() {
			return fullLength;
		}
		
		/**set the length from the goal node
		 * 
		 * @param gLength new length
		 */
		public void setFromGoal(int gLength) {
			this.fromGoal = gLength;
			
		}
		/**
		 * set the new full length
		 * @param fLength new length
		 */
		public void setFLength(int fLength) {
			this.fullLength = fLength;
		}
		/**set the distance from starting node
		 * 
		 * @param hLength new distance
		 */
		public void setFromStart(int hLength) {
			this.fromStart = hLength;
		}
		
		/**set the node to be analysed
		 * 
		 * @param node
		 */
		public void setNode(Node node) {
			path.setNode(node);
			
		}
		/**get the analysed node
		 * 
		 * @return
		 */
		public Node getNode() {
		
			return path.getNode();
		}
		


	
}
