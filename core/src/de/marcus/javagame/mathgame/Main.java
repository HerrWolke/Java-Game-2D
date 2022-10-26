package de.marcus.javagame.mathgame;

import com.badlogic.gdx.math.Interpolation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int correctCount = 0;
    private static final List<Question> questions = randomQuestions(10);


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        questions.stream().forEach(question -> ask(question, input));
        input.close();

    }

    private static void ask (Question question, Scanner input) {
        System.out.println(question.askQuest());
        double guess = input.nextDouble();
        boolean isCorrect = question.makeGuess(guess);
        System.out.println(question.explain(isCorrect));
        System.out.println();
        correctCount += isCorrect ? 1 : 0;
    }

    private static void stats() {
        double percentage = (correctCount * 1.0d) / questions.size() * 100;
        System.out.printf("Correct: %.2f%% (%d/%d)%n", percentage, correctCount, questions.size());
    }

    private static List<Question> randomQuestions(int count) {
        List<Question> questions = new ArrayList<Question>();
        while (count --> 0) questions.add(new Question());
        return questions;
    }
}

































