package dev.tiltrikt.todolist.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAuthenticationToken extends AbstractAuthenticationToken {

    Object principal;

    @SuppressWarnings("unchecked")
    public UserAuthenticationToken(Object principal) {

        super(Collections.EMPTY_LIST);
        super.setAuthenticated(true);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
