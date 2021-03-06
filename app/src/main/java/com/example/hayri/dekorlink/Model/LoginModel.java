package com.example.hayri.dekorlink.Model;

public class LoginModel {
    private String message;
    private String uyeID;
    private int isSuccess;
    private String username;


    public String getUyeID() {
        return uyeID;
    }

    public void setUyeID(String uyeID) {
        this.uyeID = uyeID;
    }

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

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "message='" + message + '\'' +
                ", uyeID='" + uyeID + '\'' +
                ", isSuccess=" + isSuccess +
                ", username='" + username + '\'' +
                '}';
    }

}
