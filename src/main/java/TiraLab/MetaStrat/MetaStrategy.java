/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.MetaStrat;

import TiraLab.Controllers.Move;
import TiraLab.GameLogic.WinDecider;

/**
 *
 * @author ColdFish
 */
public class MetaStrategy {

    public double score;
    public WinDecider decider;
    public Move previousMove;

    /**
     * A class that follows how the player plays, as in - if the player is actively trying to counter the AI, an metastrategy can be implemented to counter the counter.
     */
    public MetaStrategy() {
        decider = new WinDecider();
    }

    /**
     * Take the move that the AI would have otherwise thrown, but rotate the pattern by the 'rotations' amount. 
     * Eg. Rock rotated by one, becomes Paper. By Two - Scissors. By three? Rock again.
     * @param rotations the amount of rotations to be made
     * @param defaultMove the move that you start from
     * @return the move after the rotations
     */
    public Move getMoveByRotating(int rotations, Move defaultMove) {
        Move returnable = defaultMove;
        for (int i = 0; i < rotations; i++) {
            switch (returnable) {
                case PAPER: {
                    returnable = Move.SCISSORS;
                    break;
                }
                case ROCK: {
                    returnable = Move.PAPER;
                    break;
                }
                default: {
                    returnable = Move.SCISSORS;
                    break;
                }
            }
        }
        return returnable;
    }

    /**
     * get the Move the metastrategy selected
     * @param defaultMove the default Move to return
     * @return 
     */
    public Move getMove(Move defaultMove) {
        previousMove = defaultMove;
        return defaultMove;
    }
}
