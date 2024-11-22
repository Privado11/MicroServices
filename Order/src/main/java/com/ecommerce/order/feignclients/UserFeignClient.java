package com.ecommerce.order.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.order.dto.users.UserDto;

@FeignClient(name = "user", url = "http://localhost:8083")
@RequestMapping("/api/v1/users")
public interface UserFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<UserDto> findById(@PathVariable Long id);

}
