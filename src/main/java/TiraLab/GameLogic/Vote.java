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

    public Vote(Move move) {
        this.votes = 0;
        this.move = move;
    }

    public double getValue() {
        return votes;
    }

    public String getKey() {
        return move.name();
    }

}
