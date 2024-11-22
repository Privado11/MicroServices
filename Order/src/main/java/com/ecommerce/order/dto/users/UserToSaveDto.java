package com.ecommerce.order.dto.users;

public record UserToSaveDto(Long id, String username, String password, String email) {
}
