package dev.tiltrikt.todolist.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthenticationRequest {

  @NotBlank(message = "Username is mandatory")
  String username;

  @NotBlank(message = "Password is mandatory")
  String password;
}
