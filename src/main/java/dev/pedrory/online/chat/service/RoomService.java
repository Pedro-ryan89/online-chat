/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.pedrory.online.chat.service;

import dev.pedrory.online.chat.domain.Room;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author pedro_ryan89
 */
public class RoomService {
    private final Map<String, Room> rooms = new ConcurrentHashMap<>();

    public Room create(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Room name cannot be empty");
        }

        if (findByName(name).isPresent()) {
            throw new IllegalArgumentException("Room name is in use");
        }

        Room room = new Room(name);
        rooms.put(room.getroomId(), room);
        return room;
    }


    public Optional<Room> findById(String id) {
        return Optional.ofNullable(rooms.get(id));
    }

    public Optional<Room> findByName(String name) {
        return rooms.values().stream()
                .filter(r -> r.getroomName().equalsIgnoreCase(name))
                .findFirst();
    }


  
    public void addUserToRoom(String roomId, String userId) {

        Room room = rooms.get(roomId);

        if (room == null) {
            throw new IllegalArgumentException("Room does not exist");
        }

        room.addUser(userId);
    }


  
    public void removeUserFromRoom(String roomId, String userId) {

        Room room = rooms.get(roomId);

        if (room == null) {
            throw new IllegalArgumentException("Room does not exist");
        }

        room.removeUser(userId);
    }


    public Map<String, Room> listRooms() {
        return Map.copyOf(rooms);
    }
    
}
