package com.piq2015.piQ.models;

/**
 * Created by aliasapps on 15-03-21.
 */
public class GameState {
    private static String currentUserInput;
    private int correctGuesses;
    private static int lives;
    private static int peeks;

    public static int getLives() {
        return lives;
    }

    public static int getPeeks() {
        return peeks;
    }

    public enum States {CORRECT, WRONG, GAMEOVER}

    private States currentState;

    public GameState() {
        currentState = States.CORRECT;
        currentUserInput = "";
        correctGuesses = 0;
        lives = 3;
        peeks = 3;
    }

    public String getCurrentUserInput() {
        return currentUserInput;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public void correctGuess(char guess) {
        currentUserInput += "" + guess;
        correctGuesses++;
        currentState = States.CORRECT;
    }

    public int getIndex() {
        return currentUserInput.length();
    }

    public void incorrectGuess() {
        lives--;
        if (lives > 0)
            currentState = States.WRONG;
        else
            currentState = States.GAMEOVER;
    }

    public static int getCorrectCount() {
        return currentUserInput.length();
    }

    public States getState() {
        return currentState;
    }

    public static boolean peek() {
        if (peeks > 0) {
            --peeks;
            return true;
        }
        return false;
    }
}
