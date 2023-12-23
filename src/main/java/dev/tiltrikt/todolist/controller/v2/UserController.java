package dev.tiltrikt.todolist.controller.v2;

import dev.tiltrikt.todolist.dto.user.UserAddRequest;
import dev.tiltrikt.todolist.model.User;
import dev.tiltrikt.todolist.response.TodolistResponse;
import dev.tiltrikt.todolist.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @NotNull
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TodolistResponse<String> addUser(@Valid @RequestBody UserAddRequest request) {
        userService.add(request);

        return TodolistResponse.ok();
    }

    @NotNull
    @DeleteMapping("/delete")
    public TodolistResponse<String> deleteUser(@AuthenticationPrincipal User user) {
        userService.delete(user);

        return TodolistResponse.ok();
    }

}
