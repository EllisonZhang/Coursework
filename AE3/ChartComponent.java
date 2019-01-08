// draw the picture

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class ChartComponent extends JComponent {
	private double yield;
	private int amount;
	private int days;
	public void paintComponent(Graphics g) {
		g.drawLine(20, 50, 20, 450);
		g.drawLine(20, 450, 700 ,450); // 400*700
        g.drawString("Y",0,50);
//        g.drawString("0.0",0,465);
        g.drawString("X",650,465);
//      FileHandling file = new FileHandling();
		ArrayList<BondTrade> test = FileHandling.trade; 
		
        for(int i=0;i<test.size()-1; i++) {
        	yield = test.get(i).getYeild();
        	days = test.get(i).getDays();
        	int j = (int)(yield*680/19.477);
        	int k = (int)(days*400/4217);
        	System.out.println(j); 	
        	g.setColor(Color.BLACK);
            g.drawOval(j+20, k+50, 3, 3);
            g.fillOval(j+20, k+50, 3, 3);    
        }
		
        
       
	}
//	public void paintPoint(Graphics g) {
//		FileHandling file = new FileHandling();
//		ArrayList<BondTrade> test = file.getTrade(); 
//        for(int i=0;i<test.size()-1; i++) {
//        	yield = test.get(i).getYeild();
//        	int j = (int)(19.477/680*yield);
//        	g.setColor(Color.BLACK);
//            g.drawOval(j, 50, 3, 3);
//            g.fillOval(j, 50, 3, 3);    
//        }
//		
//	}
	public void append() {
		
	}   
        	
    
}
