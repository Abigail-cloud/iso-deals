package com.deals.isodeals.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private String message;
    private String details;
    private Date timeStamps;
}
