// draw the picture
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JComponent;

public class ChartComponent extends JComponent {
	ArrayList<Double> yield = new ArrayList<Double>();
	ArrayList<Double> amount = new ArrayList<Double>();
	ArrayList<Double> days = new ArrayList<Double>();
	ArrayList<Double> presentXValue;
	ArrayList<Double> presentYValue;
	private ArrayList<BondTrade> trade;
	private FileHandling file;
	double X;
	double Y;
	double minX;
	double maxX;
	double minY;
	double maxY;
	public ChartComponent(ArrayList<BondTrade> trade) {
		this.trade = trade;	
		for (int i = 0; i < trade.size(); i++) {
			yield.add(trade.get(i).getYeild());
			days.add((double) trade.get(i).getDays());
			amount.add((double) trade.get(i).getAmount());
		}
		presentXValue = yield;
		presentYValue = days;
		this.addMouseListener(new MousePressListener());
	}
	
	public void paintComponent(Graphics g) {
		minX = findMin(presentXValue);
		maxX = findMax(presentXValue);
		minY = findMin(presentYValue);
		maxY = findMax(presentYValue);
		Graphics2D g2 = (Graphics2D) g;
		g.drawLine(20, 20, 20, 450);
		g.drawLine(20, 450, 700, 450); // 400*680
		g.drawString("Y-Axis", 0, 20);
		g.drawString("X-Axis", 665, 465);
		for (int i =0; i < 10; i++) {
//draw tickmark of Y			
			g.drawLine(20,450-i*450/10,30,450-i*450/10);		
			String tickmark_Y = String.format("%.1f", i*(maxY - minY)/10);
			g.drawString(tickmark_Y,0,450-i*450/10);
//draw tickmark of X		
			g.drawLine(20+i*680/10, 450, 20+i*680/10,440);
			String tickmark_X = String.format("%.1f", i*(maxX - minX)/10);
			g.drawString(tickmark_X, 10 + i*680/10,465);
		}
		for (int i = 0; i < trade.size(); i++) {			
			X = presentXValue.get(i) * 680 / (maxX - minX);
			Y = presentYValue.get(i) * 400 / (maxY - minY);
			g.setColor(Color.BLACK);
			Ellipse2D.Double e = new Ellipse2D.Double(X + 20, 450 - Y, 5, 5);
			g2.fill(e);
		}
		for (int i = 0; i < trade.size(); i++) {
			yield.add(trade.get(i).getYeild());
			days.add((double) trade.get(i).getDays());
			amount.add((double) trade.get(i).getAmount());
		}
	}
	class MousePressListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			boolean check = false;
			int x = e.getX();
			int y = e.getY();	
			System.out.println(x);
			System.out.println(y);
			for (int i = 0; i < trade.size(); i++) {			
				X = presentXValue.get(i) * 680 / (maxX - minX);
				Y = presentYValue.get(i) * 400 / (maxY - minY);
				if(x>(X + 20)&&x<(X + 25)&&y<(455-Y)&&y>(450-Y)) {
					System.out.println(X + 20);
					System.out.println(450-Y);
					check = true;
					View.rateArea.setText("Yield: "+ trade.get(i).getYeild()
							             + "\nDays: "+ trade.get(i).getDays()
							             + "\nDays: "+ trade.get(i).getAmount());
				} 
				if (check ==true ) {
					break;
				}
				
			}
			
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
   
    }
	public double findMax(ArrayList<Double> numberList) {
		double max = 0.0;
		for (int i = 0; i < numberList.size(); i++) {
			if (numberList.get(i) > max) {
				max = numberList.get(i);
			}
		}
		return max;
	}

	public double findMin(ArrayList<Double> numberList) {
		double min = 0.0;
		for (int i = 0; i < numberList.size(); i++) {
			if (numberList.get(i) < min) {
				min = numberList.get(i);
			}
		}
		return min;
	}

	public ArrayList<Double> getPresentXValue() {
		return this.presentXValue;
	}

	public ArrayList<Double> getPresentYValue() {
		return this.presentYValue;
	}

	public void setPresentXValue(ArrayList<Double> XValue) {
		this.presentXValue = XValue;
	}

	public void setPresentYValue(ArrayList<Double> YValue) {
		this.presentYValue = YValue;
	}

}
