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

        String commonSub = lrs(playerMoveHistory);
        while (!commonSub.isEmpty()) {
            while (true) {
                int indexOfSub = playerMoveHistory.indexOf(commonSub);
                char c = playerMoveHistory.charAt(indexOfSub + commonSub.length());
                System.out.println("Longest common string was : " + commonSub + ", found first at index " + indexOfSub);
                // Check what the score was last time situation was like this
                switch (playerWinHistory.charAt(indexOfSub + commonSub.length())) {
                    case 'W': {
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'W') {
                            //If player both the last time and now, he will most likely go for the same strategy again
                            return getCounterFor(c);
                        }

                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'L') {
                            //If player won and then lost twice in a row, he will most likely swap strategy
                            return getAnythingBut(c);
                        }

                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'D') {
                            return getCounterFor(c);
                        }
                    }

                    case 'L': {
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'W') {
                            //If player Lost and then won
                            return getAnythingBut(c);
                        }

                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'L') {
                            //If player lost back then
                            return getCounterFor(c);
                        }

                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'D') {
                            return getAnythingBut(c);
                        }
                    }

                    case 'D': {
                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'W') {
                            return getCounterFor(c);
                        }

                        if (playerWinHistory.charAt(playerWinHistory.length() - 1) == 'L') {
                            return getAnythingBut(c);
                        }

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

    public String lrs(String s) {
        int n = s.length();
        String[] suffixes = new String[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = s.substring(i, n);
        }

        Arrays.sort(suffixes);

        String lrs = "";
        for (int i = 0; i < n - 1; i++) {
            String x = lcp(suffixes[i], suffixes[i + 1]);
            int playerMoves = playerMoveHistory.length();
            int indeX = playerMoveHistory.lastIndexOf(x);
            if (x.length() > lrs.length() && (indeX + x.length() == playerMoveHistory.length())) {
                lrs = x;
            }
        }
        return lrs;
    }

    public String lcp(String s, String t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return s.substring(0, i);
            }
        }
        return s.substring(0, n);
    }
}
