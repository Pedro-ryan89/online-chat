/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.pedrory.online.chat.service;

import dev.pedrory.online.chat.domain.Message;
import dev.pedrory.online.chat.domain.MessageType;
import dev.pedrory.online.chat.domain.Room;
import dev.pedrory.online.chat.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author pedro_ryan89
 */
public class MessageService {
    private final Map<String, List<Message>> messagesByRoom = new ConcurrentHashMap<>();

    private final RoomService roomService;
    private final UserService userService;

    public MessageService(RoomService roomService, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }

    
    // Envia mensagem para uma sala.
     
    public Message sendMessage(String roomId, String userId, String content, MessageType type) {

        //1. Validar sala
        Room room = roomService.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found: " + roomId));

        //2. Validar usuário
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        //3. Validar conteúdo
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Message content cannot be empty");
        }

        //4. Criar a mensagem
        Message message = new Message(roomId, user, content, type);

        //5. Salvar em estrutura thread-safe
        messagesByRoom
                .computeIfAbsent(roomId, id -> new CopyOnWriteArrayList<>())
                .add(message);

        //6. Adicionar a mensagem à sala
        room.addMessage(message.getId());

        return message;
    }

    //Lista todas as mensagens de uma salas
    public List<Message> getMessagesByRoom(String roomId) {
        return messagesByRoom.getOrDefault(roomId, List.of());
    }

    //Busca todas as mensagens enviadas por um usuário.
   
    public List<Message> getMessagesByUser(String userId) {
        List<Message> result = new ArrayList<>();

        // percorre todas as salas
        messagesByRoom.values().forEach(list ->
                list.stream()
                        .filter(m -> m.getUserId().equals(userId))
                        .forEach(result::add)
        );

        return result;
    }

    // Remove todas as mensagens de uma sala.
     
    public void clearRoom(String roomId) {
        messagesByRoom.remove(roomId);
    }
    
}
