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

    /**
     * Creates an AI that attempts to track if the player is rotating in one
     * direction, and returns the counter for the next element in the rotation
     * pattern
     */
    public AntiRotationAI() {
        super.AiType = "AntiRotationAI";
    }

    @Override
    public Controllers.Move giveMove() {
        if (lastPlayerMove == null) {
            return null;
        }

        String dir;
        switch (rotation) {
            case 0:
                dir = "Clockwise";
                break;
            case 1:
                dir = "AntiClockwise";
                break;
            default:
                dir = "no specific pattern";
                break;
        }
        System.out.println("Rotation AI : Player was going " + dir + " and their last move was " + lastPlayerMove + ", and the move before that was " + twoMovesBackPlayerMove);

        // Check what the last player move was, and then if the player is currently showing signs of a rotating pattern, return the counter for the suspected next move
        switch (lastPlayerMove) {
            case PAPER: {
                switch (rotation) {
                    // If player is rotating clockwise, return the countering move for the next suspected move in the rotation
                    case 0: {
                        super.aiPreviousMove = Move.ROCK;
                        return Move.ROCK;
                    }
                    // If player is rotating counterclockwise, return the countering move for the next suspected move in the rotation
                    case 1: {
                        super.aiPreviousMove = Move.PAPER;
                        return Move.PAPER;
                    }
                }
            }

            case ROCK: {
                switch (rotation) {
                    // If player is rotating clockwise, return the countering move for the next suspected move in the rotation
                    case 0: {
                        super.aiPreviousMove = Move.SCISSORS;
                        return Move.SCISSORS;
                    }
                    // If player is rotating counterclockwise, return the countering move for the next suspected move in the rotation
                    case 1: {
                        super.aiPreviousMove = Move.ROCK;
                        return Move.ROCK;
                    }
                }
            }

            default: {
                switch (rotation) {
                    // If player is rotating clockwise, return the countering move for the next suspected move in the rotation
                    case 0: {
                        super.aiPreviousMove = Move.PAPER;
                        return Move.PAPER;
                    }
                    // If player is rotating counterclockwise, return the countering move for the next suspected move in the rotation
                    case 1: {
                        super.aiPreviousMove = Move.SCISSORS;
                        return Move.SCISSORS;
                    }
                }

            }
        }

        // if player is not showing signs of rotation, return a random move
        Move rand = super.returnRandomMove();
        super.aiPreviousMove = rand;
        return rand;
    }

    @Override
    public void placeMove(String playerMove) {
        // figure out if there is a rotating pattern ongoing, and place the previous move as the move from two moves ago
        rotation = rotation(playerMove);
        twoMovesBackPlayerMove = lastPlayerMove;
        lastPlayerMove = super.decider.convertMove(playerMove);
    }

    /**
     * Checks if the player is placing moves in a rotating pattern, and returns
     * which way it is rotating -1 = no pattern 0 = clockwise 1 =
     * counterClockwise
     *
     * @param pMov the last player move
     * @return returns -1 = no pattern,  0 = clockwise or 1 =counterClockwise
     */
    private int rotation(String pMov) {
        // 0 = clockwise, 1 = anticlockwise
        if (lastPlayerMove == null || twoMovesBackPlayerMove == null) {
            return -1;
        }

        // Check if the two previous moves show a rotating pattern
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
