package dev.tiltrikt.todolist.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserAddRequest {

    @NotBlank(message = "Username is mandatory")
    String username;

    @NotBlank(message = "Password is mandatory")
    String password;
}
