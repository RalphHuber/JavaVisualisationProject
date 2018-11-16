package Main;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class TestWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestWindow window = new TestWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		
		GraphGUI gui = new GraphGUI(map);
		
		GraphGUI graphGUI = new GraphGUI(map);
		graphGUI.setBounds(6, 80, 296, 192);
		frame.getContentPane().add(graphGUI);
	}
}
