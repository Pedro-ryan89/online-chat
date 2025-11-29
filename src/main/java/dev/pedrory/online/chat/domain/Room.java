/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.pedrory.online.chat.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author pedro_ryan89
 */
public class Room {
    private final String roomId;
    private final String roomName;
    
    private final Set<String> userIds;
    private final List<String> messageIds;
    
    public Room(String roomName){
        this.roomName = roomName;
        this.roomId = UUID.randomUUID().toString();
        this.messageIds = new CopyOnWriteArrayList<>();
        this.userIds = new ConcurrentSkipListSet<>();
    }
    
    public Set<String> getuserIds(){return Collections.unmodifiableSet(userIds);}
    public List<String>getmessageIds(){return Collections.unmodifiableList(messageIds);}
    public String getroomId(){return roomId;}
    public String getroomName(){return roomName;}
    
    
    public void addUser(String userId){
        if (userId != null){
            userIds.add(userId);
            
        }
    }
    
    public void removeUser(String userId){
        userIds.remove(userId);
    }
    
    public void addMessage(String messageId){
        if (messageId !=  null && !messageId.isBlank()){
            messageIds.add(messageId);
        }
    }
    
    
}
