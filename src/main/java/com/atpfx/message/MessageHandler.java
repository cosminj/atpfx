package com.atpfx.message;

public interface MessageHandler<T> {

    void handle(T message);

    String fxcmType();
}