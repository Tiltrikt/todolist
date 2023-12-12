package dev.tiltrikt.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = PRIVATE)
public class TaskDTO {

    @Positive(message = "Id must be a positive integer")
    int id;

    @NotBlank(message = "Text is mandatory")
    String text;

    @Builder.Default
    boolean active = true;
}
