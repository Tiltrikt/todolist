package dev.tiltrikt.todolist.service.user;

import dev.tiltrikt.todolist.dto.user.UserRegistrationRequest;
import dev.tiltrikt.todolist.exception.UserException;
import dev.tiltrikt.todolist.model.User;
import org.jetbrains.annotations.NotNull;

public interface UserService {

    @NotNull
    User addUser(@NotNull UserRegistrationRequest request) throws UserException;

    @NotNull
    User getUser(@NotNull String username) throws UserException;
}
