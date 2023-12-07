package dev.tiltrikt.todolist;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Task {

  int id;

  String text;

  @NonFinal
  @Builder.Default
  boolean active = true;

  public boolean active() {
    return active;
  }

  public boolean finished() {
    return !active;
  }

  public void finish() {
    active = false;
  }

}
