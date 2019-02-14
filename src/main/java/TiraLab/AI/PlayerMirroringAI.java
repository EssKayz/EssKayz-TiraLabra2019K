/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI;

import TiraLab.Structures.RandomGen;
import TiraLab.Controllers;
import TiraLab.Controllers.Move;
import TiraLab.GameLogic.WinDecider;

/**
 *
 * @author ColdFish
 */
public class PlayerMirroringAI extends GameAI implements AIntf {

    private Move lastPlayerMove;
    private WinDecider decider;
    private boolean playerWonLastRound;

    /**
     * Creates an AI that attempts to mirror the player
     */
    public PlayerMirroringAI() {
        super.AiType = "MirroringAI";
        decider = new WinDecider();
    }

    @Override
    public Controllers.Move giveMove() {
        if (lastPlayerMove == null) {
            Move random = super.returnRandomMove();
            super.aiPreviousMove = random;
            return random;
        }

        Move theMove = null;
        if (playerWonLastRound) {
            //If player won last round, he might keep with same move
            switch (lastPlayerMove) {
                case PAPER: {
                    theMove = Move.SCISSORS;
                    break;
                }
                case ROCK: {
                    theMove = Move.PAPER;
                    break;
                }
                default: {
                    theMove = Move.ROCK;
                    break;
                }
            }
        } else {
            // If he lost, he is more likely to swap!
            RandomGen r = new RandomGen();
            float d = r.getRandomFloat();
            switch (lastPlayerMove) {
                case PAPER: {
                    if (d < 0.5) {
                        theMove = Move.ROCK;
                        break;
                    } else {
                        theMove = Move.SCISSORS;
                        break;
                    }
                }
                case ROCK: {
                    if (d < 0.5) {
                        theMove = Move.PAPER;
                        break;
                    } else {
                        theMove = Move.SCISSORS;
                        break;
                    }
                }
                default: {
                    if (d < 0.5) {
                        theMove = Move.ROCK;
                        break;
                    } else {
                        theMove = Move.PAPER;
                        break;
                    }
                }
            }
        }
        super.aiPreviousMove = theMove;
        return theMove;

    }

    @Override
    public void placeMove(String playerMove) {
        lastPlayerMove = decider.convertMove(playerMove);
        switch (decider.playerWins(lastPlayerMove, aiPreviousMove)) {
            case -1: {
                playerWonLastRound = false;
                break;
            }
            case 1: {
                playerWonLastRound = true;
                break;
            }
            default: {
                playerWonLastRound = false;
                break;
            }
        }
    }

}
