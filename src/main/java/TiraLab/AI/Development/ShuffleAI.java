package TiraLab.AI.Development;

import TiraLab.AI.AIntf;
import TiraLab.AI.GameAI;
import TiraLab.Controllers.Move;

public class ShuffleAI extends GameAI implements AIntf {

    int siirto;

    public ShuffleAI() {
        siirto = 0;
        super.AiType = "ShuffleAI";
    }

    @Override
    public Move giveMove() {
        siirto++;
        siirto = siirto % 3;
        Move given;
        switch (siirto) {
            case 0:
                given = Move.SCISSORS;
                break;
            case 1:
                given = Move.PAPER;
                break;
            default:
                given = Move.ROCK;
                break;
        }
        super.aiPreviousMove = given;
        return given;
    }

    @Override
    public void placeMove(String playerMove) {
        // do nothing
    }

}
