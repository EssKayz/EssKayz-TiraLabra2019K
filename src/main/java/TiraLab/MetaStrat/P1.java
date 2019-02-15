/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.MetaStrat;

import TiraLab.Controllers.Move;

/**
 *
 * @author ColdFish
 */
public class P1 extends MetaStrategy {

    /**
     * Returns a move rotated by one
     * @param defaultMove the move to be rotated
     * @return the move gotten after rotation by one
     */
    @Override
    public Move getMove(Move defaultMove) {
        super.previousMove = super.getMoveByRotating(1, defaultMove);
        return super.previousMove;
    }

}
