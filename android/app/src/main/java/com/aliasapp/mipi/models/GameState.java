package com.aliasapp.mipi.models;

/**
 * Created by aliasapps on 15-03-21.
 */
public class GameState {
    private static String currentUserInput;
    private int correctGuesses;
    private int lives;

    public enum States {CORRECT, WRONG, GAMEOVER}

    private States currentState;

    public GameState() {
        currentState = States.CORRECT;
        currentUserInput = "";
        correctGuesses = 0;
        lives = 3;
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
}
