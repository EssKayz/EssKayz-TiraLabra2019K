/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI;

import TiraLab.Controllers;
import TiraLab.Controllers.Move;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author ColdFish
 */
public class NovaAI extends GameAI implements AIntf {

    private Move playerPrevMove;
    private String playerMoveHistory = "";
    private String playerWinHistory = "";
    private int playerWonLastRound = 0;
    private int strategySwaps = 0;
    private int strategyStays = 0;

    /**
     * Creates an AI that records the history of the player's moves and score
     * history, and attempts to counter the longest recognized repeated pattern
     * where the player has won with a similar pattern
     */
    public NovaAI() {
        super.AiType = "NovaAI";
    }

    @Override
    public Controllers.Move giveMove() {
        Move returnable = null;
        if (playerWinHistory.length() < 3) {
            return null;
        }

        String commonSub = longestRepeatingSubStr(playerMoveHistory);
        int indexOfSub = super.Stringmeth.indexOf(commonSub, playerMoveHistory);
        char c = playerMoveHistory.charAt(indexOfSub + commonSub.length());
        System.out.println("Longest common string was : " + commonSub + ", found first at index " + indexOfSub);

        // Check what the score was last time situation was like this
        switch (playerWinHistory.charAt(indexOfSub + commonSub.length())) {
            //If player both the last time and now, he will most likely go for the same strategy again
            case 'W': {
                if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'W') {
                    returnable = getCounterFor(c);
                    break;
                }

                //If player won and then lost this time, he will most likely swap strategy
                if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'L') {
                    returnable = getAnythingBut(c);
                    break;
                }

                // If the player won then, and had a draw now, he will probably stick with it to win the next round
                if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'D') {
                    returnable = getCounterFor(c);
                    break;
                }
            }

            case 'L': {
                //If player Lost the last time, and won this time, he will probably stick with the plan considering it worked
                if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'W') {
                    returnable = getCounterFor(c);
                    break;
                }

                //If player lost back then, and lost now - he will most likely swap strategy
                if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'L') {
                    returnable = getAnythingBut(c);
                    break;
                }

                //If player lost back then, and lost now - he will most likely stick with the plan
                if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'D') {
                    returnable = getCounterFor(c);
                    break;
                }
            }

            case 'D': {
                // Player drew last time ,and won this time - he will probably stick with the plan
                if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'W') {
                    returnable = getCounterFor(c);
                    break;
                }
                // Player drew last time, lost this time - he will probably change strategy
                if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'L') {
                    returnable = getAnythingBut(c);
                    break;
                }

                // If he drew back then, and drew now - he will probably swap - so we stick with it
                if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'D') {
                    Move m = super.decider.convertMove("" + c);
                    super.aiPreviousMove = m;
                    returnable = m;
                    break;
                }
            }
            default: {
                return null;
            }
        }
        
        return returnable;
    }

    /**
     * Returns a move that is any move but the move characterized in the move
     *
     * @param move a char, 'r', 'p', or 's' representing Rock Paper or Scissors
     * @return returns any move except the move specified in the param move
     */
    public Move getAnythingBut(char move) {
        double r = 0.5;
        double p = 0.5;
        double s = 0.5;
        Move m = null;

        Random rand = new Random();
        double d = rand.nextDouble();

        if (move == 'R') {
            if (d < 0.5) {
                m = Move.PAPER;
                super.aiPreviousMove = m;
                return m;
            }
            m = Move.SCISSORS;
            super.aiPreviousMove = m;
            return m;
        }
        if (move == 'P') {
            if (d < 0.5) {
                m = Move.ROCK;
                super.aiPreviousMove = m;
                return m;
            }
            m = Move.SCISSORS;
            super.aiPreviousMove = m;
            return m;
        }
        if (move == 'S') {
            if (d < 0.5) {
                m = Move.PAPER;
                super.aiPreviousMove = m;
                return m;
            }
            m = Move.ROCK;
            return m;
        }

        return null;
    }

    /**
     * returns the move that counters the move characterized in param 'move'
     *
     * @param move a char, 'r', 'p', or 's' representing Rock Paper or Scissors
     * @return returns the move that counters the move from parameter
     */
    public Move getCounterFor(char move) {
        Move m = null;
        if (move == 'R') {
            m = Move.PAPER;
            super.aiPreviousMove = m;
            return m;
        }
        if (move == 'P') {
            m = Move.SCISSORS;
            super.aiPreviousMove = m;
            return m;
        }
        if (move == 'S') {
            m = Move.ROCK;
            super.aiPreviousMove = m;
            return m;
        }
        super.aiPreviousMove = m;
        return m;
    }

    @Override
    public void placeMove(String playerMove) {
        playerPrevMove = super.decider.convertMove(playerMove);
        playerWonLastRound = super.decider.playerWins(playerPrevMove, super.aiPreviousMove);

        char c = playerMove.charAt(0);
        playerMoveHistory += c;

        switch (playerWonLastRound) {
            case 0: {
                playerWinHistory += "D";
                break;
            }
            case 1: {
                playerWinHistory += "W";
                break;
            }
            default: {
                playerWinHistory += "L";
                break;
            }
        }
    }

    /**
     * returns the longest repeating substring within the string 's'
     *
     * @param s the string to search from
     * @return returns the longest repeating substring in param s
     */
    public String longestRepeatingSubStr(String s) {
        int stringLength = s.length();
        String[] suffixes = new String[stringLength];
        for (int index = 0; index < stringLength; index++) {
            suffixes[index] = super.Stringmeth.substring(s, index, stringLength);
        }

        super.arrLib.sort(suffixes);

        String lrs = "";
        for (int i = 0; i < stringLength - 1; i++) {
            String x = lcp(suffixes[i], suffixes[i + 1]);
            int playerMoves = playerMoveHistory.length();
            int indeX = super.Stringmeth.lastIndexOf(x, playerMoveHistory);
            if (x.length() > lrs.length() && (indeX + x.length() == playerMoveHistory.length())) {
                lrs = x;
            }
        }
        return lrs;
    }

    /**
     * returns the longest common prefix for two strings, s and t
     *
     * @param s one string
     * @param t another string
     * @return returns the longest common prefix of s and t
     */
    public String lcp(String s, String t) {
        int shortest = Math.min(s.length(), t.length());
        for (int index = 0; index < shortest; index++) {
            if (s.charAt(index) != t.charAt(index)) {
                return super.Stringmeth.substring(s, 0, index);
            }
        }
        return super.Stringmeth.substring(s, 0, shortest);
    }
}
