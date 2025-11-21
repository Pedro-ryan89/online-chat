/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package dev.pedrory.online.chat.domain;

/**
 *
 * @author pedro_ryan89
 */
public enum MessageType {
    NORMAL("normal"),
    SERVER("server"),
    JOIN("join"),
    LEAVE("leave");
    
    final private String value;
    
    MessageType(String value){
        this.value = value;
    }
    
    
}
