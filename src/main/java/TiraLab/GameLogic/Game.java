/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.GameLogic;

import TiraLab.AI.AIntf;
import TiraLab.AI.RandomAI;
import TiraLab.AI.RememberingAI;
import TiraLab.AI.ShuffleAI;
import TiraLab.Controllers.Move;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author ColdFish
 */
public class Game {

    private int playerScore;
    private int aiScore;
    private int rounds;
    private List<AIntf> ais;

    public Game() {
        this.playerScore = 0;
        this.aiScore = 0;
        this.rounds = 0;
        initAIs();
    }

    public int getRounds() {
        return rounds;
    }

    public final void initAIs() {
        ais = new ArrayList<>();
        ais.add(new ShuffleAI());
        ais.add(new RememberingAI(10));
        ais.add(new RandomAI());
    }

    public Move getAIMove() {
        rounds++;
        HashMap<Move, Double> votes = new HashMap<>();
        votes.put(Move.ROCK, 0.0);
        votes.put(Move.PAPER, 0.0);
        votes.put(Move.SCISSORS, 0.0);

        for (AIntf ai : ais) {
            Move selection = ai.giveMove();
            double winRate = (double) ai.getWins() / rounds;
            switch (selection) {
                case PAPER:
                    votes.put(Move.PAPER, votes.get(Move.PAPER) + (1 * winRate));
                    break;
                case ROCK:
                    votes.put(Move.ROCK, votes.get(Move.ROCK) + (1 * winRate));
                    break;
                case SCISSORS:
                    votes.put(Move.SCISSORS, votes.get(Move.SCISSORS) + (1 * winRate));
                    break;
            }
        };

        return Collections.max(votes.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void placeMove(String move) {
        for (AIntf ai : ais) {
            ai.placeMove(move);
        }
    }

    public void playerWins(Move aiWinningMove) {
        for (AIntf ai : ais) {
            ai.increaseWinRating(aiWinningMove);
        }
        playerScore++;
    }

    public void draw(Move aiWinningMove) {
        for (AIntf ai : ais) {
            ai.increaseWinRating(aiWinningMove);
        }
        rounds--;
    }

    public void playerLoses(Move aiWinningMove) {
        for (AIntf ai : ais) {
            ai.increaseWinRating(aiWinningMove);
        }
        aiScore++;
    }

    public int getAiScore() {
        return aiScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public List<AIntf> getAis() {
        return ais;
    }

    public void setAis(List<AIntf> ais) {
        this.ais = ais;
    }

    public void setAiScore(int aiScore) {
        this.aiScore = aiScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

}
