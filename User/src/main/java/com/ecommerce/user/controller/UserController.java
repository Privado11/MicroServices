package com.ecommerce.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.user.dto.UserDto;
import com.ecommerce.user.dto.UserToSaveDto;
import com.ecommerce.user.exception.NotFoundExceptionEntity;
import com.ecommerce.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> save(@RequestBody UserToSaveDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        try {
            UserDto carDto = userService.findById(id);
            return ResponseEntity.ok(carDto);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") Long id, @RequestBody UserToSaveDto userDto) {
        try {
            UserDto carDto = userService.update(id, userDto);
            return ResponseEntity.ok(carDto);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
