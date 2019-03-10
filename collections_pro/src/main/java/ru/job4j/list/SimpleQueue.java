package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * @author shaplov
 * @since 09.03.2019
 */
public class SimpleQueue<E> {

    private SimpleStackClass<E> inputStack;
    private SimpleStackClass<E> outputStack;
    private int inputSize = 0;
    private int outputSize = 0;

    public SimpleQueue() {
        this.inputStack = new SimpleStackClass<>();
        this.outputStack = new SimpleStackClass<>();
    }

    public void push(E value) {
        inputStack.push(value);
        inputSize++;
    }

    public E pull() {
        if (outputSize == 0) {
            while (inputSize > 0) {
                outputStack.push(inputStack.pull());
                inputSize--;
                outputSize++;
            }
        }
        if (outputSize == 0) {
            throw new NoSuchElementException();
        }
        outputSize--;
        return outputStack.pull();
    }
}
