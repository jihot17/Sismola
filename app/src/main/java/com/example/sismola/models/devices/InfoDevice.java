package com.example.sismola.models.devices;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class InfoDevice{

	@SerializedName("device_name")
	private String deviceName;

	@SerializedName("device_serial")
	private String deviceSerial;

	@SerializedName("device_info")
	private Object deviceInfo;

	@SerializedName("device_id")
	private String deviceId;

	@SerializedName("device_location")
	private String deviceLocation;

	public void setDeviceName(String deviceName){
		this.deviceName = deviceName;
	}

	public String getDeviceName(){
		return deviceName;
	}

	public void setDeviceSerial(String deviceSerial){
		this.deviceSerial = deviceSerial;
	}

	public String getDeviceSerial(){
		return deviceSerial;
	}

	public void setDeviceInfo(Object deviceInfo){
		this.deviceInfo = deviceInfo;
	}

	public Object getDeviceInfo(){
		return deviceInfo;
	}

	public void setDeviceId(String deviceId){
		this.deviceId = deviceId;
	}

	public String getDeviceId(){
		return deviceId;
	}

	public void setDeviceLocation(String deviceLocation){
		this.deviceLocation = deviceLocation;
	}

	public String getDeviceLocation(){
		return deviceLocation;
	}

	@Override
 	public String toString(){
		return 
			"InfoDevice{" + 
			"device_name = '" + deviceName + '\'' + 
			",device_serial = '" + deviceSerial + '\'' + 
			",device_info = '" + deviceInfo + '\'' + 
			",device_id = '" + deviceId + '\'' + 
			",device_location = '" + deviceLocation + '\'' + 
			"}";
		}
}