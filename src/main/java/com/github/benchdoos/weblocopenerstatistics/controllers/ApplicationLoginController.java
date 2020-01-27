package com.github.benchdoos.weblocopenerstatistics.controllers;

import com.github.benchdoos.weblocopenerstatistics.domain.ApplicationLogin;
import com.github.benchdoos.weblocopenerstatistics.domain.dto.ApplicationLoginDto;
import com.github.benchdoos.weblocopenerstatistics.services.ApplicationsService;
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
@RequestMapping("/applications")
public class ApplicationLoginController {
    private final ApplicationsService applicationsService;

    @PostMapping("/notify/{uuid}")
    public void notifyApplicationLogin(@PathVariable UUID uuid,
                                       @RequestBody ApplicationLoginDto userLogin) {
        applicationsService.notifyLogin(uuid, userLogin);
    }

    @GetMapping("/{uuid}")
    public ApplicationLogin getApplicationByUuid(@PathVariable UUID uuid) {
        return applicationsService.getUserByUuid(uuid);
    }

    @GetMapping("/all")
    public Page<ApplicationLogin> getAllApplications(@PageableDefault Pageable page) {
        return applicationsService.getAllUsers(page);
    }
}
