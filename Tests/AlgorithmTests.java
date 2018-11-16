package Testing;

import static org.junit.Assert.*;

import org.junit.Test;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Main.*;

public class AlgorithmTests {
	
	Map m1 = new Map(2);
	

	@Test
	public void BFSPathSearchTest() {
		
		m1.getList()[0].setCoord(1, 1);;
		BFSPath b1 = new BFSPath(m1,0,1);
		ArrayList<Instance> l1 = b1.Search(m1);
		assertEquals(1,l1.get(0).getVisited().get(0).getCoord().getX());
		assertEquals(true,l1.get(0).getVisited().get(0).getConnections()[0].getIsEmpty());
		
		m1.getList()[0].setCoord(10, 10);
		m1.getList()[1].setCoord(20, 20);
		m1.getList()[0].addConnectionNode(1, 20, false);
		BFSPath b2 = new BFSPath(m1,0,1);
		ArrayList<Instance> l2 = b2.Search(m1);
		assertEquals(10,l2.get(0).getVisited().get(0).getCoord().getX());
		assertEquals(false,l2.get(0).getVisited().get(0).getConnections()[1].getIsEmpty());
		assertEquals(20,l2.get(0).getVisited().get(0).getConnections()[1].getLength());
		
	}
	
	@Test
	public void DFSPathSearchTest() {
		m1.getList()[0].setCoord(1, 1);;
		DFSPath d1 = new DFSPath(m1,0,1);
		ArrayList<Instance> l1 = d1.Search(m1);
		assertEquals(1,l1.get(0).getVisited().get(0).getCoord().getX());
		assertEquals(true,l1.get(0).getVisited().get(0).getConnections()[0].getIsEmpty());
		
		m1.getList()[0].setCoord(10, 10);
		m1.getList()[1].setCoord(20, 20);
		m1.getList()[0].addConnectionNode(1, 20, false);
		DFSPath d2 = new DFSPath(m1,0,1);
		ArrayList<Instance> l2 = d2.Search(m1);
		assertEquals(10,l2.get(0).getVisited().get(0).getCoord().getX());
		assertEquals(false,l2.get(0).getVisited().get(0).getConnections()[1].getIsEmpty());
		assertEquals(20,l2.get(0).getVisited().get(0).getConnections()[1].getLength());
	}
	
	@Test
	public void GreedyPathSearchTest() {
		m1.getList()[0].setCoord(1, 1);;
		GreedyPath g1 = new GreedyPath(m1,0,1);
		ArrayList<Instance> l1 = g1.Search(m1);
		assertEquals(1,l1.get(0).getVisited().get(0).getCoord().getX());
		assertEquals(true,l1.get(0).getVisited().get(0).getConnections()[0].getIsEmpty());
		
		m1.getList()[0].setCoord(10, 10);
		m1.getList()[1].setCoord(20, 20);
		m1.getList()[0].addConnectionNode(1, 20, false);
		GreedyPath g2 = new GreedyPath(m1,0,1);
		ArrayList<Instance> l2 = g2.Search(m1);
		assertEquals(10,l2.get(0).getVisited().get(0).getCoord().getX());
		assertEquals(false,l2.get(0).getVisited().get(0).getConnections()[1].getIsEmpty());
		assertEquals(20,l2.get(0).getVisited().get(0).getConnections()[1].getLength());
	}
	
	@Test
	public void ASPathSearchTest() {
		m1.getList()[0].setCoord(1, 1);;
		ASPath a1 = new ASPath(m1,0,1);
		ArrayList<Instance> l1 = a1.Search(m1);
		assertEquals(1,l1.get(0).getVisited().get(0).getCoord().getX());
		assertEquals(true,l1.get(0).getVisited().get(0).getConnections()[0].getIsEmpty());
		
		m1.getList()[0].setCoord(10, 10);
		m1.getList()[1].setCoord(20, 20);
		m1.getList()[0].addConnectionNode(1, 20, false);
		ASPath a2 = new ASPath(m1,0,1);
		ArrayList<Instance> l2 = a2.Search(m1);
		assertEquals(10,l2.get(0).getVisited().get(0).getCoord().getX());
		assertEquals(false,l2.get(0).getVisited().get(0).getConnections()[1].getIsEmpty());
		assertEquals(20,l2.get(0).getVisited().get(0).getConnections()[1].getLength());
	}
}
