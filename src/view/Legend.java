package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Legend extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel[] colors;
	private JLabel[] labels;
	
	public Legend(){
		colors = new JPanel[4];
		labels = new JLabel[4];
		setSize(480, 35);
		for (int i = 0; i < 4; i++){
			colors[i] = new JPanel();
			colors[i].setBounds(10 + i*120, 0, 25, 25);
			labels[i] = new JLabel();
			labels[i].setBounds(45 + i*120, 0, 75, 25);
			add(colors[i]);
			add(labels[i]);
		}

		colors[0].setBackground(Color.GREEN);
		colors[1].setBackground(Color.YELLOW);
		colors[2].setBackground(Color.RED);
		colors[3].setBackground(Color.BLACK);

		labels[0].setText("Bueno");
		labels[1].setText("Regular");
		labels[2].setText("Malo");
		labels[3].setText("Muy Malo");
	}
}
