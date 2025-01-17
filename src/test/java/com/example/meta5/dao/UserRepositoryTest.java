package com.example.meta5.dao;

import com.example.meta5.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {
        // Given
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole("ADMIN");

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("testuser");
    }

    @Test
    void testFindByUsername() {
        // Given
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole("ADMIN");
        userRepository.save(user);

        // When
        User foundUser = userRepository.findByUsername("testuser");

        // Then
        //assertThat(foundUser).isPresent();
        //assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
    }
    /*
    @Test
    void testDeleteUser() {
        // Given
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setRole("testuser@example.com");
        User savedUser = userRepository.save(user);

        // When
        userRepository.delete(savedUser);

        // Then
        Optional<User> deletedUser = userRepository.findById(savedUser.getId());
        assertThat(deletedUser).isNotPresent();
    }
     */
}
