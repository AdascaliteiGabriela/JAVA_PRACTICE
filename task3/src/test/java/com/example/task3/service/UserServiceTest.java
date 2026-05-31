package com.example.task3;

package com.example.task3.service;

import com.example.task3.model.Role;
import com.example.task3.model.Users;
import com.example.task3.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Test 1: Successful registration
    @Test
    void register_shouldCreateUser_whenUsernameIsNew() {

        // GIVEN
        when(userRepository.findByUsername("test"))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode("1234"))
                .thenReturn("hashedPassword");

        // WHEN
        Users result = userService.register("test", "1234");

        // THEN
        assertNotNull(result);
        assertEquals("test", result.getUsername());
        assertEquals("hashedPassword", result.getPassword());
        assertEquals(Role.ROLE_USER, result.getRole());

        verify(userRepository, times(1)).save(any(Users.class));
    }

    // ✅ Test 2: Duplicate username
    @Test
    void register_shouldThrowException_whenUserAlreadyExists() {

        // GIVEN
        Users existing = new Users();
        existing.setUsername("test");

        when(userRepository.findByUsername("test"))
                .thenReturn(Optional.of(existing));

        // WHEN + THEN
        assertThrows(RuntimeException.class, () -> {
            userService.register("test", "1234");
        });

        verify(userRepository, never()).save(any());
    }
}
