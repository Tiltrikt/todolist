package dev.tiltrikt.todolist.service.user;

import com.github.dozermapper.core.Mapper;
import dev.tiltrikt.todolist.dto.user.UserAddRequest;
import dev.tiltrikt.todolist.exception.UserException;
import dev.tiltrikt.todolist.model.User;
import dev.tiltrikt.todolist.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    Mapper mapper;

    PasswordEncoder encoder;

    @NotNull
    @Override
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {

        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("no user with username: " + username + " found"));
    }

    @Override
    public void delete(@NotNull User user) {

        userRepository.delete(user);
    }

    @Override
    public void add(@NotNull UserAddRequest request) throws UserException {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserException("User with username " + request.getUsername() + " already exists");
        }

        User user = mapper.map(request, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
