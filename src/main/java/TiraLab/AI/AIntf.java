/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI;

import TiraLab.Controllers.Move;

/**
 *
 * @author ColdFish
 */
public interface AIntf {
    
    /**
     * Returns the move that the AI chose
     * @return returns enum Move selection
     */
    public Move giveMove();

    
    /**
     * Gives the player move to the AI, so it can adjust future predictions
     * @param playerMove the last player move
     */
    public void placeMove(String playerMove);
    
    
    /**
     * Increase the winRating of the AI, if the AI would have won against prevMove 
     * @param prevMove the previous move of the AI
     */
    public void increaseWinRating(Move prevMove);
    
    /**
     * Returns the amount of times the specific AI would have won if it's choises were always selected
     * @return return the amount of wins of the AI
     */
    public int getWins();
    
    /**
     * Resets the win counter of the AI
     */
    public void resetWins();
}
