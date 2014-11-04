package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import model.Sensor;
import controller.Imeca;

public class App extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Console console;
	private JButton start, how, why;
	
	public App(){
		super("Sistema Experto - Monitoreo Ambiental");
		
		setSize(500, 350);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		console = new Console();
		console.setBounds(10, 10, 330, 300);
		console.setBorder(BorderFactory.createLineBorder(Color.black));
		
		start = new JButton("Iniciar");
		start.setBounds(360, 30, 120, 25);
		start.addActionListener(this);

		how = new JButton("¿Como?");
		how.setBounds(360, 70, 120, 25);
		how.addActionListener(this);
		
		why = new JButton("¿Por qué?");
		why.setBounds(360, 110, 120, 25);
		why.addActionListener(this);
		
		add(console);
		add(start);
		add(how);
		add(why);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start){
			Imeca imecaController = new Imeca(console);
			Runnable runnable = new Sensor(imecaController);
			Thread sensor = new Thread(runnable);
			sensor.start();
		}
	}

}
