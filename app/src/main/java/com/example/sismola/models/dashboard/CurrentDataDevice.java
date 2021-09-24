package com.example.sismola.models.dashboard;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CurrentDataDevice{

	@SerializedName("air_temp")
	private int airTemp;

	@SerializedName("rainfall")
	private int rainfall;

	@SerializedName("last_update")
	private String lastUpdate;

	@SerializedName("ph")
	private double ph;

	@SerializedName("last_update_unix")
	private int lastUpdateUnix;

	@SerializedName("humidity")
	private double humidity;

	@SerializedName("light_intensity")
	private int lightIntensity;

	@SerializedName("soil_moisture")
	private double soilMoisture;

	@SerializedName("soil_temp")
	private double soilTemp;

	public void setAirTemp(int airTemp){
		this.airTemp = airTemp;
	}

	public int getAirTemp(){
		return airTemp;
	}

	public void setRainfall(int rainfall){
		this.rainfall = rainfall;
	}

	public int getRainfall(){
		return rainfall;
	}

	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdate(){
		return lastUpdate;
	}

	public void setPh(double ph){
		this.ph = ph;
	}

	public double getPh(){
		return ph;
	}

	public void setLastUpdateUnix(int lastUpdateUnix){
		this.lastUpdateUnix = lastUpdateUnix;
	}

	public int getLastUpdateUnix(){
		return lastUpdateUnix;
	}

	public void setHumidity(double humidity){
		this.humidity = humidity;
	}

	public double getHumidity(){
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
			"CurrentDataDevice{" + 
			"air_temp = '" + airTemp + '\'' + 
			",rainfall = '" + rainfall + '\'' + 
			",last_update = '" + lastUpdate + '\'' + 
			",ph = '" + ph + '\'' + 
			",last_update_unix = '" + lastUpdateUnix + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",light_intensity = '" + lightIntensity + '\'' + 
			",soil_moisture = '" + soilMoisture + '\'' + 
			",soil_temp = '" + soilTemp + '\'' + 
			"}";
		}
}