package dev.tiltrikt.todolist.repository;

import dev.tiltrikt.todolist.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

  List<Token> findAllByRevokedFalseAndUserId(Integer user);

  Optional<Token> findByToken(String token);
}

