package dev.pedrory.online.chat.domain;

import java.util.Optional;
import java.util.UUID;

public class User {

    private final String id;
    private String name;
    private String currentRoomId;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.currentRoomId = null;
    }

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}
    public String getId() {return this.id;}
    public Optional<String> getCurrentRoomId() {return Optional.ofNullable(this.currentRoomId);}
    public void setCurrentRoomId(String roomId) {this.currentRoomId = roomId;}
}
