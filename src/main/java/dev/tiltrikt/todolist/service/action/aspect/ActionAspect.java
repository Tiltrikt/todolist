package dev.tiltrikt.todolist.service.action.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ActionAspect {

    @After("execution(public void execute(java.util.List<String>))")
    private void afterAction() {
        System.out.print("tl > ");
    }
}
