package com.ecommerce.user.dto;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ecommerce.user.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    User toSaveDto(UserToSaveDto userToSaveDto);

}
