package com.ecommerce.user.service;
 


import java.util.List;

import com.ecommerce.user.dto.UserDto;
import com.ecommerce.user.dto.UserToSaveDto;

public interface UserService {
    UserDto save(UserToSaveDto userToSaveDto);
    UserDto update(Long id, UserToSaveDto userDto);
    UserDto findById(Long id);
    List<UserDto> findAll();
    void deleteById(Long id);
}
