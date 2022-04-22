package com.example.trafficmi.Model;

public class LogInModel {

    String username, password, users;

    LogInModel(){

    }

   public LogInModel(String username, String password){
        this.username = username;
        this.password = password;
        this.users = users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
