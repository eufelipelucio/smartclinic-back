package com.smartClinic.smartclinic.Exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsernameNotFoundExceptionMessage extends UsernameNotFoundException {
    
    public UsernameNotFoundExceptionMessage(String message) {
        super(message);
    }
}
