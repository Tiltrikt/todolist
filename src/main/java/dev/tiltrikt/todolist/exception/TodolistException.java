package dev.tiltrikt.todolist.exception;

public abstract class TodolistException extends RuntimeException{

    public TodolistException(String message) {
        super(message);
    }
}
