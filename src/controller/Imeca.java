package controller;

import java.awt.Color;

import jess.JessException;
import model.AirData;
import model.InferenceEngine;
import view.App;
import view.FileLogger;


public class Imeca {
	
	private App console;
	private InferenceEngine bc; 
	private String lastStatus;
	private AirData lastData;
	private FileLogger log;
	
	public Imeca(App c){
		console = c;
		lastStatus = "";
		bc = new InferenceEngine();
		log = new FileLogger();
	}
	
	public void process(AirData data){
		
		double imecaO3 = data.getO3()*100/0.11;
		double imecaSO2 = data.getSO2()*100/0.13;
		double imecaNO2 = data.getNO2()*100/0.21;
		double imecaCO = data.getCO()*100/11;
		double imecaAvg = (imecaO3 + imecaCO + imecaNO2 + imecaSO2) / 4;
		
		String airStatus = "";
		try {
			airStatus = fetchResult(bc.fireRules(imecaAvg));
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data);
		lastStatus = airStatus; 
		lastData = data;
		console.append("IMECA = " + imecaAvg);
		console.append("Calidad del aire: " + airStatus + "\n---------------------");
		
		log.printTimestamp();
		log.print("IMECA = " + imecaAvg);
		log.print("Calidad del aire: " + airStatus);
		log.print(data.toString());
		
	}
	
	private String fetchResult(String data){
		char k = data.charAt(data.length()-1);
		String result = "";
		switch(k){
			case '1': 	result = "Bueno"; console.setBkColor(Color.GREEN); 
						console.setMessage("Calidad del ambiente satisfactoria.");break;
			case '2': 	result = "Regular"; console.setBkColor(Color.YELLOW); 
						console.setMessage("La contaminacion presenta un efecto moderado en la salud de algunos habitantes."); break;
			case '3': 	result = "Malo"; console.setBkColor(Color.RED);
						console.setMessage("La contaminacion genera problemas respiratorios y cardiacos en niños y ancianos."); break;
			case '4':	result = "Muy Malo"; console.setBkColor(Color.BLACK);
						console.setMessage("La contaminacion genera efectos negativos en la salud de todos los habitantes"); break;
		}
		
		return result;
	}
	
	public String generateHow(){
		String how = "";
		
		if (lastStatus.equals(""))
			how = "Aun no se tomaron muestras del ambiente.";
		else{
			if (lastStatus.equals("Bueno")){
				how = "Estado del ambiente: " + lastStatus + "\n\n";
				how = how + "El indice IMECA calculado se encuentra el el rango ";
				how = how + "[0 - 50]";
			}
			if (lastStatus.equals("Regular")){
				how = "Estado del ambiente: " + lastStatus + "\n\n";
				how = how + "El indice IMECA calculado se encuentra el el rango ";
				how = how + "[51 - 100]";
			}
			if (lastStatus.equals("Malo")){
				how = "Estado del ambiente: " + lastStatus + "\n\n";
				how = how + "El indice IMECA calculado se encuentra el el rango ";
				how = how + "[101 - 150]";
			}
			if (lastStatus.equals("Muy Malo")){
				how = "Estado del ambiente: " + lastStatus + "\n\n";
				how = how + "El indice IMECA calculado es mayor a";
				how = how + "150";
			}
			
			how = how + "\n\n" + lastData;
		}
		
		return how;
	}
	
	
}
