package ua.levelup.Homework16;


import ua.levelup.Homework16.annotations.MatrixProperty;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMatrixWithTreads {


    @MatrixProperty(type = IntegerGenerator.class, rows=3, column=3)
    @MatrixProperty(type = DoubleGenerator.class, rows=3, column=2)
    public <T extends Number> void testMatrix1(Generator<T> generator, int rows, int columns) {
        Matrix<T> matrix = new Matrix<>(generator,rows,columns);
        matrix.print();
    }


    public static void main(String[] args) {
        TestMatrixWithTreads testMatrixHomework = new TestMatrixWithTreads();
        testMatrix(testMatrixHomework);
        System.out.println("Матрицы с многопоточностью");
    }

    public static void testMatrix(Object object) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Method[] declaredMethods = object.getClass().getDeclaredMethods();
        long time = System.nanoTime();
        executor.execute(new TestTread(declaredMethods,object));
        executor.shutdown();
        System.out.println("Total time="+((System.nanoTime()-time)/1_000)+"mсs");
    }
}