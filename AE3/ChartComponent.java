// draw the picture
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

	public ChartComponent(ArrayList<BondTrade> trade) {
		this.trade = trade;	
		for (int i = 0; i < trade.size(); i++) {
			yield.add(trade.get(i).getYeild());
			days.add((double) trade.get(i).getDays());
			amount.add((double) trade.get(i).getAmount());
		}
		presentXValue = yield;
		presentYValue = days;
	}

	public void paintComponent(Graphics g) {
		double minX = findMin(presentXValue);
		double maxX = findMax(presentXValue);
		double minY = findMin(presentYValue);
		double maxY = findMax(presentYValue);
		Graphics2D g2 = (Graphics2D) g;
		g.drawLine(20, 20, 20, 450);
		g.drawLine(20, 450, 700, 450); // 400*680
		g.drawString("Y", 0, 20);
		g.drawString("X", 685, 465);
		for (int i =0; i < 10; i++) {
//draw tickmark of Y			
			g.drawLine(20,450-i*450/10,30,450-i*450/10);		
			String tickmark_Y = String.format("%.1f", i*(maxY - minY)/10);
			g.drawString(tickmark_Y,0,450-i*450/10);
//draw tickmark of X		
			g.drawLine(20+i*680/10, 450, 20+i*680/10,440);
			String tickmark_X = String.format("%.1f", i*(maxX - minX)/10);
			g.drawString(tickmark_X, 20 + i*680/10,465);
		}
		for (int i = 0; i < trade.size(); i++) {
			double X;
			double Y;
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
