package dev.pedrory.online.chat.service;

import dev.pedrory.online.chat.domain.User;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    public User create(String name) {
        // validações e criação de usuário 
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        
        Optional<User> existingUser = findByName(name);
        if(existingUser.isPresent()){
            throw new IllegalArgumentException("There is already a user with that name.");
        }
        
        User user = new User(name);
        users.put(user.getId(),user);
        return user;
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
   }

    public Optional<User> findByName(String name) {
        return users.values().stream()
                .filter(u -> u.getName().equalsIgnoreCase(name)).findFirst();
    }

    public void remove(String id) {
        users.remove(id);
    }
}
