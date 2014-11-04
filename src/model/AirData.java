package model;

public class AirData {
	private double CO, NO2, SO2, O3;
	
	public AirData(double a, double b, double c, double d){
		CO = a;
		NO2 = b;
		SO2 = c;
		O3 = d;
	}
	
	public double getCO() {
		return CO;
	}
	
	public double getNO2() {
		return NO2;
	}
	
	public double getO3() {
		return O3;
	}

	public double getSO2() {
		return SO2;
	}
	
	public String toString(){
		return "CO = " + CO + "\nNO2 = " + NO2 + "\nO3 = " + O3 + "\nSO2 = " + SO2 + "\n-------------------------------------------"; 
	}
}
