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
}
