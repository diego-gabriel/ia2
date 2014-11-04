package model;

import controller.Imeca;

public class Sensor implements Runnable{

	private Imeca imecaObserver;
	private final int SLEEP_TIME = 200;
	public Sensor(Imeca observer){
		imecaObserver = observer;
		System.out.println("created");
	}
	
	@Override
	public void run() {
		while (true){
			delay();
			imecaObserver.process(generateValues());
		}
	}
	
	private void delay(){
		try{
			Thread.sleep(SLEEP_TIME);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private AirData generateValues(){
		
		double O3, CO, SO2, NO2;
		double remain = 1.0;
		CO = Math.random() * remain;
		remain -= CO;
		NO2 = Math.random() * remain;
		remain -= NO2;
		SO2 = Math.random() * remain;
		remain -= SO2;
		O3 = Math.random() * remain;
		
		return new AirData(CO, NO2, SO2, O3);
		
	}
}
