package dev.tiltrikt.todolist.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UniverseResponse<T> {

    T payload;

    Map<String, String> errors;

    @Builder.Default
    Date timestamp = new Date();

    @Builder.Default
    boolean success = true;
}