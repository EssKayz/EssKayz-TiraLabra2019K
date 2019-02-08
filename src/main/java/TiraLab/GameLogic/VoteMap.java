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

    public Vote[] getVotes() {
        return votes;
    }

    public void put(Move m, double value) {
        for (Vote v : votes) {
            if (v.move.equals(m)) {
                v.votes += value;
            }
        }
    }

    public double get(Move m) {
        for (Vote v : votes) {
            if (v.move.equals(m)) {
                return v.votes;
            }
        }
        return -1;
    }

}
