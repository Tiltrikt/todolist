package dev.tiltrikt.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "tasklist")
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Task {

    @Id
    int id;

    String text;

    @NonFinal
    @Builder.Default
    boolean active = true;
}

