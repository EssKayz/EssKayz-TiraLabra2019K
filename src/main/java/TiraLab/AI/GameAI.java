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
public class GameAI {
    public int wins;
    public Move lastMove;
    public String AiType;
    
    public int getWins(){
        return wins;
    }
}
