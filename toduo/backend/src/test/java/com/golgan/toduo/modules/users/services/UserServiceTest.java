package com.golgan.toduo.modules.users.services;

import com.golgan.toduo.core.services.PasswordService;
import com.golgan.toduo.modules.users.dto.UserCreateDto;
import com.golgan.toduo.modules.users.dto.UserUpdateDto;
import com.golgan.toduo.modules.users.mappers.UserMapper;
import com.golgan.toduo.modules.users.models.UserEntity;
import com.golgan.toduo.modules.users.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private UserService userService;


    private UserEntity user;
    private final String email = "test@example.com";
    private final Long userId = 1L;
    private final String name = "testus";


    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setId(userId);
        user.setEmail(email);
        user.setFirstName(name);
        user.setLastName(name);
    }



    @Test
    void getByEmail() {
        when(userRepository.findByEmail(email)).thenReturn(user);

        UserEntity result = userService.getByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(userRepository).findByEmail(email);
    }

    @Test
    void findById() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserEntity result = userService.findById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
    }

    @Test
    void findAll() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserEntity> result = userService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }


    @Test
    void create() {
        UserCreateDto dto = new UserCreateDto(email, "rawPassword", "Петр", "Иванов");

        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(userMapper.toEntity(dto)).thenReturn(user);
        when(passwordService.encode("rawPassword")).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity created = userService.create(dto);

        assertNotNull(created);
        verify(userRepository).save(user);
        verify(passwordService).encode("rawPassword");
    }

    @Test
    void update() {
        UserUpdateDto dto = new UserUpdateDto("Иван", "Петров");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        UserEntity updated = userService.update(userId, dto);

        assertNotNull(updated);
        verify(userMapper).update(dto, user);
        verify(userRepository).save(user);
    }

    @Test
    void deleteById() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Настраиваем заглушку на метод delete(Object), а не deleteById
        doNothing().when(userRepository).delete(user);

        assertDoesNotThrow(() -> userService.deleteById(userId));

        // Проверяем реальные взаимодействия с репозиторием
        verify(userRepository).findById(userId);
        verify(userRepository).delete(user);
    }

    @Test
    void getUserOrNotFound() {

        UserEntity result = userService.getUserOrNotFound(user);

        assertNotNull(result);
        assertEquals(userId, result.getId());
    }
}