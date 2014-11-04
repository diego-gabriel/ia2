package model;

import controller.Imeca;

public class Sensor implements Runnable{

	private Imeca imecaObserver;
	private final int SLEEP_TIME = 500;
	private boolean active;
	private double O3, CO, SO2, NO2;
	
	public Sensor(Imeca observer){
		imecaObserver = observer;
		active = false;
		
		CO = Math.random() * 25;
		NO2 = Math.random() * 0.430;
		SO2 = Math.random() * 0.265;
		O3 = Math.random() * 0.225;
	}
	
	@Override
	public void run() {
		
		while (true){
			if (active)
				imecaObserver.process(generateValues());
			delay();
			
		}
	}
	
	public boolean isRunning(){
		return active;
	}
	
	public void resume(){
		active = true;
	}
	
	public void stop(){
		active = false;
	}
	
	private void delay(){
		try{
			Thread.sleep(SLEEP_TIME);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private AirData generateValues(){
		
		CO += Math.random() * 25 * sign();
		NO2 += Math.random() * 0.430 * sign();
		SO2 += Math.random() * 0.265 * sign();
		O3 += Math.random() * 0.225 * sign();

		if(CO < 0)
			CO = -CO;
		if(NO2 < 0)
			NO2 = -NO2;
		if(SO2 < 0)
			SO2 = -SO2;
		if(O3 < 0)
			O3 = -O3;
		
		return new AirData(CO, NO2, SO2, O3);
	}
	
	private double sign(){ 
		return Math.random() < 0.5 ? 0.1 : -0.1;
	}
}
