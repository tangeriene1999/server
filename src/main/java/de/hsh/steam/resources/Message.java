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
public class Message {
    private String message;
    public Message(){
        
    }

    public Message(String message){
        this.message=message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
