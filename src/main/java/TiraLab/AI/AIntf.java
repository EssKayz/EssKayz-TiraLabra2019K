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
     * @return 
     */
    public Move giveMove();

    
    /**
     * Gives the player move to the AI, so it can adjust future predictions
     * @param playerMove 
     */
    public void placeMove(String playerMove);
    
    
    /**
     * Increase the winRating of the AI, if the AI would have won against prevMove 
     * @param prevMove 
     */
    public void increaseWinRating(Move prevMove);
    
    /**
     * Returns the amount of times the specific AI would have won if it's choises were always selected
     * @return 
     */
    public int getWins();
    
}
