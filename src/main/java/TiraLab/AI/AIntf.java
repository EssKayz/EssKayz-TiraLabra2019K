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
    
    public Move giveMove();

    public void placeMove(String playerMove);
    
    public void increaseWinRating(Move prevMove);
    
    public int getWins();
    
}
