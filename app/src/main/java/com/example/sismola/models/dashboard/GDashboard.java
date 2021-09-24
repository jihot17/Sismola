package com.example.sismola.models.dashboard;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GDashboard{

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
			"GDashboard{" + 
			"success = '" + success + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}