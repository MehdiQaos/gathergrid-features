package com.gathergrid.gathergridfeatures.service;

import com.gathergrid.gathergridfeatures.domain.User;
import com.gathergrid.gathergridfeatures.repository.interfacesImpl.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class UserService {
    private final UserRepository<User> userRepository;

    public UserService(UserRepository<User> userRepository1) {
        this.userRepository = userRepository1;
    }

    public Optional<User> save(User user){
        Optional<User> byEmail = userRepository.findByEmail(user);
        if (byEmail.isEmpty()) {
            userRepository.save(user);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
    public Optional<User> CheckEmail(User user){
        Optional<User> user1 = userRepository.findByEmail(user);
        if(user1.isPresent()) {
            if(BCrypt.checkpw(user.getPassword(), user1.get().getPassword())) {
                return user1;
            }
        }
       throw new RuntimeException("This user Not Found");
    }

    public User getById(long id) {
        return userRepository.findById(User.class, id);
    }
}

