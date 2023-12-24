package dev.tiltrikt.todolist.configuration;

import dev.tiltrikt.todolist.dto.user.UserRegistrationRequest;
import dev.tiltrikt.todolist.model.User;
import dev.tiltrikt.todolist.repository.UserRepository;
import dev.tiltrikt.todolist.service.authentication.AuthenticationService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserConfiguration implements CommandLineRunner {

    AuthenticationService authenticationService;

    UserRepository userRepository;

    @Getter
    @NonFinal
    User defaultUser;

    @Override
    public void run(String... args) {

        try {
            //noinspection OptionalGetWithoutIsPresent
            defaultUser = userRepository.findUserByUsername("user").get();
        } catch (Exception ex) {

            authenticationService.register(new UserRegistrationRequest("user", "pass"));
            //noinspection OptionalGetWithoutIsPresent
            defaultUser = userRepository.findUserByUsername("user").get();
        }

    }
}
