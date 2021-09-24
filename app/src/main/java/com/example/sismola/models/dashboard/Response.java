package com.example.sismola.models.dashboard;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Response{

	@SerializedName("owner_email")
	private String ownerEmail;

	@SerializedName("owner_name")
	private String ownerName;

	@SerializedName("devices")
	private Devices devices;

	public void setOwnerEmail(String ownerEmail){
		this.ownerEmail = ownerEmail;
	}

	public String getOwnerEmail(){
		return ownerEmail;
	}

	public void setOwnerName(String ownerName){
		this.ownerName = ownerName;
	}

	public String getOwnerName(){
		return ownerName;
	}

	public void setDevices(Devices devices){
		this.devices = devices;
	}

	public Devices getDevices(){
		return devices;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"owner_email = '" + ownerEmail + '\'' + 
			",owner_name = '" + ownerName + '\'' + 
			",devices = '" + devices + '\'' + 
			"}";
		}
}