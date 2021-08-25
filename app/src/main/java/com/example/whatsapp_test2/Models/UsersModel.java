package com.example.whatsapp_test2.Models;

public class UsersModel {

    String pic,name,email,password,userID,lastMessage;

    public UsersModel() {
    }

    public UsersModel(String pic) {
        this.pic = pic;
    }

    public UsersModel(String pic, String name, String email, String password, String userID, String lastMessage) {
        this.pic = pic;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userID = userID;
        this.lastMessage = lastMessage;
    }


    public UsersModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

//    public UsersModel(String name, String email, String password,String pic) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.pic = pic;
//    }


    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

}
