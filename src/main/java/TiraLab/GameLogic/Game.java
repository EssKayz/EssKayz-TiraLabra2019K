/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.GameLogic;

import TiraLab.AI.*;
import TiraLab.Structures.RandomGen;

import TiraLab.Controllers.Move;

/**
 *
 * @author ColdFish
 */
public class Game {

    private int playerScore;
    private int aiScore;
    private int draws;

    private AIntf[] ais;
    private String sessionID;

    /**
     * Creates a new Game session
     *
     * @param session the identifier of the game session
     */
    public Game(String session) {
        this.playerScore = 0;
        this.aiScore = 0;
        this.draws = 0;
        this.sessionID = session;
        initAIs();
    }

    public void resetScore() {
        this.playerScore = 0;
        this.aiScore = 0;
        this.draws = 0;
        initAIs();
    }

    public void resetAIWins() {
        for (AIntf ai : ais) {
            ai.resetWins();
        }
    }

    public String getSessionID() {
        return sessionID;
    }

    public AIntf[] getAis() {
        return ais;
    }

    /**
     * Initialize the AI's to be used for the session
     */
    public final void initAIs() {
        ais = new AIntf[]{
            new RandomAI(),
            new MarkovChainOneMoveAI(),
            new AntiRotationAI(),
            new PlayerMirroringAI(),
            //
            new PathMatchAI(2),
            new PathMatchAI(5),
            //
            new PatternMatchAI(),
            new NovaAI()
        };
    }

    /**
     * Returns a Move, that is selected by a voting procedure by the initialized
     * AI interface implementations.
     *
     * @return returns the Move the AI's collectively chose
     */
    public Move getAIMove() {
        VoteMap map = new VoteMap();

        // Get a vote for each of the AI's
        System.out.println("Votes for session " + this.sessionID);
        for (AIntf ai : ais) {
            GameAI aitype = (GameAI) ai;
            Move selection = ai.giveMove();
            if (selection == null) {
                continue;
            }
            System.out.println(aitype.AiType + " votes for " + selection.name() + " before metastrategy");
            selection = aitype.getMetaStrategyModifiedMove(selection);

            double shortTerm = aitype.getShortTermWinRate();
            double winRate = (double) ai.getWins() / (ai.getWins() + playerScore);
            if (Double.isNaN(winRate)) {
                winRate = 0.0;
            }

            shortTerm = Math.floor((shortTerm * 100));
            winRate = Math.max((Math.floor((winRate * 100))), 0.1);

            if (winRate <= 47 && shortTerm <= 20 || shortTerm <= 10 && (winRate < 52)) {
                System.out.println(aitype.AiType + "'s Winrate was too low (" + winRate + " : " + shortTerm + ") - skipping vote");
                System.out.println("");
                continue;
            }

            System.out.println(aitype.AiType + " votes for " + selection.toString() + " with a short term winrate of " + (shortTerm) + "%, and a long term winrate of " + winRate + "%");

            // Increases the weighting of votes for AI's that are performing well!
            if (winRate > 57 && shortTerm > 20) {
                winRate += 15;
            }
            if (winRate > 65 && shortTerm > 25) {
                winRate += winRate / 3;
            }
            if (shortTerm >= 60) {
                winRate *= 1.25;
                shortTerm += 2;
            }

            winRate *= Math.max((shortTerm), 0.005);
            System.out.println(aitype.AiType + " influenced vote on " + selection.toString() + " by " + winRate + " points");

            System.out.println("");

            map.put(selection, winRate);
        };

        System.out.println("");
        int totalVotes = 0;
        for (Vote entry : map.getVotes()) {
            totalVotes += Math.max(0, entry.getValue());
            System.out.println(entry.getKey() + " got " + entry.getValue() + " votes");
        }

        double rock = Math.max(map.get(Move.ROCK), 0);
        double paper = Math.max(map.get(Move.PAPER), 0);
        double scissors = Math.max(map.get(Move.SCISSORS), 0);

        System.out.println("");
        System.out.println("Rock : 0 - " + rock);
        System.out.println("Paper : " + rock + " - " + (rock + paper));
        System.out.println("Scissors : " + (rock + paper) + " - " + (scissors + rock + paper));

        // Return a weighted random outcome, weights based on AI votes (So if no AI votes rock, rock will have a 0% probability to ocur, for example - or if all AI's agree on Rock - Rock will be 100% etc..)
        RandomGen r = new RandomGen();
        int x = r.getRandomInt(totalVotes);
        System.out.println("Randomizer got : " + x);
        System.out.println("");
        Move given;
        if (x < rock) {
            given = Move.ROCK;
        } else if (x < (paper + rock)) {
            given = Move.PAPER;
        } else {
            given = Move.SCISSORS;
        }

        Move chosen = given;
        System.out.println("Chosen move to be played was : " + chosen.toString());

        // Make AI random for the first 6 rounds while other AI's have a chance to gather data
        if (aiScore + playerScore + draws <= 6) {
            System.out.println("First five rounds, returning random move!");
            RandomAI randy = new RandomAI();
            return randy.giveMove();
        }

        return chosen;
    }

    /**
     * Gives the move that the player played to all the the AI's, so they can
     * adjust their decision making in the future
     *
     * @param movemove to be placed to the AI's memory (those that use the
     * information)
     */
    public void placeMove(String move) {
        for (AIntf ai : ais) {
            ai.placeMove(move);
        }
    }

    /**
     * Increase player score by one, and tell all the AI's what the winning move
     * against player would have been, so they can check if their vote would
     * have won the round, and adjust accordingly.
     *
     * @param aiWinningMove the move that would have won for the AI
     */
    public void playerWins(Move aiWinningMove) {
        for (AIntf ai : ais) {
            ai.increaseWinRating(aiWinningMove);
        }
        playerScore++;
    }

    /**
     * Increase draws by one, and tell all the AI's what the winning move
     * against player would have been, so they can check if their vote would
     * have won the round, and adjust accordingly.
     *
     * @param aiWinningMove the move that would have won for the AI
     */
    public void draw(Move aiWinningMove) {
        for (AIntf ai : ais) {
            ai.increaseWinRating(aiWinningMove);
        }
        draws++;
    }

    /**
     * Increase AI score by one, and tell all the AI's what the winning move
     * against player was, so they can check if their vote would have won the
     * round, and adjust accordingly.
     *
     * @param aiWinningMove the move that would have won for the AI
     */
    public void playerLoses(Move aiWinningMove) {
        for (AIntf ai : ais) {
            ai.increaseWinRating(aiWinningMove);
        }
        aiScore++;
    }

    /**
     * returns the AI score
     *
     * @return
     */
    public int getAiScore() {
        return aiScore;
    }

    /**
     * returns the amount of draws
     *
     * @return
     */
    public int getDraws() {
        return draws;
    }

    /**
     * returns the player's score
     *
     * @return
     */
    public int getPlayerScore() {
        return playerScore;
    }
}
