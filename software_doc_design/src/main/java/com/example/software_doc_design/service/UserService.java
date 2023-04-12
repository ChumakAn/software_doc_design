package com.example.software_doc_design.service;

import com.example.software_doc_design.domain.User;
import com.example.software_doc_design.mappers.UserMapper;
import com.example.software_doc_design.persistance.entity.UserEntity;
import com.example.software_doc_design.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return userMapper.toDomain(userRepository.save(userEntity));
    }

    public User getByLoginAndPassword(String login, String password) {
        UserEntity userEntity = userRepository.getByLoginAndPassword(login, password);
        if (userEntity == null) {
            throw new EntityNotFoundException("User with login " + login + " and password " + password + " not found");
        }
        return userMapper.toDomain(userEntity);
    }

    public List<User> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDomain).collect(Collectors.toList());
    }

    public User getById(Long id) {
        UserEntity userEntity = userRepository.getReferenceById(id);
        return userMapper.toDomain(userEntity);
    }

    public User update(Long id, User user) {
        if (userRepository.existsById(id)) {
            UserEntity userEntity = userMapper.toEntity(user);
            userEntity.setId(id);
            userRepository.save(userEntity);
            return userMapper.toDomain(userEntity);
        }
        return null;
    }

    public User delete(Long id) {
        if (userRepository.existsById(id)) {
            UserEntity userEntity = userRepository.getReferenceById(id);
            userRepository.delete(userEntity);
            return userMapper.toDomain(userEntity);
        }
        return null;
    }
}

