package ua.levelup.Homework16.annotations;

import ua.levelup.Homework16.Generator;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MatrixProperties.class)
public @interface MatrixProperty {
    Class<? extends Generator<?>> type();
    int rows();
    int column();
}