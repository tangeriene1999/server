/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsh.steam.resources;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xiang
 */
@XmlRootElement
public class UserData {
    private String username;
    private String password;
    public UserData(){
        
    }
    public UserData(String username,String password){
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
