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

    public NovaAI() {
        super.AiType = "PlayerWinHistoryMatchAI";
    }

    @Override
    public Controllers.Move giveMove() {
        if (playerWinHistory.length() < 3) {
            return null;
        }

        String commonSub = longestRepeatingSubStr(playerMoveHistory);
        while (!commonSub.isEmpty()) {
            while (true) {
                int indexOfSub = playerMoveHistory.indexOf(commonSub);
                char c = playerMoveHistory.charAt(indexOfSub + commonSub.length());
                System.out.println("Longest common string was : " + commonSub + ", found first at index " + indexOfSub);

                // Check what the score was last time situation was like this
                switch (playerWinHistory.charAt(indexOfSub + commonSub.length())) {
                    //If player both the last time and now, he will most likely go for the same strategy again
                    case 'W': {
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'W') {
                            return getCounterFor(c);
                        }

                        //If player won and then lost this time, he will most likely swap strategy
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'L') {
                            return getAnythingBut(c);
                        }

                        // If the player won then, and had a draw now, he will probably stick with it to win the next round
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'D') {
                            return getCounterFor(c);
                        }
                    }

                    case 'L': {
                        //If player Lost the last time, and won this time, he will probably stick with the plan considering it worked
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'W') {
                            return getCounterFor(c);
                        }

                        //If player lost back then, and lost now - he will most likely swap strategy
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'L') {
                            return getAnythingBut(c);
                        }

                        //If player lost back then, and lost now - he will most likely stick with the plan
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'D') {
                            return getCounterFor(c);
                        }
                    }

                    case 'D': {
                        // Player drew last time ,and won this time - he will probably stick with the plan
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'W') {
                            return getCounterFor(c);
                        }
                        // Player drew last time, lost this time - he will probably change strategy
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'L') {
                            return getAnythingBut(c);
                        }

                        // If he drew back then, and drew now - he will probably swap - so we stick with it
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'D') {
                            Move m = super.decider.convertMove("" + c);
                            super.aiPreviousMove = m;
                            return m;
                        }
                    }
                    default: {
                        return null;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Returns a move that is any move but the move characterized in the move
     * param
     *
     * @param move
     * @return
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
     * @param move
     * @return
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
     * @param s
     * @return
     */
    public String longestRepeatingSubStr(String s) {
        int stringLength = s.length();
        String[] suffixes = new String[stringLength];
        for (int index = 0; index < stringLength; index++) {
            suffixes[index] = s.substring(index, stringLength);
        }

        Arrays.sort(suffixes);

        String lrs = "";
        for (int i = 0; i < stringLength - 1; i++) {
            String x = lcp(suffixes[i], suffixes[i + 1]);
            int playerMoves = playerMoveHistory.length();
            int indeX = playerMoveHistory.lastIndexOf(x);
            if (x.length() > lrs.length() && (indeX + x.length() == playerMoveHistory.length())) {
                lrs = x;
            }
        }
        return lrs;
    }

    /**
     * returns the longest common prefix for two strings, s and t
     *
     * @param s
     * @param t
     * @return
     */
    public String lcp(String s, String t) {
        int shortest = Math.min(s.length(), t.length());
        for (int index = 0; index < shortest; index++) {
            if (s.charAt(index) != t.charAt(index)) {
                return s.substring(0, index);
            }
        }
        return s.substring(0, shortest);
    }
}
