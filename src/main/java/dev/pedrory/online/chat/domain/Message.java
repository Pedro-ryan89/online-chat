package dev.pedrory.online.chat.domain;

import java.time.Instant;
import java.util.UUID;

public class Message {

    private final String id;
    private final String roomId;
    private final String userId;
    private final String content;
    private final Instant timestamp;
    private final MessageType type;

    //User esta null,lembrar de fazer uma validação futuramente para evitar quebra
    public Message(User user,String content, MessageType type) {
        this.id = UUID.randomUUID().toString();
        this.roomId = UUID.randomUUID().toString();          
        this.userId = user.getId();         
        this.content = content;
        this.type = type;              
        this.timestamp = Instant.now();
    }

    public String getId() { return id; }
    public String getRoomId() { return roomId; }
    public String getUserId() { return userId; }
    public String getContent() { return content; }
    public Instant getTimestamp() { return timestamp; }
    public MessageType getType() { return type; }
}
