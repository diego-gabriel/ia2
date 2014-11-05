package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Sensor;
import controller.Imeca;

public class App extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Console console;
	private JButton start, how;
	private Sensor sensor;
	private Imeca controller;
	private JPanel status;
	private Legend legend;
	private JTextArea message;
	
	public App(){
		super("Sistema Experto - Monitoreo Ambiental");

		setSize(500, 410);
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
		
		status = new JPanel();
		status.setBounds(360,  110, 120, 200);
		
		legend = new Legend();
		legend.setBounds(10, 320, 480, 35);
		
		message = new JTextArea("...");
		message.setBounds(10, 350, 480, 35);
		message.setEditable(false);
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		message.setBackground(getBackground());
		
		add(console);
		add(start);
		add(how);
		add(status);
		add(legend);
		add(message);
		
		startSensor();
	}
	
	private void startSensor(){
		controller = new Imeca(this);
		sensor = new Sensor(controller);
		Thread hilo = new Thread(sensor);
		hilo.start();
	}
	
	public void setBkColor(Color color){
		status.setBackground(color);
	}
	
	public void append(String text){
		console.append(text);
	}
	
	public void setMessage(String msg){
		message.setText(msg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start){
			if (sensor.isRunning()){
				sensor.stop();
				start.setText("Iniciar");
			} else {
				sensor.resume();
				start.setText("Detener");
			}
		}
		
		if (e.getSource() == how){
			boolean isRunning = sensor.isRunning();
			if (isRunning)
				sensor.stop();
			JOptionPane.showMessageDialog(this, controller.generateHow());
			if (isRunning)
				sensor.resume();
		}
	}	

}
