package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**GUI for running visualisations, replaced by Graph GUI
 * 
 * @author Steven Cook
 *
 */
public class MapGUI {

	private JFrame frame;
	private Map map;
	private int start;
	private int finish;
	private ArrayList<Instance> instances;
	private int index;
	//create search interface

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapGUI window = new MapGUI();
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
	public MapGUI(Map map, int start, int finish) {
		initialize();

	}
	
	private void drawMap() {
		
		
	}

	public MapGUI(){
		index = 0;
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
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		searchButton.setBounds(182, 243, 117, 29);
		frame.getContentPane().add(searchButton);
		
		JButton prevButton = new JButton("<");
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(index==0)){
					index=index-1;
				}
			}
		});
		prevButton.setBounds(141, 243, 29, 29);
		frame.getContentPane().add(prevButton);
		
		JButton nextButton = new JButton(">");
		nextButton.setBounds(310, 243, 29, 29);
		frame.getContentPane().add(nextButton);
		
		
		frame.getContentPane().add(new DrawMap());
	}
}
