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

        String pastMov = gamePlayerHistory.substring(gamePlayerHistory.length() - memoryLength, gamePlayerHistory.length());
        int r = 0;
        int p = 0;
        int s = 0;
        int lastIndex = 0;

        while (lastIndex > -1) {
            lastIndex = gamePlayerHistory.indexOf(pastMov, lastIndex);

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
