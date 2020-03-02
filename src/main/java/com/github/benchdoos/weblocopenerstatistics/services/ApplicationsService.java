package com.github.benchdoos.weblocopenerstatistics.services;

import com.github.benchdoos.weblocopenerstatistics.domain.ApplicationLogin;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.ApplicationLoginDto;
import com.github.benchdoos.weblocopenerstatistics.exceptions.ApplicationNotFoundException;
import com.github.benchdoos.weblocopenerstatistics.repository.ApplicationLoginRepository;
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
public class ApplicationsService {
    private final ApplicationLoginRepository applicationLoginRepository;

    public void notifyLogin(UUID uuid, ApplicationLoginDto userLogin) {
        final Optional<ApplicationLogin> firstByUuid = applicationLoginRepository.findById(uuid);
        if (firstByUuid.isPresent()) {
            final ApplicationLogin applicationLogin = firstByUuid.get();
            updateUserInfo(userLogin, applicationLogin);
        } else {
            final ApplicationLogin newApplicationLoginForUuid = createNewUserForUuid(uuid, userLogin);
            log.info("User created with uuid: {}. User: {}", uuid, userLogin);
        }
    }

    private ApplicationLogin createNewUserForUuid(UUID uuid, ApplicationLoginDto userLogin) {
        final ApplicationLogin applicationLogin = ApplicationLogin.builder()
                .id(uuid)
                .countryCode(userLogin.getCountryName())
                .selectedLanguage(userLogin.getSelectedLanguage())
                .applicationVersion(userLogin.getApplicationVersion())
                .loginCounts(1L)
                .build();
        return applicationLoginRepository.save(applicationLogin);
    }

    private void updateUserInfo(ApplicationLoginDto userLogin, ApplicationLogin applicationLogin) {
        applicationLogin.setLastTimeSeen(new Date());
        applicationLogin.setCountryCode(userLogin.getCountryName());
        applicationLogin.setSelectedLanguage(userLogin.getSelectedLanguage());
        applicationLogin.setApplicationVersion(userLogin.getApplicationVersion());
        updateUserLoginCounts(applicationLogin);
        applicationLoginRepository.save(applicationLogin);
    }

    private void updateUserLoginCounts(ApplicationLogin applicationLogin) {
        if (applicationLogin.getLoginCounts() != null) {
            applicationLogin.setLoginCounts(applicationLogin.getLoginCounts() + 1);
        } else {
            applicationLogin.setLoginCounts(1L);
        }
    }

    public Page<ApplicationLogin> getAllUsers(Pageable page) {
        return applicationLoginRepository.findAll(page);
    }

    public ApplicationLogin getUserByUuid(UUID uuid) {
        return applicationLoginRepository.findById(uuid).orElseThrow(ApplicationNotFoundException::new);
    }
}
