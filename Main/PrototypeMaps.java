package Main;
//I need to javadoc this
public class PrototypeMaps {

    Map[] maps;
    
	public PrototypeMaps()
	{
		maps = new Map[3];
		
		//first map
		Map map1 = new Map(5);
		//set 
		//A to B
		map1.addConnectionMap(0, 1, 7, true);
		//A to D
		map1.addConnectionMap(0, 3, 5, true);
		//B to C
		map1.addConnectionMap(1, 2, 9, true);
		//B to D
		map1.addConnectionMap(1, 3, 6, true);
		//B to E
		map1.addConnectionMap(1, 4, 5, true);
		//D to E
		map1.addConnectionMap(3, 4, 4, true);
		
		//second map
		Map map2 = new Map(6);
		//A to B
		map2.addConnectionMap(0, 1, 4, false);
		//A to C
		map2.addConnectionMap(0, 2, 5, true);
		//B to A
		map2.addConnectionMap(1, 0, 4, false);
		//B to D
		map2.addConnectionMap(1, 3, 3, true);
		//D to E
		map2.addConnectionMap(3, 4, 7, true);
		//E to B
		map2.addConnectionMap(4, 1, 5, false);
		//F to D
		map2.addConnectionMap(5, 3, 6, false);
		
		/*//third map (bst)
		Map bst1 = new Map (9);
		//A to B
		bst1.addConnectionMap(1, 2, 2, false);
		//B to D
		bst1.addConnectionMap(2, 4, 1, false);
		//B to E
		bst1.addConnectionMap(2, 5, 4, false);
		//E to G
		bst1.addConnectionMap(5, 7, 3, false);
		//E to H
		bst1.addConnectionMap(5, 8, 5, false);
		//A to C
		bst1.addConnectionMap(1, 3, 7, false);
		//C to F
		bst1.addConnectionMap(3, 6, 9, false);
		//F to I
		bst1.addConnectionMap(6, 9, 8, false);
		*/
		maps[0] = map1;
		maps[1] = map2;
		//maps[2] = bst1;
		
		
	}
        
        public Map getMap(int i)
        {
            return maps[i];
        }
}
