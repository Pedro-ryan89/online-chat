/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.pedrory.online.chat.domain;

import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author pedro_ryan89
 */
public class User {
    private final String id;
    private String name;
    private String currentRoomId;
    
    public User(String name,String currentRoomId){
        this.currentRoomId = currentRoomId;
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }
    
    public String getName(String name){return name;}
    public void setName(String name){this.name = name;}
    public String getId(){return this.id;}
    public Optional<String> getCurrentRoomId(){return Optional.ofNullable(currentRoomId);}
    public void setCurrentRoomId(String roomId){this.currentRoomId = currentRoomId;}
    
    
    
}
