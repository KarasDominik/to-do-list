package com.karasdominik.useraccount.dto.exception;

import com.karasdominik.common.ConflictedRequestException;

public class EmailAlreadyUsedException extends ConflictedRequestException {

    public EmailAlreadyUsedException(String email) {
        super(String.format("Email %s is already in use", email));
    }
}
