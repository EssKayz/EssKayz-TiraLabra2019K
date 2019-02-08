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

    public MetaStrategy() {
        decider = new WinDecider();
    }

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

    public Move getMove(Move defaultMove) {
        previousMove = defaultMove;
        return defaultMove;
    }
}
