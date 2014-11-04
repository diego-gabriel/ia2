package controller;

import model.AirData;
import view.Console;


public class Imeca {
	
	private Console console;
	public Imeca(Console c){
		console = c;
	}
	
	public void process(AirData data){
		
		double imecaO3 = data.getO3()*100/0.11;
		double imecaSO2 = data.getSO2()*100/0.13;
		double imecaNO2 = data.getNO2()*100/0.21;
		double imecaCO = data.getCO()*100/11;
		double imecaAvg = (imecaO3 + imecaCO + imecaNO2 + imecaSO2) / 4;
		System.out.println(data);
		console.append("IMECA = " + imecaAvg + "\n---------------------");
	}
	
	
}
