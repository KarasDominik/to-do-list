package com.karasdominik.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karasdominik.task.dto.exception.InvalidContentException;
import com.karasdominik.task.dto.exception.TaskNotFoundException;
import com.karasdominik.useraccount.dto.exception.EmailAlreadyUsedException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ObjectMapper mapper;

    @ExceptionHandler(InvalidContentException.class)
    @ResponseStatus(BAD_REQUEST)
    public void handle(HttpServletResponse response, InvalidContentException exception) throws IOException {
        response.getWriter().write(create(exception.message()));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public void handle(HttpServletResponse response, TaskNotFoundException exception) throws IOException {
        response.getWriter().write(create(exception.message()));
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    @ResponseStatus(CONFLICT)
    public void handle(HttpServletResponse response, EmailAlreadyUsedException exception) throws IOException {
        response.getWriter().write(create(exception.message()));
    }

    private String create(String message) throws JsonProcessingException {
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("errorMessage", message);
        return mapper.writeValueAsString(errorBody);
    }
}
