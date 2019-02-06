/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI;

import TiraLab.Controllers;
import TiraLab.Controllers.Move;

/**
 *
 * @author ColdFish
 */
public class PathMatchAI extends GameAI implements AIntf {

    private String gamePlayerHistory = "";
    private int memoryLength;

    /**
     * Creates an AI that attempts to match patterns that the player has played
     * before, and counter those
     *
     * @param memory the amount of moves kept in memory to find from history
     */
    public PathMatchAI(int memory) {
        super.AiType = "PathMatcherAI-" + memory + "moves";
        memoryLength = memory;
    }

    @Override
    public Controllers.Move giveMove() {
        // String pastMov = pastMoves.returnString(false);
        if (gamePlayerHistory.length() <= memoryLength) {
            return null;
        }

        // Gets the last time that the same pattern happened as now
        // And then counts the amount of each respective move being made after the pattern, and then gives the one that happened most frequently
        String pastMov = super.Stringmeth.substring(gamePlayerHistory, gamePlayerHistory.length() - memoryLength, gamePlayerHistory.length());
        int r = 0;
        int p = 0;
        int s = 0;
        int lastIndex = 0;

        while (lastIndex > -1) {
            lastIndex = super.Stringmeth.indexOf(pastMov, gamePlayerHistory, lastIndex);

            if (lastIndex != -1) {
                lastIndex += pastMov.length();
                if (lastIndex + 1 < gamePlayerHistory.length()) {
                    switch (gamePlayerHistory.charAt(lastIndex)) {
                        case 'R': {
                            r++;
                            break;
                        }
                        case 'P': {
                            p++;
                            break;
                        }
                        case 'S': {
                            s++;
                            break;
                        }
                    }
                }
            }
            lastIndex--;
        }

        if (r > p && r > s) {
            Move m = Move.PAPER;
            super.aiPreviousMove = m;
            return m;
        }
        if (p > r && p > s) {
            Move m = Move.SCISSORS;
            super.aiPreviousMove = m;
            return m;
        }
        if (s > r && s > p) {
            Move m = Move.ROCK;
            super.aiPreviousMove = m;
            return m;
        }

        Move m = super.returnRandomMove();
        super.aiPreviousMove = m;
        return m;
    }

    @Override
    public void placeMove(String playerMove) {
        char c = playerMove.charAt(0);
        gamePlayerHistory += c;
    }

}
