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

    /**
     * Tracks the amount of wins for the AI during the current session, reset when user wants to reset his game
     */
    public int wins;

    /**
     * Tracks the previous move of the AI
     */
    public Move aiPreviousMove;

    /**
     * Defines the type of AI, used for debugging and showing statistics per AI
     */
    public String AiType;
    private final int shortTermSpan = 10;

    /**
     * Creates an WinDecider object that classes will inherit to use the helper methods from
     */
    public WinDecider decider;

    /**
     * Creates a Queue that tracks the win history with the length of shortTermSpan
     */
    public intQ shortTermWins = new intQ(shortTermSpan);

    /**
     * Creates a superClass that AI's extend to inherit shared methods from
     */
    public GameAI() {
        this.decider = new WinDecider();
    }

    /**
     * Places a win into the AI's short term memory Queue
     */
    public void placeWin() {
        shortTermWins.dequeue();
        shortTermWins.enqueue(1);
    }

    /**
     * Places a loss into the AI's short term memory Queue
     */
    public void placeLose() {
        shortTermWins.dequeue();
        shortTermWins.enqueue(0);
    }
    
    /**
     * Reset the WinCounter of the AI
     */
    public void resetWins(){
        this.wins = 0;
    }

    /**
     * Returns the AI's short term memory Win Rate
     * @return 
     */
    public double getShortTermWinRate() {
        return (double) shortTermWins.getContentSum() / shortTermSpan;
    }

    /**
     * Checks if would have AI won, and places a win or loss accordingly to short term memory
     * @param winningMove 
     */
    public void increaseWinRating(Move winningMove) {
        if (aiPreviousMove == winningMove) {
            wins++;
            placeWin();
        } else {
            placeLose();
        }
    }

    
    /**
     * Returns a random Move
     * @return 
     */
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

    /**
     * returns AI score
     * @return 
     */
    public int getWins() {
        return wins;
    }
}
