package Testing;
import static org.junit.Assert.*;

import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Main.*;
public class CreateGraphTest {
	
	CreateGraph create;
	Arrays arrays;
	Map map;

	@Before
	public void setUp() throws Exception {
		 map = new Map(5);
	    	map.addConnectionMap(0, 2, 0, true);
			map.addConnectionMap(0, 1, 0, true);
			map.addConnectionMap(1, 3, 0, true);
			map.addConnectionMap(1, 4, 0, true);
			map.setCoord(0, 1000, 100);
			map.setCoord(1, 900, 200 );
			map.setCoord(2, 1100, 200);
			map.setCoord(3, 800, 300);
			map.setCoord(4, 900, 300);
		
		create = new CreateGraph();
		
	}

	@After
	public void tearDown() throws Exception {
	
	}
	
	@Test
	public void addNodeTest(){
		Map map = new Map(1);
		map.setCoord(0, 100, 100);
		create.addNode(100, 100);
		Map map2 = create.getMap();
		assertTrue(arrays.deepEquals(map.getList(), map2.getList()));
		
	}
	
	
	@Test 
	public void getReverse(){
		create = new CreateGraph();
		create.setMap(map);
		create.buildGraph();
		Connection conn = new Connection(0,2,0,false);
		Connection conn2 = new Connection(2,0,0,false);
		GUIConnection gc2 = new GUIConnection(conn2, map);
		GUIConnection gc = new GUIConnection(conn, map);
		gc2 = create.getReverse(gc2);
		assertEquals(gc2, gc);
		
		
	}
	
	@Test
	public void updateNodes(){
		Node n1 = new Node(2);
		Node n2 = new Node(2);
		n1.addConnectionNode(1, 0, 0, false);
		n2.addConnectionNode(0, 1, 0, false);
		Node n3 = new Node(3);
		Node n4 = new Node(3);
		Node n5 = new Node(3, 1000, 1000);
		n3.addConnectionNode(1, 0, 0, false);
		n4.addConnectionNode(0, 1, 0, false);
		Node[] list = {n1,n2};
		Node[] list2 = {n3,n4,n5};
		create.setNodeNumber(3);
		list = create.updateNodes(list, 1000, 1000);
		assertTrue(arrays.deepEquals(list,list2));
		
	}
	
	@Test
	public void updateConn(){
		Connection one = new Connection(1,2,0,false);
		Connection two = new Connection(1,3,0,false);
		Connection three = new Connection();
		create.setNodeNumber(3);
		Connection[] conns = {one, two};
		System.out.println();
		Connection[] conns2 = {one, two, three};
		assertTrue(arrays.deepEquals(create.updateConn(conns),conns2));
	}
	
	
	

}
