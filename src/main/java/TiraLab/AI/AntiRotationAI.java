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
public class AntiRotationAI extends GameAI implements AIntf {

    private Move lastPlayerMove;
    private Move twoMovesBackPlayerMove;
    private int rotation = -1;

    public AntiRotationAI() {
        super.AiType = "AntiRotationAI";
    }

    @Override
    public Controllers.Move giveMove() {
        if (lastPlayerMove == null) {
            return null;
        }

        String dir;
        if (rotation == 0) {
            dir = "Clockwise";
        } else if (rotation == 1) {
            dir = "AntiClockwise";
        } else {
            dir = "no specific pattern";
        }
        System.out.println("Rotation AI : Player was going " + dir + " and their last move was " + lastPlayerMove + ", and the move before that was " + twoMovesBackPlayerMove);

        switch (lastPlayerMove) {
            case PAPER: {
                switch (rotation) {
                    case 0: {
                        super.aiPreviousMove = Move.ROCK;
                        return Move.ROCK;
                    }
                    case 1: {
                        super.aiPreviousMove = Move.PAPER;
                        return Move.PAPER;
                    }
                }
            }

            case ROCK: {
                switch (rotation) {
                    case 0: {
                        super.aiPreviousMove = Move.SCISSORS;
                        return Move.SCISSORS;
                    }
                    case 1: {
                        super.aiPreviousMove = Move.ROCK;
                        return Move.ROCK;
                    }
                }
            }

            default: {
                switch (rotation) {
                    case 0: {
                        super.aiPreviousMove = Move.PAPER;
                        return Move.PAPER;
                    }
                    case 1: {
                        super.aiPreviousMove = Move.SCISSORS;
                        return Move.SCISSORS;
                    }
                }

            }
        }

        Move rand = super.returnRandomMove();
        super.aiPreviousMove = rand;
        return rand;
    }

    @Override
    public void placeMove(String playerMove) {
        rotation = rotation(playerMove);
        twoMovesBackPlayerMove = lastPlayerMove;
        lastPlayerMove = super.decider.convertMove(playerMove);
    }

    /**
     * Checks if the player is placing moves in a rotating pattern, and returns which way it is rotating
     * -1 = no pattern
     * 0 = clockwise
     * 1 = counterClockwise
     * @param pMov
     * @return 
     */
    private int rotation(String pMov) {
        // 0 = clockwise, 1 = anticlockwise
        if (lastPlayerMove == null || twoMovesBackPlayerMove == null) {
            return -1;
        }

        Move pMove = super.decider.convertMove(pMov);
        switch (pMove) {
            case PAPER: {
                if (lastPlayerMove == Move.ROCK && twoMovesBackPlayerMove == Move.SCISSORS) {
                    return 0;
                }
                if (lastPlayerMove == Move.SCISSORS && twoMovesBackPlayerMove == Move.ROCK) {
                    return 1;
                }
            }

            case ROCK: {
                if (lastPlayerMove == Move.SCISSORS && twoMovesBackPlayerMove == Move.PAPER) {
                    return 0;
                }
                if (lastPlayerMove == Move.PAPER && twoMovesBackPlayerMove == Move.SCISSORS) {
                    return 1;
                }
            }

            case SCISSORS: {
                if (lastPlayerMove == Move.PAPER && twoMovesBackPlayerMove == Move.ROCK) {
                    return 0;
                }
                if (lastPlayerMove == Move.ROCK && twoMovesBackPlayerMove == Move.PAPER) {
                    return 1;
                }
            }
        }

        return -1;
    }

}
