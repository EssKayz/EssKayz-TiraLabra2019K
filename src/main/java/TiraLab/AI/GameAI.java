/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI;

import TiraLab.Structures.RandomGen;
import TiraLab.Controllers.Move;
import TiraLab.GameLogic.WinDecider;
import TiraLab.MetaStrat.*;
import TiraLab.Structures.ArrayLib;
import TiraLab.Structures.MathLib;
import TiraLab.Structures.StringMethods;
import TiraLab.Structures.intQ;

/**
 *
 * @author ColdFish
 */
public class GameAI {

    public double score = 0;
    public double confidence = 0.0;

    /**
     * Tracks the amount of wins for the AI during the current session, reset
     * when user wants to reset his game
     */
    public int wins;

    // Libraries for all AI's to use for string manipulation etc
    public MathLib mathLib;
    public StringMethods Stringmeth;
    public ArrayLib arrLib;

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
     * Creates an WinDecider object that classes will inherit to use the helper
     * methods from
     */
    public WinDecider decider;

    /**
     * Creates a Queue that tracks the win history with the length of
     * shortTermSpan
     */
    public intQ shortTermWins = new intQ(shortTermSpan);

    private MetaStrategy[] metaStrats = new MetaStrategy[]{new P0(), new P1(), new P2()};

    /**
     * Creates a superClass that AI's extend to inherit shared methods from
     */
    public GameAI() {
        this.decider = new WinDecider();
        this.Stringmeth = new StringMethods();
        this.mathLib = new MathLib();
        this.arrLib = new ArrayLib();
    }

    public Move getMetaStrategyModifiedMove(Move move) {
        MetaStrategy best = new P0();
        best.score = 0.001;
        // Select the best metastragey by score
        for (MetaStrategy strat : metaStrats) {
            strat.previousMove = strat.getMove(move);
            if (strat.score > best.score) {
                best = strat;
            }
        }
        Move selected = best.getMove(move);
        aiPreviousMove = selected;
        return selected;
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
    public void resetWins() {
        this.wins = 0;
    }

    /**
     * Returns the AI's short term memory Win Rate
     *
     * @return returns the short term winrate of the AI in %
     */
    public double getShortTermWinRate() {
        return (double) shortTermWins.getContentSum() / shortTermSpan;
    }

    public double getScore() {
        return (score + confidence) + (getShortTermWinRate() * wins);
    }

    /**
     * Checks if would have AI won, and places a win or loss accordingly to
     * short term memory
     *
     * @param winningMove the move that would have won the player
     */
    public void increaseWinRating(Move winningMove) {
        for (MetaStrategy strat : metaStrats) {
            if (strat.previousMove == winningMove) {
                strat.score++;
                strat.score *= 0.9;
            } else {
                strat.score--;
                strat.score *= 0.75;
            }
            if (strat.score < 0) {
                strat.score = 0;
            }
        }

        if (aiPreviousMove == winningMove) {
            confidence++;
            score += (confidence + 2);
            score *= 0.95;
            wins++;
            placeWin();
        } else {
            confidence *= 0.25;
            score--;
            score *= 0.75;
            placeLose();
        }
        if (score < 0) {
            score = 0;
        }
    }

    /**
     * Returns a random Move
     *
     * @return Returns a random Move
     */
    public Move returnRandomMove() {
        RandomGen r = new RandomGen();
        int d = r.getRandomInt(4);
        Move given;
        if (d < 1) {
            given = Move.ROCK;
        } else if (d < 2) {
            given = Move.SCISSORS;
        } else {
            given = Move.PAPER;
        }

        return given;
    }

    /**
     * returns AI score
     *
     * @return
     */
    public int getWins() {
        return wins;
    }
}
