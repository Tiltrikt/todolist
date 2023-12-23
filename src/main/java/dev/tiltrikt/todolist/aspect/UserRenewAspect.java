package dev.tiltrikt.todolist.aspect;

import dev.tiltrikt.todolist.security.ActualUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRenewAspect {

    UserDependent userDependent;

    ActualUser actualUser;

    @Before("execution(* dev.tiltrikt.todolist.service.task.TaskServiceMultipleUser.*(..)) " +
            "&& !execution(public void setUser(*))")
    public void renewUser() {

        actualUser.setActualUser(userDependent);
    }
}
