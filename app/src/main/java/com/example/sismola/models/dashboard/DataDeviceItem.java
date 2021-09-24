package com.example.sismola.models.dashboard;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DataDeviceItem{

	@SerializedName("date")
	private String date;

	@SerializedName("air_temp")
	private int airTemp;

	@SerializedName("date_unix")
	private int dateUnix;

	@SerializedName("rainfall")
	private int rainfall;

	@SerializedName("ph")
	private double ph;

	@SerializedName("humidity")
	private int humidity;

	@SerializedName("light_intensity")
	private int lightIntensity;

	@SerializedName("soil_moisture")
	private double soilMoisture;

	@SerializedName("soil_temp")
	private double soilTemp;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setAirTemp(int airTemp){
		this.airTemp = airTemp;
	}

	public int getAirTemp(){
		return airTemp;
	}

	public void setDateUnix(int dateUnix){
		this.dateUnix = dateUnix;
	}

	public int getDateUnix(){
		return dateUnix;
	}

	public void setRainfall(int rainfall){
		this.rainfall = rainfall;
	}

	public int getRainfall(){
		return rainfall;
	}

	public void setPh(double ph){
		this.ph = ph;
	}

	public double getPh(){
		return ph;
	}

	public void setHumidity(int humidity){
		this.humidity = humidity;
	}

	public int getHumidity(){
		return humidity;
	}

	public void setLightIntensity(int lightIntensity){
		this.lightIntensity = lightIntensity;
	}

	public int getLightIntensity(){
		return lightIntensity;
	}

	public void setSoilMoisture(double soilMoisture){
		this.soilMoisture = soilMoisture;
	}

	public double getSoilMoisture(){
		return soilMoisture;
	}

	public void setSoilTemp(double soilTemp){
		this.soilTemp = soilTemp;
	}

	public double getSoilTemp(){
		return soilTemp;
	}

	@Override
 	public String toString(){
		return 
			"DataDeviceItem{" + 
			"date = '" + date + '\'' + 
			",air_temp = '" + airTemp + '\'' + 
			",date_unix = '" + dateUnix + '\'' + 
			",rainfall = '" + rainfall + '\'' + 
			",ph = '" + ph + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",light_intensity = '" + lightIntensity + '\'' + 
			",soil_moisture = '" + soilMoisture + '\'' + 
			",soil_temp = '" + soilTemp + '\'' + 
			"}";
		}
}