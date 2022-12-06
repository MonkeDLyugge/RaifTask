package com.lyugge.raif_task.controller;

import com.lyugge.raif_task.domain.ErrorResponce;
import com.lyugge.raif_task.exceptions.IncorrectParametersException;
import com.lyugge.raif_task.exceptions.NullResultException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectParametersException.class)
    public ErrorResponce onIncorrectParameters(IncorrectParametersException exception){
        return new ErrorResponce("Incorrect parameters", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Для возврата клиенту вместо null - json c сообщением, что ничего не найдено
    @ExceptionHandler(NullResultException.class)
    public ErrorResponce onNullResult(NullResultException exception){
        return new ErrorResponce("Null result", exception.getMessage());
    }

}
