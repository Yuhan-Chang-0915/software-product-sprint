package com.google.sps.data;

public class FormData {
    private String name, email, message;
    private Long timestamp;
    public FormData(String name, String email, String message, Long timestamp){
        this.name = name;
        this.email = email;
        this.message = message;
        this.timestamp = timestamp;
    }
    
}
