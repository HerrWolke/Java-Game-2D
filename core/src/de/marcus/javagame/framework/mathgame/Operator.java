package de.marcus.javagame.framework.mathgame;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Operator {

    ADD ("+", (left, right) -> left + right),
    SUB ("-", (left, right) -> left - right),
    MUL ("*", (left, right) -> left * right),
    DIV ("/", (left, right) -> left / right);

    private static final Random RAND = new Random(System.currentTimeMillis());
    private final Random rand = new Random(System.currentTimeMillis());
    private static final List <Operator> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();

    public String symbol;
    public Operation operation;

    private Operator (String symbol, Operation operation) {
        this.symbol = symbol;
        this.operation = operation;
    }
    public static Operator randomOperator () {
        return VALUES.get(RAND.nextInt(SIZE));
    }


    public double calculate (double left, double right) {
        return operation.calculate(left,right);
    }

    public String expression (double left, double right) {
        return String.format("%.2f %s %.2f", left, symbol, right);
    }

    @Override
    public String toString () {
        return symbol;
    }


}
