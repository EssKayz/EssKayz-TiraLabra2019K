/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI;

import TiraLab.Controllers;
import TiraLab.Controllers.Move;

/**
 *
 * @author ColdFish
 */
public class RandomAI extends GameAI implements AIntf {
    /**
     * Creates an AI that returns a random Move enum
     */
    public RandomAI() {
        super.AiType = "RandomAI";
    }

    @Override
    public Controllers.Move giveMove() {
        Move random = super.returnRandomMove();
        super.aiPreviousMove = random;
        return random;
    }

    @Override
    public void placeMove(String playerMove) {
        // Player moves dont matter when throwing random guessess
    }

}
