package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**functionallity for saving and loading maps
 * 
 * @author Ralph Sinnhuber
 *
 */
public class SaveLoadMap {
	
	/**
	 * constructor
	 */
	public SaveLoadMap(){
		
	}
	
	/**save a map to a file
	 * 
	 * @param m map to be saved
	 */
	public void saveMap(Map m){
		JFileChooser chooser = new JFileChooser();
		int actionValue = chooser.showSaveDialog(null);
		 if ( actionValue == JFileChooser.APPROVE_OPTION ){ 

		 // if here then write out the data - note that we're assuming that the user did place the .ser
		 // extension to the filename, as I was too lazy when writing this code to force the issue...
		 try {
		 FileOutputStream fs = new FileOutputStream( chooser.getSelectedFile() );
		 ObjectOutputStream os = new ObjectOutputStream( fs );
		 os.writeObject(m);
		 os.close();
		 } catch ( Exception e ) { // generic way to just catch everything and assume a bad outcome
		 e.printStackTrace();
		 } 
		/* try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("/tmp/employee.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(m);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /tmp/employee.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }*/
		}
	}
	
	/**load a map from a file
	 * 
	 * @return
	 */
	public Map loadMap(){
		Map m = null;
	      try
	      {
	    	  JFileChooser fileChooser = new JFileChooser();
	          int actionValue = fileChooser.showOpenDialog(null);
	          
	          if (actionValue == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();
	            System.out.println(selectedFile.getName());
	            FileInputStream fileIn = new FileInputStream(selectedFile);
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         m = (Map) in.readObject();
		         in.close();
		         fileIn.close();
		         return m;
	          }
	      return null;
	        
	         
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return null;
	         
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Map not found");
	         c.printStackTrace();
	         return null;
	      }
	}
	

    public static void main(String[] args) {
       
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
		//map.addConnectionMap(7, 4, 0, true);
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
		
		System.out.println(map.getList().length);
		
		SaveLoadMap sl = new SaveLoadMap();
		
		//sl.saveMap(map);
		
		Map m = sl.loadMap();
		
		/*JFrame frame2 = new JFrame();  
        frame2.setTitle("Basic shapes");
        frame2.setSize(2000, 500);
        frame2.setLocationRelativeTo(null);        
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);	
        
        GraphGUI gui = new GraphGUI(map);
        frame2.add(gui);
        gui.drawing();*/
		
		JFrame frame = new JFrame();  
        frame.setTitle("Basic shapes");
        frame.setSize(2000, 500);
        frame.setLocationRelativeTo(null);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);	
        
        GraphGUI gui2 = new GraphGUI(m);
        frame.add(gui2);
        gui2.drawing();
		
        System.out.println(m.getList().length);
		
    }

}
