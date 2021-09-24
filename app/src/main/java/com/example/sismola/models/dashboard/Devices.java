package com.example.sismola.models.dashboard;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Devices{

	@SerializedName("data_device")
	private List<DataDeviceItem> dataDevice;

	@SerializedName("info_device")
	private InfoDevice infoDevice;

	@SerializedName("current_data_device")
	private CurrentDataDevice currentDataDevice;

	public void setDataDevice(List<DataDeviceItem> dataDevice){
		this.dataDevice = dataDevice;
	}

	public List<DataDeviceItem> getDataDevice(){
		return dataDevice;
	}

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
			"Devices{" + 
			"data_device = '" + dataDevice + '\'' + 
			",info_device = '" + infoDevice + '\'' + 
			",current_data_device = '" + currentDataDevice + '\'' + 
			"}";
		}
}