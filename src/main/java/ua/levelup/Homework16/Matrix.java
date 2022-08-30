package ua.levelup.Homework16;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Matrix<T extends Number> {

    T[][] matrix;

    public Matrix(Generator<T> generator, int rows, int columns){
       matrix=(T[][])Array.newInstance(generator.getType(), rows, columns);
       fillMatrix(generator);
    }


    public void fillMatrix(Generator<T> generator){
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[i].length; j++){
                matrix[i][j]= generator.getNextRand();
            }
        }
    }

    public void print (){
        System.out.println(Arrays.deepToString(matrix));
    }
}
