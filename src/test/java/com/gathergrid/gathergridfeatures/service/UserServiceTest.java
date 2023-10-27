package com.gathergrid.gathergridfeatures.service;

import com.gathergrid.gathergridfeatures.domain.User;
import com.gathergrid.gathergridfeatures.repository.interfacesImpl.CommentRepositryImpl;
import com.gathergrid.gathergridfeatures.repository.interfacesImpl.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService;
    UserRepository<User> userRepository;


    @BeforeEach
    void setup(){
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void save() {
        User  user = new User("youssef", "MAti","hi@gmail.com","1234");
        Mockito.when(userService.save(user).orElseThrow());
        Optional<User> save = userService.save(user);
        assertEquals(save.get(),user);
    }

    @Test
    void checkEmail() {
        String email="hi@gmail.com";
        User  user = new User("youssef", "MAti"
                ,"hi@gmail.com","1234");
        Optional<User> save = userService.CheckEmail(user);
        assertThrows(IllegalArgumentException.class,()->userService.CheckEmail(user),"This user Not found");
    }
    @Test
    public void testCheckEmailValidUser() {
        // Arrange
        User  user = new User("youssef", "MAti","hi@gmail.com","1234");

        User userInput = new User();
        userInput.setEmail("");

        Mockito.when(userRepository.findByEmail(userInput)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.CheckEmail(userInput);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testCheckEmailInvalidUser() {

        // Arrange
        User userInput = new User("testuser@example.com", "password");

        Mockito.when(userRepository.findByEmail(userInput)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> userService.CheckEmail(userInput));
    }

    @Test
    public void testCheckEmailWrongPassword() {
        // Arrange
        User user = new User("testuser@example.com", "passwordHash");
        User userInput = new User("testuser@example.com", "wrongPassword");

        Mockito.when(userRepository.findByEmail(userInput)).thenReturn(Optional.of(user));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> userService.CheckEmail(userInput));
    }

    @Test
    void getById() {
    }




    @Test
    public void testSaveNewUser() {
        // Arrange


    }

    @Test
    public void testSaveExistingUser() {
        // Arrange
     User user = new User("youssef", "MAti","hi@gmail.com","1234");

    }
}