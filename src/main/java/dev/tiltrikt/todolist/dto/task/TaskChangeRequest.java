package dev.tiltrikt.todolist.dto.task;

import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TaskChangeRequest {

    String text;

    Boolean active;

    @AssertTrue(message = "At least one field must be not empty")
    public boolean isAtLeastOneVariableNotEmpty() {
        return text != null || active != null;
    }
}
