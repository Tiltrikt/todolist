package dev.tiltrikt.todolist.service.user;

import dev.tiltrikt.todolist.dto.user.UserRegistrationRequest;
import dev.tiltrikt.todolist.exception.UserException;
import dev.tiltrikt.todolist.model.User;
import dev.tiltrikt.todolist.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @NotNull
    public User addUser(@NotNull UserRegistrationRequest request) throws UserException {

        Optional<User> optionalUser = userRepository.findUserByUsername(request.getUsername());
        if (optionalUser.isPresent())
            throw new UserException("user with username: " + request.getUsername() + " already exists");

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return userRepository.save(user);
    }

    @NotNull
    public User getUser(@NotNull String username) throws UserException {

        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserException("no user with username: " + username + " found"));
    }
}
