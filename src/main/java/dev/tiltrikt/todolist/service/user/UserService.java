package dev.tiltrikt.todolist.service.user;

import dev.tiltrikt.todolist.dto.user.UserAddRequest;
import dev.tiltrikt.todolist.exception.UserException;
import dev.tiltrikt.todolist.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void delete(User user);

    void add(@NotNull UserAddRequest request) throws UserException;
}
