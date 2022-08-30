package ua.levelup.Homework16;

import ua.levelup.Homework16.annotations.MatrixProperties;
import ua.levelup.Homework16.annotations.MatrixProperty;

import java.lang.reflect.Method;

public class TestTread implements Runnable{

    TestTread(Method [] declaredMethods, Object object){
        for (Method method : declaredMethods) {
            MatrixProperties matrixProperties = method.getAnnotation(MatrixProperties.class);
            if (matrixProperties == null) {
                continue;
            }
            for (MatrixProperty matrixProperty : matrixProperties.value()){
                try {
                    Generator<?> generator=matrixProperty.type().getDeclaredConstructor().newInstance();
                    method.invoke(object, generator, matrixProperty.rows(), matrixProperty.column());
                } catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }


    @Override
    public void run() {

    }
}
