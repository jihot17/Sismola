package com.example.sismola.models.devices;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GDevices{

	@SerializedName("success")
	private boolean success;

	@SerializedName("response")
	private Response response;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"GDevices{" + 
			"success = '" + success + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}