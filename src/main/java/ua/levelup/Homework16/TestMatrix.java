package ua.levelup.Homework16;


import ua.levelup.Homework16.annotations.MatrixProperties;
import ua.levelup.Homework16.annotations.MatrixProperty;

import java.lang.reflect.Method;

public class TestMatrix {


    @MatrixProperty(type = IntegerGenerator.class, rows=3, column=3)
    @MatrixProperty(type = DoubleGenerator.class, rows=3, column=2)
    public <T extends Number> void testMatrix1(Generator<T> generator, int rows, int columns) {
        Matrix<T> matrix = new Matrix<>(generator,rows,columns);
        matrix.print();
    }


    public static void main(String[] args) {
        TestMatrix testMatrix = new TestMatrix();
        testMatrix(testMatrix);
        System.out.println("Матрицы без многопоточности");
    }

    public static void testMatrix(Object object) {
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        long time = System.nanoTime();
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
        System.out.println("Total time="+((System.nanoTime()-time)/1_000)+"mсs");
    }
}