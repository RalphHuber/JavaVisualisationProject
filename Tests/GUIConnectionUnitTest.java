package Testing;
import static org.junit.Assert.*;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Main.*;

public class GUIConnectionUnitTest {
	
	private GUIConnection gc ;
	private GUIConnection gc2 ;
	Map map;


	@Before
	public void setUp() throws Exception {
		Node one = new Node(2);
		Node two = new Node(2);
		Node[] nodes = {one, two};
		
		Map map = new Map(nodes);
		
		map.addConnectionMap(0, 1, 0, false);
		map.addConnectionMap(1, 0, 0, false);
		map.setCoord(0, 0, 0);
		map.setCoord(1, 200, 200);
		
		Connection conn = map.findConnection(one, two);
		Connection conn2 = map.findConnection(two, one);
		
		conn.setFrom(0);
		gc = new GUIConnection(conn, map);
		gc2 = new GUIConnection(conn2, map);
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void testSetLayout() {
		
		fail("Not yet implemented");
	}*/

	@Test
	public void testIsReverse() {
		assertTrue(gc.isReverse(gc2)); 
	}

}
