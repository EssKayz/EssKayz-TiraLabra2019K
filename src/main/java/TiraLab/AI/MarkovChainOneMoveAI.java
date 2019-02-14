/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI;

import TiraLab.Structures.RandomGen;
import TiraLab.Controllers;
import TiraLab.Controllers.Move;

/**
 *
 * @author ColdFish
 */
public class MarkovChainOneMoveAI extends GameAI implements AIntf {

    private float[][] markovChain;
    private int[] timesPlayed;

    private int lastMove; //last move of the human player
    private int moveBeforeLast; //move before last of the human player

    /**
     * Initialized new Markov Chain AI that tracks past one move
     */
    public MarkovChainOneMoveAI() {
        super.AiType = "MarkovChainAI";
        markovChain = new float[][]{{0.33f, 0.33f, 0.33f}, {0.33f, 0.33f, 0.33f}, {0.33f, 0.33f, 0.33f}};
        timesPlayed = new int[]{0, 0, 0};
    }

    @Override
    public Move giveMove() {
        RandomGen r = new RandomGen();
        float ranFloat = r.getRandomFloat();
        Move selected;

        // compare random value to the value for lastMove (0 = rock, 1 = paper, 2 = scissors) in the markov chain, and return a countering move for the most likely human selection
        if (ranFloat <= markovChain[lastMove][1]) {
            selected = Move.SCISSORS;
        } else if (ranFloat <= markovChain[lastMove][2] + markovChain[lastMove][1]) {
            selected = Move.ROCK;
        } else {
            selected = Move.PAPER;
        }
        super.aiPreviousMove = selected;
        return selected;
    }

    @Override
    public void placeMove(String playerMove) {
        moveBeforeLast = lastMove;

        // Place the last player move to the lastMove parameter
        switch (playerMove) {
            case "Rock":
                lastMove = 0;
                break;
            case "Paper":
                lastMove = 1;
                break;
            default:
                lastMove = 2;
                break;
        }

        // Multiply everything in column i of the Markov Chain by timesPlayed[moveBeforeLast]
        for (int i = 0; i < 3; i++) {
            markovChain[moveBeforeLast][i] *= timesPlayed[moveBeforeLast];
        }

        // Increment row value we want by one 
        markovChain[moveBeforeLast][lastMove] += 1;

        //Increment the amount of times played by one
        timesPlayed[moveBeforeLast]++;

        //Divide all values in the markovChain column J by timesPlayed[moveBeforeLast] to get a fraction value representing how often an value is selected
        for (int j = 0; j < 3; j++) {
            markovChain[moveBeforeLast][j] /= timesPlayed[moveBeforeLast];
        }

        System.out.println("Markov chain :");
        System.out.println("Rock to Rock: " + markovChain[0][0] + " Rock to Paper: " + markovChain[0][1] + " Rock to Scissors: " + markovChain[0][2]);
        System.out.println("Paper to Rock: " + markovChain[1][0] + " Paper to Paper: " + markovChain[1][1] + " Paper to Scissors: " + markovChain[1][2]);
        System.out.println("Scissors to Rock: " + markovChain[2][0] + " Scissors to Paper: " + markovChain[2][1] + " Scissors to Scissors: " + markovChain[2][2]);
    }

}
