package com.github.benchdoos.weblocopenerstatistics.controllers;

import com.github.benchdoos.weblocopenerstatistics.domain.User;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.UserLoginDto;
import com.github.benchdoos.weblocopenerstatistics.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/notify/{uuid}")
    public void notifyUserLogin(@PathVariable UUID uuid,
                                @RequestBody UserLoginDto userLogin) {
        userService.notifyLogin(uuid, userLogin);
    }

    @GetMapping("/{uuid}")
    public User getUserByUuid(UUID uuid) {
        return userService.getUserByUuid(uuid);
    }

    @GetMapping("/all")
    public Page<User> getAllUsers(@PageableDefault Pageable page) {
        return userService.getAllUsers(page);
    }
}
