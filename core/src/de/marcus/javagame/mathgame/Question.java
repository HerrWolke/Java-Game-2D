package de.marcus.javagame.mathgame;

import java.util.Random;

public class Question {

    private double left;
    private double right;
    static final Random RAND = new Random(System.currentTimeMillis());
    private Operator operator;


    public Question (double left, double right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Question (int max) {
        this(randInt(max), randInt(max), Operator.randomOperator());
    }

    public Question() {
        this(10);
    }

    public String askQuest () {
        return String.format("What is %s ", operator.expression(left,right));
    }

    public String explain (boolean correct) {
        return correct ? "Correct" : String.format("Falsch, es ist: %.2f", calculate());
    }

    public boolean makeGuess (double guess) {
        return compareDouble(guess, calculate(), 0.01);
    }

    public double calculate () {
        return operator.calculate(left, right);
    }

    public String toString () {
        return String.format("%s = %.2f", operator.expression(left, right), calculate());
    }

    public static boolean compareDouble (double expected, double actual, double threshold) {
        return Math.abs(expected - actual) < threshold;
    }

    private static double randInt (int range) {
        return Math.floor(RAND.nextDouble() * range);
    }
































}
