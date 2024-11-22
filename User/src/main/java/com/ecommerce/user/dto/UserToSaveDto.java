package com.ecommerce.user.dto;

public record UserToSaveDto(Long id, String username, String password, String email) {
}
