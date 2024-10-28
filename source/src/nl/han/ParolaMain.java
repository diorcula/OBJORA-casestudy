package nl.han;

import java.util.Scanner;

public class ParolaMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parola parola = Parola.getInstance();

        System.out.println("Enter your player name: ");
        String playername = scanner.nextLine();

        System.out.println("Welcome to Parola, " + playername + ", select the quiz you want to play: " + parola.getQuizzes());
        String quizName = scanner.nextLine();

        System.out.println("The 8-question quiz starts. Good luck!");
        parola.startQuiz(playername, quizName);

        do {
            System.out.println(parola.nextQuestion());
            System.out.print("Give your answer to this question: ");
            String answer = scanner.nextLine();
            parola.processAnswer(answer);
        } while (!parola.quizFinished());

        System.out.println("You've earned the following letters: " + parola.getLettersForRightAnswers(playername));
        System.out.print("Make a word, as long as possible, that contains these letters: ");
        String word = scanner.nextLine();
//
        int score = parola.calculateScore(playername, word);
        System.out.println("Score: " + score);
    }
}
