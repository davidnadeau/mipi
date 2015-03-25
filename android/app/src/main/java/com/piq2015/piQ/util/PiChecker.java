package com.piq2015.piQ.util;

import com.piq2015.piQ.models.C;
import com.piq2015.piQ.models.GameState;

/**
 * Created by aliasapps on 15-03-21.
 */
public class PiChecker {
    private static GameState gameState;

    public PiChecker() {
        gameState = new GameState();
    }

    public GameState.States guess(int position) {
        char guess = KeypadAdapter.positionToNumber(position);
        if (C.PI.charAt(gameState.getIndex()) == guess)
            gameState.correctGuess(guess);
        else
            gameState.incorrectGuess();

        return gameState.getState();
    }

    public String getInput() {
        return gameState.getCurrentUserInput();
    }

    public boolean isPeeking(int position) {
        return KeypadAdapter.isPeek(position);
    }
}
