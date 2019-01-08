// the Interface 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class View extends JFrame{
	private JPanel panel;
	private JButton openButton;
	private JButton quitButton;
	private double value = 0.0;
	private JLabel label;
	private GridBagConstraints position;
	private final int FRAME_WIDTH = 900;
	private final int FRAME_HEIGHT = 600; 
	private final int CHART_WIDTH = 700;
	private final int CHART_HEIGHT = 500; 
	FileHandling file;
	private JTextArea rateArea;
	private JComboBox X;
	private JComboBox Y;
	private String fileName;
	private ChartComponent chart;
	public View () {
		creatTextArea();
		creatButton();
		creatChart();
		creatCombox ();
		creatPanel();
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
	}
	public void creatChart() {
		chart = new ChartComponent();
		chart.setBounds(50, 20, CHART_WIDTH, CHART_HEIGHT);
	}
	

// use this to hold the selected bond details
	public void creatTextArea() {
		final int FILE_WIDTH = 250;
		final int FILE_LENGTH = 60;
		rateArea = new JTextArea();
		rateArea.setEditable(false);
		rateArea.setBounds(500, 485, FILE_WIDTH,FILE_LENGTH);
	}
// JfileChooser	
	class openButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			file = new FileHandling();
			fileName = file.title;
			ArrayList<BondTrade> test = file.getTrade(); 
			for(int i=0;i<= test.size()-1;i++) {
				System.out.println(test.get(i).getYeild());
			}
			repaint();
			
//			ArrayList<BondTrade> test = file.getTrade(); 
//			System.out.println(test.get(3).getYeild());
//			String text = rateField.getText();
//		    value += Double.parseDouble(text);
//		    label.setText("Balance: "+value);
//		    rateArea.append(value+ "\r\n");
//		    rateArea.setText(String.valueOf(value));
		}	
	}
    
// quit program when user click quik button
	class quitButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}	
	}
	public void creatButton() {
// open button
		openButton = new JButton("Open");
		ActionListener open = new openButtonClickListener();
		openButton.addActionListener(open);
		openButton.setBounds(620, 10, 100, 25);
		label = new JLabel("fileName");
		label.setBounds(300, 10,100, 25);
// quit button	
		quitButton	= new JButton("Quit");	
		ActionListener quit = new quitButtonClickListener();
		quitButton.addActionListener(quit);		
		quitButton.setBounds(720, 10, 100, 25);
	}
	public void creatCombox () {
		X = new JComboBox();
	    X.addItem("Value");
	    X.addItem("Days");
	    X.addItem("Amout");
	    X.setBounds(150,500, 100, 25);
	    Y = new JComboBox();
	    Y.addItem("Value");
	    Y.addItem("Days");
	    Y.addItem("Amout");
	    Y.setBounds(270,500, 100, 25);
	}
	
	public void creatPanel() { 
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(rateArea);		
		panel.add(openButton);
		panel.add(label);
		panel.add(quitButton);
		panel.add(chart);	
		panel.add(X);
		panel.add(Y);
		add(panel);   		
	}
	
}
