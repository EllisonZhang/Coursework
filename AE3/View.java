// the Interface 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class View extends JFrame {
	private JPanel panel;
	private JButton openButton;
	private JButton quitButton;
	private JLabel label;
	private final int FRAME_WIDTH = 900;
	private final int FRAME_HEIGHT = 600;
	private final int CHART_WIDTH = 700;
	private final int CHART_HEIGHT = 500;
	private FileHandling file;
	public static JTextArea rateArea;
	private JComboBox<String> X;
	private JComboBox<String> Y;
	private String fileName;
	private ChartComponent chart;
	private ArrayList<BondTrade> trade = new ArrayList<BondTrade>();

	public View() {
		creatTextArea();
		creatButton();
		creatCombox();
		creatLable();
		creatPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

// use this to hold the selected bond details
	static public void creatTextArea() {
		final int FILE_WIDTH = 250;
		final int FILE_LENGTH = 60;
		rateArea = new JTextArea();
		rateArea.setEditable(false);
		rateArea.setBounds(500, 485, FILE_WIDTH, FILE_LENGTH);

	}

    class MousePressListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getY();
			int y = e.getX();	
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
	
	class openButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			file = new FileHandling();
			fileName = file.title;
			label.setText(fileName);
			ArrayList<BondTrade> trade = file.getTrade();
			chart = new ChartComponent(trade);
			chart.setBounds(50, 20, CHART_WIDTH, CHART_HEIGHT);
//			chart.addMouseListener(new MousePressListener());
			panel.add(chart);
			repaint();
		}
	}

// quit program when user click quik button
	class quitButtonClickListener implements ActionListener {
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
// quit button	
		quitButton = new JButton("Quit");
		ActionListener quit = new quitButtonClickListener();
		quitButton.addActionListener(quit);
		quitButton.setBounds(720, 10, 100, 25);
	}

	public void creatLable() {
		label = new JLabel("fileName");
		label.setBounds(300, 10, 200, 30);
	}

	class comboBoxClickListenerX implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JComboBox comboBox = (JComboBox) e.getSource();
			Object selected = comboBox.getSelectedItem();

			if (selected.equals("X-Yield")) {
				chart.presentXValue = chart.yield;
				chart.repaint();
				System.out.println("hello");
			} else if (selected.equals("X-Days")) {
				chart.presentXValue = chart.days;
				chart.repaint();
				System.out.println("hello1");
			} else if (selected.equals("X-Amount")) {
				chart.presentXValue = chart.amount;
				chart.repaint();
				System.out.println("hello2");
			}
		}
	}

	class comboBoxClickListenerY implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JComboBox comboBox = (JComboBox) e.getSource();
			Object selected = comboBox.getSelectedItem();
//			
			if (selected.equals("Y-Yield")) {
				chart.presentYValue = chart.yield;
				chart.repaint();
				System.out.println("hello");
			} else if (selected.equals("Y-Days")) {
				chart.presentYValue = chart.days;
				chart.repaint();
				System.out.println("hello");
			} else if (selected.equals("Y-Amount")) {
				chart.presentYValue = chart.amount;
				chart.repaint();
				System.out.println("hello");
			}
		}
	}

	public void creatCombox() {
		X = new JComboBox<String>();
		X.addItem("X-Yield");
		X.addItem("X-Days");
		X.addItem("X-Amount");
		X.setBounds(150, 500, 100, 25);
		ActionListener changeXBox = new comboBoxClickListenerX();
		X.addActionListener(changeXBox);
		ActionListener changeYBox = new comboBoxClickListenerY();
		Y = new JComboBox<String>();
		Y.addItem("Y-Days");
		Y.addItem("Y-Yield");
		Y.addItem("Y-Amount");
		Y.setBounds(270, 500, 100, 25);
		Y.addActionListener(changeYBox);
	}

	public void creatPanel() {
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(rateArea);
		panel.add(openButton);
		panel.add(label);
		panel.add(quitButton);	
		panel.add(X);
		panel.add(Y);
		add(panel);
	}
}
