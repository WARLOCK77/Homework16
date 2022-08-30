package ua.levelup.Homework16;

public interface Generator<T extends Number>{
    T getNextRand();
    Class<T> getType();
}
