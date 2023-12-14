package dev.tiltrikt.todolist.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Data
@Builder
@AllArgsConstructor
@Table(schema = "todolist", name = "tasks")
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    String text;

    @Builder.Default
    boolean active = true;

}

