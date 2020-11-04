import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class GUI extends javax.swing.JFrame  {

	JFrame jframe;
	JPanel topPanel;
	JPanel bottomPanel;
    JScrollPane scrollPane;
    JLabel label;
    
    int[] move1;
    int[] aiMove;
    
    ArrayList<JButton> buttonList;
	public GUI(){
		
		move1 = new int[] {-1, -1};
		buttonList = new ArrayList<JButton>();
		
    	jframe = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JSplitPane splitPane = new JSplitPane();
        topPanel = new JPanel();
		bottomPanel = new JPanel(new GridBagLayout());
		newGrid(Main.d);
		topPanel();
		bottomPanel();
        setPreferredSize(new Dimension(1500, 1000)); 
        getContentPane().setLayout(new GridLayout());  
        getContentPane().add(splitPane);             
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT); 
        splitPane.setDividerLocation(700);                   
        splitPane.setTopComponent(scrollPane);                  
        splitPane.setBottomComponent(bottomPanel);           
        pack();  
	}
	
	
	
	private void newGrid(int d) {
		int bSize = 50;
    	topPanel.setLayout(new GridLayout(Main.d, Main.d));
    	for(int y = 0; y < Main.d; y++) {
    		for(int x = 0; x < Main.d; x++) {
    			JButton button = new JButton();
    			String text = "";
    			if(Main.grid.getCell(x, y) != null) {
					text = Main.grid.getCell(x, y).getDisplayText();
					if( Main.grid.getCell(x, y).name == 'P') {
						button.setBackground(Color.LIGHT_GRAY);
					}else {
						button.setBackground(Color.GREEN);
					}
    			}else{
    				button.setBackground(Color.GREEN);
    			}
	            
				button.setText(text);
	            button.setFont(new Font("Arial", Font.BOLD, 15));
	            button.setMargin(new Insets(0, 0, 0, 0));
	            button.addActionListener(new ActionListener() {
	            	@Override
	                public void actionPerformed(ActionEvent e) {	         
	            		int index = buttonList.indexOf(button);
	            		int y = index/d;
	            		int x = index%d;
	            		
	            		if(move1[0] == -1) {
		            		//check if human piece
		            		if(Main.grid.getCell(x, y) == null || Main.grid.getCell(x, y).side != 0) {
		            			label.setText("Invalid piece to move.");
		            			return;
		            		}
		            		
	            			move1[0] = x;
	            			move1[1] = y;
	            			buttonList.get(move1[1]*Main.d + move1[0]).setBackground(Color.YELLOW);
	            		}else {
	            			boolean success = Main.grid.move(move1[0], move1[1], x, y);
	            			//System.out.println(Main.grid.getCell(move1[0], move1[1]).getDisplayText()+"("+move1[0]+", " + move1[1] +") -> ("+x+", " +y+ ")" );
	            			if(!success) {
	            				//System.out.println("Invalid move");
	            				label.setText("Invalid move.");
	            			}else { //update GUI
	            				if(aiMove != null) {
		            				buttonList.get(aiMove[1]*Main.d + aiMove[0]).setBackground(Color.GREEN);
		            				buttonList.get(aiMove[3]*Main.d + aiMove[2]).setBackground(Color.GREEN);
	            				}
	            				buttonList.get(move1[1]*Main.d + move1[0]).setText("");
	            				buttonList.get(move1[1]*Main.d + move1[0]).setBackground(Color.GREEN);
	            				if(Main.grid.getCell(x, y) != null) {
	            					buttonList.get(y*Main.d + x).setText(Main.grid.getCell(x, y).getDisplayText());
	            				}else {
	            					buttonList.get(y*Main.d + x).setText("");
	            				}
	            				//System.out.println("Successful move");
	            				//Main.printGrid(Main.grid);
	            				move1[0] = -1;
	            				
	            				if(Main.grid.side0.size() > 0 && Main.grid.side1.size() > 0 ) {
		            				//Make AI move here
	            					label.setText("AI is making a move.");
	            					System.out.println("AI is making move..");
		            				MiniMax m = new MiniMax();
		            				aiMove = m.getNextMove();
		            				if(aiMove != null) {
			            				
			            				Main.grid.move(aiMove[0], aiMove[1], aiMove[2], aiMove[3]);
			            				
			            				//Update GUI
			            				buttonList.get(aiMove[1]*Main.d + aiMove[0]).setText("");
			            				buttonList.get(aiMove[1]*Main.d + aiMove[0]).setBackground(Color.RED);
			            				buttonList.get(aiMove[3]*Main.d + aiMove[2]).setBackground(Color.RED);
			            				if(Main.grid.getCell(aiMove[2], aiMove[3]) != null) {
			            					buttonList.get(aiMove[3]*Main.d + aiMove[2]).setText(Main.grid.getCell(aiMove[2], aiMove[3]).getDisplayText());
			            				}else {
			            					buttonList.get(aiMove[3]*Main.d + aiMove[2]).setText("");
			            				}
			            				label.setText("Your move.");
		            				}else {
		            					System.out.println("Ai could not make a move.");
		            				}
	            				}else {
	            					if(Main.grid.side0.size() == 0) {
	            						label.setText("You lost. AI won.");
	            					}else {
	            						label.setText("You won. AI lost.");
	            					}
	            				}
	            			}
	            		}
	            	}
	            });
	            buttonList.add(button);
        		button.setPreferredSize(new Dimension(bSize,bSize));
                topPanel.add(button);
    		}
    	}
    	
	}
	
    private void topPanel() {
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0,0));
        container.add(topPanel);
        scrollPane = new JScrollPane(container);
    }
    
    
    private void bottomPanel() {
		//Component horizontalStrut = Box.createHorizontalStrut(100);
		//bottomPanel.add(horizontalStrut);
		
		label = new JLabel("Your move.");
		label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		bottomPanel.add(label);
		
		
		//Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		//bottomPanel.add(horizontalStrut_1);
    }
}
