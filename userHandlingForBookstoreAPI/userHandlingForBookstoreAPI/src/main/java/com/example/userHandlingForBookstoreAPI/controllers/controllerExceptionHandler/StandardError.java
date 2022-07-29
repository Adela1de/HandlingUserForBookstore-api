package com.example.userHandlingForBookstoreAPI.controllers.controllerExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private String message;
    private Integer status;
    private Long timeStamp;

}
