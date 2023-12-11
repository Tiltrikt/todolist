package dev.tiltrikt.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "task_list")
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {

    @Id
    int id;

    String text;

    @Builder.Default
    boolean active = true;

}

