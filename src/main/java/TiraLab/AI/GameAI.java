/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI;

import TiraLab.Controllers.Move;
import TiraLab.GameLogic.WinDecider;
import TiraLab.Structures.intQ;
import java.util.Random;

/**
 *
 * @author ColdFish
 */
public class GameAI {

    public int wins;
    public Move aiPreviousMove;
    public String AiType;
    private final int shortTermSpan = 10;
    public WinDecider decider;

    public intQ shortTermWins = new intQ(shortTermSpan);

    public GameAI() {
        this.decider = new WinDecider();
    }

    public void placeWin() {
        shortTermWins.dequeue();
        shortTermWins.enqueue(1);
    }

    public void placeLose() {
        shortTermWins.dequeue();
        shortTermWins.enqueue(0);
    }

    public double getShortTermWinRate() {
        return (double) shortTermWins.getContentSum() / shortTermSpan;
    }

    public void increaseWinRating(Move winningMove) {
        if (aiPreviousMove == winningMove) {
            wins++;
            placeWin();
        } else {
            placeLose();
        }
    }

    public Move returnRandomMove() {
        Random r = new Random();
        double d = r.nextDouble();
        Move given;
        if (d < 0.33) {
            given = Move.ROCK;
        } else if (d < 0.67) {
            given = Move.SCISSORS;
        } else {
            given = Move.PAPER;
        }

        return given;
    }

    public int getWins() {
        return wins;
    }
}
