package dev.tiltrikt.todolist.security;

import dev.tiltrikt.todolist.model.User;
import dev.tiltrikt.todolist.repository.UserRepository;
import dev.tiltrikt.todolist.aspect.UserDependent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ActualUser {

    UserRepository userRepository;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void setActualUser(UserDependent userDependent) {

        try {

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userDependent.setUser(userRepository.findUserByUsername(user.getUsername()).get());
        } catch (ClassCastException exception) {

            // standard user for single user application (only for /v1/** controller)
            userDependent.setUser(userRepository.findUserByUsername("user").get());
        }
    }
}
