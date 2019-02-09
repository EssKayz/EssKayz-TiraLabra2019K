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
public class Vote {

    public double votes;
    public Move move;

    /**
     * Creates a new Vote object for a move
     * @param move The move the vote is for
     */
    public Vote(Move move) {
        this.votes = 0;
        this.move = move;
    }

    /**
     *
     * @return returns the amount of votes for the move
     */
    public double getValue() {
        return votes;
    }

    /**
     * get the name of the Move the vote is for
     * @return returns the name of the move
     */
    public String getKey() {
        return move.name();
    }

}
