package com.github.benchdoos.weblocopenerstatistics.services;

import com.github.benchdoos.weblocopenerstatistics.domain.User;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.UserLoginDto;
import com.github.benchdoos.weblocopenerstatistics.exceptions.UserNotFoundException;
import com.github.benchdoos.weblocopenerstatistics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private UserRepository userRepository;

    public void notifyLogin(UUID uuid, UserLoginDto userLogin) {
        final Optional<User> firstByUuid = userRepository.findById(uuid);
        if (firstByUuid.isPresent()) {
            final User user = firstByUuid.get();
            updateUserInfo(userLogin, user);
        } else {
            createNewUserForUuid(uuid, userLogin);
        }
    }

    private void createNewUserForUuid(UUID uuid, UserLoginDto userLogin) {
        final Date date = new Date();
        final User user = User.builder()
                .id(uuid)
                .countryCode(userLogin.getCountryName())
                .firstTimeSeen(date)
                .lastTimeSeen(date)
                .loginCounts(1L)
                .build();
        userRepository.save(user);
    }

    private void updateUserInfo(UserLoginDto userLogin, User user) {
        user.setLastTimeSeen(new Date());
        user.setCountryCode(userLogin.getCountryName());
        updateUserLoginCounts(user);
    }

    private void updateUserLoginCounts(User user) {
        if (user.getLoginCounts() != null) {
            user.setLoginCounts(user.getLoginCounts() + 1);
        } else {
            user.setLoginCounts(1L);
        }
    }

    public Page<User> getAllUsers(Pageable page) {
        return userRepository.findAll(page);
    }

    public User getUserByUuid(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(UserNotFoundException::new);
    }
}
