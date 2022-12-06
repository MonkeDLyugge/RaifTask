package com.lyugge.raif_task.domain;

import lombok.Data;

@Data
public class ErrorResponce {
    private final String code;
    private final String message;
}
