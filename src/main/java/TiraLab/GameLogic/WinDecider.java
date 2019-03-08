/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.GameLogic;

import TiraLab.Controllers.Move;

/**
 *
 * @author ColdFish
 */
public class WinDecider {

    /**
     * decide wether the player wins the AI or not.
     * @param playerMoves the player Move
     * @param AiMove the AI move
     * @return 1 if the player wins, -1 if the AI wins, 0 if it is a draw.
     */
    public int playerWins(Move playerMoves, Move AiMove) {
        switch (playerMoves) {
            case ROCK: {
                if (AiMove == Move.SCISSORS) {
                    return 1;
                }
                if (AiMove == Move.PAPER) {
                    return -1;
                }
                break;
            }

            case PAPER: {
                if (AiMove == Move.ROCK) {
                    return 1;
                }
                if (AiMove == Move.SCISSORS) {
                    return -1;
                }
                break;
            }

            case SCISSORS: {
                if (AiMove == Move.PAPER) {
                    return 1;
                }
                if (AiMove == Move.ROCK) {
                    return -1;
                }
                break;
            }
        }

        return 0;
    }

    /**
     * return the Move that beats the Move moveToBeat
     * @param moveToBeat move to beat
     * @return Move that wins the parameter Move
     */
    public Move getMoveThatBeats(Move moveToBeat) {
        switch (moveToBeat) {
            case PAPER: {
                return Move.SCISSORS;
            }
            case ROCK: {
                return Move.PAPER;
            }
            default: {
                return Move.ROCK;
            }
        }
    }

    /**
     * Return a Move based on input string
     * @param move the String to parse
     * @return a Move parsed by "Rock", "Paper" or "Scissors"
     */
    public Move convertMove(String move) {
        switch (move) {
            case "Rock": {
                return Move.ROCK;
            }
            case "Paper": {
                return Move.PAPER;

            }
            default: {
                return Move.SCISSORS;
            }
        }
    }
}
