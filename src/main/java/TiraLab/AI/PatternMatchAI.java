/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI;

import TiraLab.Controllers;
import TiraLab.Controllers.Move;
import TiraLab.GameLogic.WinDecider;
import java.util.Random;

/**
 *
 * @author ColdFish
 */
public class PatternMatchAI extends GameAI implements AIntf {
    
    private int pChance;
    private int rChance;
    
    private int repeatAfterWin = 0;
    private int repeatAfterLose = 0;
    private int repeatOnDraw = 0;
    private int alternateAfterWin = 0;
    private int alternateAfterLose = 0;
    private int alternateAfterDraw = 0;
    
    private int matchRate = 5;
    private int histLimit = 3;
    
    private WinDecider decider;
    
    private String lastPlayerMove;
    
    /**
     * Creates an AI that keeps track of what the player tends to do when he wins or loses, and attempts to counter that
     */
    public PatternMatchAI() {
        decider = new WinDecider();
        super.AiType = "PatternMatcher";
        pChance = 33;
        rChance = 33;
    }
    
    @Override
    public Controllers.Move giveMove() {
        Random r = new Random();
        int randomized = r.nextInt(100);
        Move aiMove = null;
        
        if (randomized < pChance) {
            aiMove = Move.PAPER;
        } else if (randomized < pChance + rChance) {
            aiMove = Move.ROCK;
        } else {
            aiMove = Move.SCISSORS;
        }
        
        super.aiPreviousMove = aiMove;
        return aiMove;
    }
    
    @Override
    public void placeMove(String playerMove) {
        Move pMove = decider.convertMove(playerMove);
        
        checkPatterns(pMove, playerMove);
        
        adjustStrategy(playerMove);
        
        lastPlayerMove = playerMove;
    }
    
    /**
     * Check if the player won, and if the player swaps strategy when losing or winning, and adjusts the decision making acordingly
     * @param pMove the player move last round
     * @param playerMoveThisRound player move this round
     */
    public void checkPatterns(Move pMove, String playerMoveThisRound) {
        switch (decider.playerWins(pMove, aiPreviousMove)) {
            case 1: {
                //Player wins, and it was the same move as last turn, or else, by altering his choise
                if (playerMoveThisRound.equals(lastPlayerMove)) {
                    repeatAfterWin++;
                    matchRate -= 2;
                } else {
                    alternateAfterWin++;
                    matchRate -= 2;
                }
                break;
            }

            // Player loses, because he kept the same card, or else -> by altering his choise
            case -1: {
                if (playerMoveThisRound.equals(lastPlayerMove)) {
                    repeatAfterLose++;
                } else {
                    alternateAfterLose++;
                }
                break;
            }
            
            case 0: {
                if (playerMoveThisRound.equals(lastPlayerMove)) {
                    repeatOnDraw++;
                } else {
                    alternateAfterDraw++;
                }
                break;
            }
        }
    }
    
    /**
     * Adjusts the AI strategy based on the previous player move
     * @param playerMove the player move from this round
     */
    private void adjustStrategy(String playerMove) {
        Move pMove = decider.convertMove(playerMove);
        switch (pMove) {
            case ROCK: {
                switch (decider.playerWins(pMove, aiPreviousMove)) {
                    //Player loses by selecting rock
                    case -1: {
                        if (alternateAfterLose > repeatAfterLose) {
                            rChance += matchRate;
                            pChance -= (matchRate / 2);
                        } else if (repeatAfterLose > alternateAfterLose) {
                            rChance -= (matchRate / 2);
                            pChance += matchRate;
                        }
                        break;
                    }

                    //Player wins by selecting rock
                    case 1: {
                        if (repeatAfterWin > alternateAfterWin) {
                            rChance -= (matchRate / 2);
                            pChance += matchRate;
                        } else if (alternateAfterWin > repeatAfterWin) {
                            rChance += matchRate;
                            pChance -= (matchRate / 2);
                        }
                        break;
                    }
                }
            }
            
            case PAPER: {
                switch (decider.playerWins(pMove, aiPreviousMove)) {
                    //Player Win with paper
                    case 1: {
                        if (repeatAfterWin > alternateAfterWin) {
                            rChance -= (matchRate / 2);
                            pChance += matchRate;
                        } else if (alternateAfterWin > repeatAfterWin) {
                            rChance += matchRate;
                            pChance -= (matchRate / 2);
                        }
                        break;
                    }

                    //Player Lose with paper
                    case -1: {
                        if (alternateAfterLose > repeatAfterLose) {
                            rChance += matchRate;
                            pChance -= (matchRate / 2);
                        } else if (repeatAfterLose > alternateAfterLose) {
                            rChance -= (matchRate / 2);
                            pChance += matchRate;
                        }
                        break;
                    }
                }
            }
            
            case SCISSORS: {
                switch (decider.playerWins(pMove, aiPreviousMove)) {
                    //Player Lose with scissors
                    case -1: {
                        if (alternateAfterLose > repeatAfterLose) {
                            rChance += (matchRate * (2 / 3));
                            pChance += matchRate * (2 / 3);
                        } else if (repeatAfterLose > alternateAfterLose) {
                            rChance -= (matchRate * (2 / 3));
                            pChance -= matchRate * (2 / 3);
                        }
                        break;
                    }

                    //Player Win with scissors
                    case 1: {
                        if (repeatAfterWin > alternateAfterWin) {
                            rChance -= (matchRate * (2 / 3));
                            pChance -= matchRate * (2 / 3);
                        } else if (alternateAfterWin > repeatAfterWin) {
                            rChance += (matchRate * (2 / 3));
                            pChance += matchRate * (2 / 3);
                        }
                        break;
                    }
                }
            }
        }
    }
    
}
