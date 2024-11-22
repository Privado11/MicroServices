package com.ecommerce.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.user.dto.UserDto;
import com.ecommerce.user.dto.UserMapper;
import com.ecommerce.user.dto.UserToSaveDto;
import com.ecommerce.user.exception.NotAbleToDeleteException;
import com.ecommerce.user.exception.NotFoundExceptionEntity;
import com.ecommerce.user.models.User;
import com.ecommerce.user.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDto save(UserToSaveDto userToSaveDto) {
        User user = userMapper.toSaveDto(userToSaveDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto update(Long id, UserToSaveDto userDto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDto.username());
                    user.setEmail(userDto.email());
                    user.setPassword(userDto.password());

                    return userMapper.toDto(userRepository.save(user));
                })
                .orElseThrow(() -> new NotFoundExceptionEntity("User not found"));
    }

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundExceptionEntity("User not found"));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("User not found"));
        userRepository.delete(user);
    }
}
