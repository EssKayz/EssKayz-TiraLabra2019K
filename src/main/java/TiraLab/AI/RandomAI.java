/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI;

import TiraLab.Controllers;
import TiraLab.Controllers.Move;
import java.util.Random;

/**
 *
 * @author ColdFish
 */
public class RandomAI extends GameAI implements AIntf {

    Random r = new Random();

    public RandomAI() {
        super.AiType = "RandomAI";
    }

    
    
    @Override
    public Controllers.Move giveMove() {
        double d = r.nextDouble();
        Move given;
        if (d < 0.33) {
            given = Move.ROCK;
        } else if (d < 0.67) {
            given = Move.SCISSORS;
        } else {
            given = Move.PAPER;
        }
        super.lastMove = given;
        return given;
    }

    @Override
    public void placeMove(String playerMove) {
        // Player moves dont matter when throwing random guessess
    }

    @Override
    public void increaseWinRating(Move winningMove) {
        if (super.lastMove == winningMove) {
            super.wins++;
        }
    }
}
