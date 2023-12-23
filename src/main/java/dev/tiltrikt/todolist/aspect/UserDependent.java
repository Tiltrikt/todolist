package dev.tiltrikt.todolist.aspect;

import dev.tiltrikt.todolist.model.User;

public interface UserDependent {

    void setUser(User user);
}
