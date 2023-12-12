package dev.tiltrikt.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(schema = "todoilist", name = "tasks")
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {

  @Id
  int id;

  @NotNull
  String text;

  @Builder.Default
  boolean active = true;

}

