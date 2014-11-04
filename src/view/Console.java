package view;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class Console extends JScrollPane{

	private static final long serialVersionUID = 1L;
	private JTextArea console;
	
	public Console(){
		super();
		console = new JTextArea();
		console.setEditable(false);
		console.setLineWrap(true);
		console.setWrapStyleWord(true);
		DefaultCaret caret = (DefaultCaret)console.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setViewportView(console);
	}
	
	public void notify(String input){
		append(input);
	}
	
	public void append(String text){
		console.append(text + "\n");
	}
}
