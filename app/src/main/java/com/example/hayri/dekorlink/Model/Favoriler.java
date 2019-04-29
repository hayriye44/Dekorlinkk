package com.example.hayri.dekorlink.Model;

public class Favoriler {
	private String message;
	private int isSuccess;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setIsSuccess(int isSuccess){
		this.isSuccess = isSuccess;
	}

	public int getIsSuccess(){
		return isSuccess;
	}

	@Override
 	public String toString(){
		return 
			"Favoriler{" + 
			"message = '" + message + '\'' + 
			",isSuccess = '" + isSuccess + '\'' + 
			"}";
		}
}
