/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI;

import TiraLab.Controllers;
import TiraLab.Controllers.Move;
import TiraLab.GameLogic.WinDecider;

/**
 *
 * @author ColdFish
 */
public class SillyAI extends GameAI implements AIntf {

    private Move alwaysReturns;

    public SillyAI(String type) {
        super.AiType = "SillyAI-" + type;
        WinDecider d = new WinDecider();
        alwaysReturns = d.convertMove(type);
    }

    @Override
    public Controllers.Move giveMove() {
        super.aiPreviousMove = alwaysReturns;
        return alwaysReturns;
    }

    @Override
    public void placeMove(String playerMove) {
        // DO nothing..
    }

}
