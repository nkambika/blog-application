package com.myprojects.exceptions;

public class NoContentsAvailableException extends RuntimeException{
    public NoContentsAvailableException(String message){
        super(message);
    }
}
