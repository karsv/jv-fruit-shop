package com.solution;

import com.solution.controller.FruitController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext("com.solution");
        FruitController bean = ctx.getBean(FruitController.class);
        bean.run(args[0]);
    }
}
