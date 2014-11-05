package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileLogger {
	
	private File file;
	
	public FileLogger(){
		file = new File("log.txt");
		
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void printTimestamp(){
		String timestamp = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss").format(Calendar.getInstance().getTime());
		write(timestamp);
	}
	
	public void print(String text){
		write(text);
	}
	
	private void write(String text){

		try{
			FileOutputStream is = new FileOutputStream(file, true);
	        OutputStreamWriter osw = new OutputStreamWriter(is);    
	        Writer writer = new BufferedWriter(osw);
			writer.write(text + "\n");
			writer.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
