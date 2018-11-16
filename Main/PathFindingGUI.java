package Main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**The main GUI for users to interact with
 * 
 * @author Wan Ikmal Fitri
 *
 */
public class PathFindingGUI extends javax.swing.JFrame {
    
	/**
	 * constructor
	 */
    public PathFindingGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    /**
     * create all interactable objects
     */
    public void CreateGraphFrame(){
	    
	    //create external JFrame for Graph Creator
	    final JFrame creategraphframe = new JFrame();  
	    creategraphframe.setLayout(new BorderLayout());
	    creategraphframe.setTitle("Graph Editor");
	    creategraphframe.setSize(900, 910);                    
	    creategraphframe.setLocationRelativeTo(null);        
	    creategraphframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    creategraphframe.setVisible(true);
	 	       
	    final CreateGraph create = new CreateGraph();
	    
	    create.setPreferredSize(new java.awt.Dimension(910, 910));
        create.setLayout(new javax.swing.BoxLayout(jPanelMain, javax.swing.BoxLayout.LINE_AXIS));
        create.setBounds(0, 0, 900, 840);
        create.setBackground(new Color(222,222,222));

	    JButton buttonCreate = new JButton("CREATE");
	    buttonCreate.setBounds(10, 860, 90, 20);
	    buttonCreate.setToolTipText("Create the graph");
	    
	    JButton buttonClear = new JButton("CLEAR");
	    buttonClear.setBounds(10, 860, 90, 20);
	    buttonClear.setToolTipText("Clear the Graph Editor");
	    buttonClear.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	create.reset();
	        }
	    });
	    
	    JButton buttonHelp = new JButton("HELP");
	    buttonHelp.setToolTipText("<html>Create a node - click in any free space <br>on the framecreate a connection -<br> click once on the starting node and<br> once on the finishing node.<br> Moving a node - left click <br>and hold on a node, then <br>whilst holding move the mouse. release <br>left click button to place it <br> Deleting a node - right click on<br> the node deleting a connection<br> - same as creation<html>");
	    buttonHelp.setBounds(10, 860, 90, 20);
	    
	    JButton buttonSave= new JButton("SAVE");
	    buttonSave.setBounds(700, 860, 90, 20);
	    buttonSave.setToolTipText("save the current map as a file");
	    buttonSave.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	SaveLoadMap save = new SaveLoadMap();
	        	save.saveMap(create.map);
	        }
	    });
	    
	    JButton buttonLoad = new JButton("LOAD");
	    buttonLoad.setBounds(800, 860, 90, 20);
	    buttonLoad.setToolTipText("Load a previously saved map");
	    buttonLoad.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	SaveLoadMap load = new SaveLoadMap();
	        	map = load.loadMap();
	        	create.setMap(map);
	        }
	    });
	      
	    JPanel panelbutton = new JPanel();
	    panelbutton.setLayout( new FlowLayout());
	    panelbutton.add(buttonCreate);
	    panelbutton.add(buttonClear);
	    panelbutton.add(buttonSave);
	    panelbutton.add(buttonLoad);
	    panelbutton.add(buttonHelp);
	    
	    buttonCreate.addActionListener(new ActionListener() {
			
	        public void actionPerformed(ActionEvent e) {
	
	            jPanelMain.removeAll(); 
	            jPanelMain.repaint();
	            jPanelMain.revalidate();
	       
	            gui = new GraphGUI(create.map);
	            gui.setBackground(new Color(222,222,222));
	            jPanelMain.add(gui);
	            jPanelMain.setBackground(new Color(222,222,222));
	            jPanelMain.repaint();
	            jPanelMain.revalidate();
	            
	            creategraphframe.dispose();
	            //creategraphframe.setVisible(false);
	        }});
	  
	    	creategraphframe.add(panelbutton, BorderLayout.PAGE_END);
	    	creategraphframe.add(create, BorderLayout.SOUTH);
	       
	       
    }
     
    /**
     * put all objects into frame and add events to them
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
        buttonGroup1 = new javax.swing.ButtonGroup();
        title1 = new javax.swing.JLabel();
        title2 = new javax.swing.JLabel();
        jPanelInstruction = new javax.swing.JPanel();
        instruction1 = new javax.swing.JLabel();
        instruction2 = new javax.swing.JLabel();
        instruction3 = new javax.swing.JLabel();
        instruction4 = new javax.swing.JLabel();
        instruction5 = new javax.swing.JLabel();
        instruction6 = new javax.swing.JLabel();
        instruction7 = new javax.swing.JLabel();
        radiobuttonBFS = new javax.swing.JRadioButton();
        radiobuttonDFS = new javax.swing.JRadioButton();
        radioButtonAStar = new javax.swing.JRadioButton();
        radiobuttonGreedy = new javax.swing.JRadioButton();
        sliderAnimationSpeed = new javax.swing.JSlider();
        buttonCreator = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textareaLog = new javax.swing.JTextArea();
        buttonStartPause = new javax.swing.JButton();
        buttonNext = new javax.swing.JButton();
        buttonPrevious = new javax.swing.JButton();
        buttonStop = new javax.swing.JButton();
        jPanelMain = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PATH FINDING");
        setBackground(new java.awt.Color(254, 254, 254));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1280, 960));
        setResizable(false);
        getContentPane().setLayout(null);

        title1.setFont(new java.awt.Font("Bitstream Charter", 1, 80)); // NOI18N
        title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title1.setText("PATH");
        getContentPane().add(title1);
        title1.setBounds(0, 30, 360, 80);

        title2.setFont(new java.awt.Font("Bitstream Charter", 1, 80)); // NOI18N
        title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title2.setText("FINDING");
        getContentPane().add(title2);
        title2.setBounds(0, 100, 360, 85);

        jPanelInstruction.setBackground(new java.awt.Color(222, 222, 222));
        jPanelInstruction.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 111, 111), 2));

        instruction1.setText("1) Create a graph by clicking \"New Graph\"");
        instruction2.setText("2) Left click on the node to choose start node.");
        instruction3.setText("4) Choose which algorithm to run");
        instruction4.setText("5) Click start to visualize the search");
        instruction5.setText("3) Right click on the node to choose finish ");
        instruction6.setText("button.");
        instruction7.setText("node.");

        javax.swing.GroupLayout jPanelInstructionLayout = new javax.swing.GroupLayout(jPanelInstruction);
        jPanelInstruction.setLayout(jPanelInstructionLayout);
        jPanelInstructionLayout.setHorizontalGroup(
            jPanelInstructionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInstructionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInstructionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(instruction1)
                    .addComponent(instruction2)
                    .addComponent(instruction3)
                    .addComponent(instruction4)
                    .addComponent(instruction5)
                    .addGroup(jPanelInstructionLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanelInstructionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(instruction7)
                            .addComponent(instruction6))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelInstructionLayout.setVerticalGroup(
            jPanelInstructionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInstructionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instruction1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(instruction6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instruction2)
                .addGap(5, 5, 5)
                .addComponent(instruction5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instruction7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instruction3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instruction4)
                .addGap(56, 56, 56))
        );
        getContentPane().add(jPanelInstruction);
        jPanelInstruction.setBounds(12, 209, 340, 170);
        

        buttonStartPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//System.out.println("action");
            	if(!(gui.searched==true)){
            			
            		System.out.println("here");
            	
	            	if(radiobuttonDFS.isSelected()){
	            		textareaLog.append("---------------------------------------------------------------" + "\n" );
	            		textareaLog.append("RUNNING DF SEARCH" + "\n" );
		                gui.SearchDFS();
	            	}
	            	else if(radiobuttonBFS.isSelected()){
	            		textareaLog.append("---------------------------------------------------------------" + "\n" );
	            		textareaLog.append("RUNNING BF SEARCH" + "\n");
		                gui.SearchBFS();
	            	}
	            	else if(radiobuttonGreedy.isSelected()){
	            		textareaLog.append("---------------------------------------------------------------" + "\n" );
	            		textareaLog.append("RUNNING GREEDY SEARCH" +  "\n");
		                gui.SearchGreedy();
	            	}
	            	else if(radioButtonAStar.isSelected()){
	            		textareaLog.append("---------------------------------------------------------------" + "\n" );
	            		textareaLog.append("RUNNING A STAR SEARCH" +  "\n");
		                gui.SearchAStar();
	            	}
	            	else{
	            		textareaLog.append("---------------------------------------------------------------" + "\n" );
	            		textareaLog.append("Please choose an algorithm to visualize" +  "\n");
	            	}
            	}
            	else{
            		gui.pause();
            		//System.out.println("action");
            	}
            }
        });
        
        buttonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.resetGraph();
                gui.searched = false;
                textareaLog.append("--------------------------RESET-------------------------" + "\n" );
            }
        });
        
        
        buttonPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!(gui.getIndex()==0)){
                    gui.setIndex(gui.getIndex() -1);
                    System.out.println(gui.getIndex());
                    gui.updateGraph();
                }
            }
        });

        buttonNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!(gui.getIndex()== (gui.getInstances().size())-1)){
                    gui.setIndex(gui.getIndex() +1);
                    System.out.println(gui.getIndex());
                    gui.updateGraph();
                }
            }
        });
        

        buttonGroup1.add(radiobuttonBFS);
        radiobuttonBFS.setText("Breadth First Search");
        radiobuttonBFS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radiobuttonBFSMouseClicked(evt);
            }
        });
 
        getContentPane().add(radiobuttonBFS);
        radiobuttonBFS.setBounds(30, 460, 164, 22);

        buttonGroup1.add(radiobuttonDFS);
        radiobuttonDFS.setText("Depth First Search");
        radiobuttonDFS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radiobuttonDFSMouseClicked(evt);
            }
        });
        radiobuttonDFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiobuttonDFSActionPerformed(evt);
            }
        });
        getContentPane().add(radiobuttonDFS);
        radiobuttonDFS.setBounds(30, 490, 152, 22);

        buttonGroup1.add(radioButtonAStar);
        radioButtonAStar.setText("A Star Search");
        radioButtonAStar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButtonAStarMouseClicked(evt);
            }
        });
        getContentPane().add(radioButtonAStar);
        radioButtonAStar.setBounds(30, 520, 121, 22);

        buttonGroup1.add(radiobuttonGreedy);
        radiobuttonGreedy.setText("Greedy Search");
        radiobuttonGreedy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radiobuttonGreedyMouseClicked(evt);
            }
        });
        getContentPane().add(radiobuttonGreedy);
        radiobuttonGreedy.setBounds(30, 550, 126, 22);
        getContentPane().add(sliderAnimationSpeed);
        sliderAnimationSpeed.setBounds(10, 780, 340, 44);

        buttonCreator.setBackground(new java.awt.Color(254, 254, 254));
        buttonCreator.setText("Create Graph");
        buttonCreator.setToolTipText("Open the Graph Editor");
        buttonCreator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreatorActionPerformed(evt);
            }
        });
        getContentPane().add(buttonCreator);
        buttonCreator.setBounds(10, 410, 340, 29);

        textareaLog.setEditable(false);
        textareaLog.setColumns(20);
        textareaLog.setLineWrap(true);
        textareaLog.setRows(5);
        jScrollPane1.setViewportView(textareaLog);
        textareaLog.setToolTipText("Log");

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 590, 340, 180);

        buttonStartPause.setBackground(new java.awt.Color(142, 230, 129));
        buttonStartPause.setText("Start/Pause");
        buttonStartPause.setToolTipText("Starts visualizing selected algorithm on the graph");
        getContentPane().add(buttonStartPause);
        buttonStartPause.setBounds(10, 860, 160, 40);

        buttonNext.setBackground(new java.awt.Color(246, 203, 101));
        buttonNext.setText("Next");
        buttonNext.setToolTipText("Goes to the next step in \"step by step mode\"");
        getContentPane().add(buttonNext);
        buttonNext.setBounds(10, 910, 160, 40);

        buttonPrevious.setBackground(new java.awt.Color(246, 203, 101));
        buttonPrevious.setText("Previous");
        buttonPrevious.setToolTipText("Goes to the previous step in \"step by step mode\"");
        getContentPane().add(buttonPrevious);
        buttonPrevious.setBounds(180, 910, 170, 40);

        buttonStop.setBackground(new java.awt.Color(228, 89, 89));
        buttonStop.setText("Stop");
        buttonStop.setToolTipText("Stop visualizing selected algorithm and reset");
        getContentPane().add(buttonStop);
        buttonStop.setBounds(180, 860, 170, 40);

        jPanelMain.setBackground(new java.awt.Color(222, 222, 222));
        jPanelMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 111, 111), 2));
        jPanelMain.setPreferredSize(new java.awt.Dimension(910, 910));
        jPanelMain.setLayout(new javax.swing.BoxLayout(jPanelMain, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(jPanelMain);
        jPanelMain.setBounds(360, 10, 910, 910);
        jPanelMain.getAccessibleContext().setAccessibleName("");

        jPanel1.setBackground(new java.awt.Color(228, 89, 89));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 111, 111)));
        jPanel1.setPreferredSize(new java.awt.Dimension(14, 14));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(770, 930, 20, 20);

        jLabel1.setText("Visited");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(800, 930, 48, 17);

        jPanel2.setBackground(new java.awt.Color(246, 203, 101));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 111, 111)));
        jPanel2.setPreferredSize(new java.awt.Dimension(14, 14));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(860, 930, 20, 20);

        jPanel3.setBackground(new java.awt.Color(89, 176, 228));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(111, 111, 111)));
        jPanel3.setPreferredSize(new java.awt.Dimension(14, 14));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(670, 930, 20, 20);

        jLabel2.setText("Frontier");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(700, 930, 70, 17);

        jLabel3.setText("Path");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(890, 930, 31, 17);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**event for breadth first search radio button
     * 
     * @param evt
     */
    private void radiobuttonBFSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radiobuttonBFSMouseClicked
        // TODO add your handling code here:
        textareaLog.setText("");
        textareaLog.append("First creates a frontier based on the connections\nof the start node then, once all frontier nodes\nhave been explored, Creates a frontier of nodes\nbased on the children of the previous frontier.\nThis algoritm is optimal" + "\n");
    }//GEN-LAST:event_radiobuttonBFSMouseClicked

    /**event for depth first search radio button
     * 
     * @param evt
     */
    private void radiobuttonDFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiobuttonDFSActionPerformed
        //REMOVE THIS IN ECLIPSE
    }//GEN-LAST:event_radiobuttonDFSActionPerformed

    /**event for the depth first search radio button
     * 
     * @param evt
     */
    private void radiobuttonDFSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radiobuttonDFSMouseClicked
        // TODO add your handling code here:
        textareaLog.setText("");
        textareaLog.append("Considers a node then Considers the first child\nof that node until there is a node with no\nchildren, in which case it then considers the\nnext child of the previous node. This algorithm\nis not optimal."+ "\n");

    }//GEN-LAST:event_radiobuttonDFSMouseClicked

    /**event for A* radio button
     * 
     * @param evt
     */
    private void radioButtonAStarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioButtonAStarMouseClicked
        // TODO add your handling code here:
        textareaLog.setText("");
        textareaLog.append("Explores nodes based on the lowest total cost\n(calculated by adding the the cost of the path\nand the distance from the goal node), if the path\nyou are about to explore ends in the goal state,\nthen the solution is found. This algoritm is\noptimal" + "\n" );
    }//GEN-LAST:event_radioButtonAStarMouseClicked

    /**event for greedy radio button
     * 
     * @param evt
     */
    private void radiobuttonGreedyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radiobuttonGreedyMouseClicked
        // TODO add your handling code here:
        textareaLog.setText("");
        textareaLog.append("Checks if the current node is the goal node then\nchooses the next node via a heuristic funtion (in\nthis case, the shortest distance from the goal\nnodes location) done on all connected nodes. this\nalgorithm is optimal"+ "\n");
    }//GEN-LAST:event_radiobuttonGreedyMouseClicked

    private void buttonDefaultActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        //remove existing panel
    }                                             

    private void buttonCreatorActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        //open graph creator
        CreateGraphFrame();    
    }                                             
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
              
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PathFindingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PathFindingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PathFindingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PathFindingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PathFindingGUI().setVisible(true);
            }
        }); //final GraphGUI gui = new GraphGUI(create.map);
        
        System.out.print("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Map map;
    private GraphGUI gui;
    private javax.swing.JButton buttonCreator;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonNext;
    private javax.swing.JButton buttonPrevious;
    private javax.swing.JButton buttonStartPause;
    private javax.swing.JButton buttonStop;
    private javax.swing.JLabel instruction1;
    private javax.swing.JLabel instruction2;
    private javax.swing.JLabel instruction3;
    private javax.swing.JLabel instruction4;
    private javax.swing.JLabel instruction5;
    private javax.swing.JLabel instruction6;
    private javax.swing.JLabel instruction7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelInstruction;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioButtonAStar;
    private javax.swing.JRadioButton radiobuttonBFS;
    private javax.swing.JRadioButton radiobuttonDFS;
    private javax.swing.JRadioButton radiobuttonGreedy;
    private javax.swing.JSlider sliderAnimationSpeed;
    private javax.swing.JTextArea textareaLog;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    // End of variables declaration//GEN-END:variables
    
}
