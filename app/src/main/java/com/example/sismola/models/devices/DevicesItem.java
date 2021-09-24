package com.example.sismola.models.devices;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DevicesItem{

	@SerializedName("info_device")
	private InfoDevice infoDevice;

	@SerializedName("current_data_device")
	private CurrentDataDevice currentDataDevice;

	public void setInfoDevice(InfoDevice infoDevice){
		this.infoDevice = infoDevice;
	}

	public InfoDevice getInfoDevice(){
		return infoDevice;
	}

	public void setCurrentDataDevice(CurrentDataDevice currentDataDevice){
		this.currentDataDevice = currentDataDevice;
	}

	public CurrentDataDevice getCurrentDataDevice(){
		return currentDataDevice;
	}

	@Override
 	public String toString(){
		return 
			"DevicesItem{" + 
			"info_device = '" + infoDevice + '\'' + 
			",current_data_device = '" + currentDataDevice + '\'' + 
			"}";
		}
}