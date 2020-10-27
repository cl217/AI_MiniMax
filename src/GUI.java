import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends javax.swing.JFrame  {

	JFrame jframe;
	JPanel topPanel;
	JPanel bottomPanel;
    JScrollPane scrollPane;
    
	public GUI(){
    	jframe = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JSplitPane splitPane = new JSplitPane();
        topPanel = new JPanel();
		bottomPanel = new JPanel();
		newGrid(Main.grid.d);
		topPanel();
		//bottomPanel();
        setPreferredSize(new Dimension(1500, 1000)); 
        getContentPane().setLayout(new GridLayout());  
        getContentPane().add(splitPane);             
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT); 
        splitPane.setDividerLocation(700);                   
        splitPane.setTopComponent(scrollPane);                  
        splitPane.setBottomComponent(bottomPanel);           
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)); 
        pack();  
	}
	
	private void newGrid(int d) {
		int bSize = 50;
		Main.grid = new Grid(d);
    	topPanel.setLayout(new GridLayout(Main.grid.d, Main.grid.d));
    	for(int x = 0; x < Main.grid.d; x++) {
    		for(int y = 0; y < Main.grid.d; y++) {
    			JButton button;
    			String text = "";
    			if(Main.grid.getCell(x, y) != null) {
					text = Main.grid.getCell(x, y).getDisplayText();
    			}
	            button = new JButton(text);
	            button.setFont(new Font("Arial", Font.BOLD, 15));
	            button.setMargin(new Insets(0, 0, 0, 0));
	            button.addActionListener(new ActionListener() {
	            	@Override
	                public void actionPerformed(ActionEvent e) {
	                	//TODO: human movements
	            		//use Main.grid.move()
	                }
	            });
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
    
    
	/*
    private void bottomPanel() {
		Component horizontalStrut = Box.createHorizontalStrut(20);
		bottomPanel.add(horizontalStrut);
		
		Box verticalBox = Box.createVerticalBox();
		bottomPanel.add(verticalBox);
		
		JLabel lblNewLabel = new JLabel("New Grid");
		verticalBox.add(lblNewLabel);
		
		JLabel errorLabel = new JLabel("Invalid d");
		verticalBox.add(errorLabel);
		errorLabel.setVisible(false);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		JLabel lblNewLabel_1 = new JLabel("d = ");
		horizontalBox.add(lblNewLabel_1);
		
		JTextField textDSize = new JTextField();
		horizontalBox.add(textDSize);
		textDSize.setColumns(10);
		textDSize.setMaximumSize(new Dimension(100, 200));
		
		JButton btnNewGrid = new JButton("Start new grid");
		verticalBox.add(btnNewGrid);
		btnNewGrid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					int d = Integer.parseInt(textDSize.getText());
					if( d % 3 == 0) {
						newGrid(d);
						errorLabel.setVisible(false);
					}else {
						errorLabel.setVisible(true);
					}
				}catch(NumberFormatException exception) {
					errorLabel.setVisible(true);
				}
				
			}
        });
		
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		bottomPanel.add(horizontalStrut_1);
    }
    */
    
    
	
}
