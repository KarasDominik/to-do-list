package com.karasdominik.useraccount.web;

import com.karasdominik.useraccount.UserAccountManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
class UserAccountController {

    private final UserAccountManagement management;
    private final UserAccountRequestMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    CreateUserResponse create(@RequestBody CreateUserAccountRequest request) {
        return CreateUserResponse.of(management.create(mapper.asCommand(request)));
    }
}
