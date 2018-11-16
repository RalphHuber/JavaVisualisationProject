package Testing;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;


import Main.*;

public class MapUnitTests {

	/* maps to test map class*/
	Map m1 = new Map(3);
	Map m2 = new Map(3);
	Map m3 = new Map(3);
	Map m4 = new Map(10);
	Map m5 = new Map(10);
	Map m6 = new Map(10);
	Map m7 = new Map(1);
	Map m8 = new Map(1);
	Map m9 = new Map(10);
	/*maps for testing node class*/ 
	Node n1 = new Node();
	Node n2 = new Node();
	Node n3 = new Node();
	Node n4 = new Node();
	Node n5 = new Node();
	Node n6 = new Node();
	Node n7 = new Node();
	
	/*node class method tests*/
	@Test
	public void translateTest(){
		//zero tests
		n1.setCoord(0,0);
		n1.translate(0, 0);
		assertEquals(0,n1.getCoord().getX());
		assertEquals(0,n1.getCoord().getY());
		//negative tests
		n2.setCoord(0,0);
		n2.translate(-1, -1);
		assertEquals(-1,n2.getCoord().getX());
		assertEquals(-1,n2.getCoord().getY());
		
		//expected tests
		n3.setCoord(0,0);
		n3.translate(10, 10);
		assertEquals(10,n3.getCoord().getX());
		assertEquals(10,n3.getCoord().getY());
		
	}
	@Test
	public void isCloseTest(){
		n4.setCoord(100,100 );
		Point p1 = new Point(101,101);
		assertEquals(true,n4.isClose(p1));
		
		n5.setCoord(0,0);
		Point p2 = new Point(-1,-1);
		assertEquals(true,n5.isClose(p2));
		
		n6.setCoord(100,100);
		Point p3 = new Point(105,105);
		assertEquals(false,n5.isClose(p3));
		
		n7.setCoord(100,100 );
		Point p4 = new Point(106,106);
		assertEquals(true,n7.isClose(p4));
	}
	
	/*Map class method tests*/
	
	@Test
	public void addConnectionMapTest(){
		m1.addConnectionMap(0, 1, 300, false);
		assertEquals("(0, 1)",m1.getList()[0].getConnections()[1].toString());
		assertEquals(300,m1.getList()[0].getConnections()[1].getLength());
		
		m2.addConnectionMap(0, 1, -300, false);
		assertEquals("(0, 1)",m2.getList()[0].getConnections()[1].toString());
		assertEquals(-300,m2.getList()[0].getConnections()[1].getLength());
		
		m3.addConnectionMap(0, 1, 0, false);
		assertEquals("(0, 1)",m3.getList()[0].getConnections()[1].toString());
		assertEquals(0,m3.getList()[0].getConnections()[1].getLength());
	}
	@Test
	public void findConnectiontTest(){
		m4.addConnectionMap(0, 1, 2, false);
		assertEquals("(0, 1)",m4.findConnection(m4.getList()[0], m4.getList()[1]).toString());
		
		m5.addConnectionMap(5, 6, 2, false);
		assertEquals("(5, 6)",m5.findConnection(m5.getList()[5], m5.getList()[6]).toString());
		
		m6.addConnectionMap(9, 1, 2, false);
		assertEquals("(9, 1)",m6.findConnection(m6.getList()[9], m6.getList()[1]).toString());
	}
	@Test
	
	public void findNodeTest() {
		Node s = new Node();
		m7.getList()[0] = s;
		assertEquals(0, m7.findNode(s));
		
		m9.getList()[8] = s;
		assertEquals(8, m9.findNode(s));
		
	}
		
}
