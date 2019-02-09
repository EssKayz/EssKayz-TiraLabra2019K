/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.GameLogic;

import TiraLab.Controllers;
import TiraLab.Controllers.Move;

/**
 *
 * @author ColdFish
 */
public class VoteMap {

    public Vote[] votes = new Vote[]{new Vote(Controllers.Move.ROCK), new Vote(Controllers.Move.PAPER), new Vote(Controllers.Move.SCISSORS)};

    
    public VoteMap() {
    }

    /**
     * Get votes
     * @return returns an array of the Vote objects
     */
    public Vote[] getVotes() {
        return votes;
    }

    /**
     * Add a vote to a move
     * @param m the move
     * @param value the value of the vote to be added to the move m
     */
    public void put(Move m, double value) {
        for (Vote v : votes) {
            if (v.move.equals(m)) {
                v.votes += value;
            }
        }
    }

    /**
     * get votes for Move m
     * @param m the move to get votes for
     * @return returns the vote value for the Move
     */
    public double get(Move m) {
        for (Vote v : votes) {
            if (v.move.equals(m)) {
                return v.votes;
            }
        }
        return -1;
    }

}
