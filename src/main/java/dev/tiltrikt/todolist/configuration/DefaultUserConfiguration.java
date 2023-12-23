package dev.tiltrikt.todolist.configuration;

import dev.tiltrikt.todolist.dto.user.UserAddRequest;
import dev.tiltrikt.todolist.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserConfiguration implements CommandLineRunner {

    UserService userService;

    @Override
    public void run(String... args) {

        try {
            userService.add(new UserAddRequest("user", "pass"));
        } catch (Exception ignored) {
        }
    }
}
