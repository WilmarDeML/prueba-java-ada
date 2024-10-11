package com.wilmar.examen_java.commons;

import java.util.List;

public class CommonResponse <T> {
    private String message;
    private boolean status;
    private T content;

    public CommonResponse(String message, boolean status, T content) {
        this.message = message;
        this.status = status;
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
