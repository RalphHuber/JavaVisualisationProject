package Main;
/**saves a list of maps for visualisation, outdated implementation of map creation
 * 
 * @author Izzi Johnson
 *
 */
public class MapCreator {

	Map[] mapList;
	String[] mapNames;
	
	public MapCreator()
	{
		mapList = new Map[10];
		for(int i = 0; i<10; i++){
			mapList[i] = new Map(1);
		}
		mapNames = new String[10];
		mapNames[0] = "Map1";
		mapNames[1] = "Map2";
		mapNames[2] = "Map3";
		mapNames[3] = "Map4";
		mapNames[4] = "Map5";
		mapNames[5] = "Map6";
		mapNames[6] = "Map7";
		mapNames[7] = "Map8";
		mapNames[8] = "Map9";
		mapNames[9] = "Map10";
	}
	
	public void addMap(int numNodes, String name, int listPos)
	{	
		//System.out.println(""+1);
		mapList[listPos] = new Map(numNodes);
		//System.out.println(""+2);
		mapNames[listPos] = name;
		//System.out.println(""+3);
	}
	
	public void addConnection(int listPos, int from, int to, int length, Boolean isTwoWay)
	{
		mapList[listPos].addConnectionMap(from, to, length, isTwoWay);
	}
	
	public Map getMap(int i)
	{
		return mapList[i];
	}
	public void setMap(int i, Map m){
		mapList[i] = m;
	}
}
