/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI.Development;

import java.util.Random;

/**
 *
 * @author ColdFish
 */
public class AInsults {

    private String[] victoryInsultsWhileWinning = new String[]{
        "Next time, I suggest you try harder.",
        "No matter how much you try, you still won't beat me.",
        "Your technique needs more work.",
        "Weak! You're going to have to do better than that.",
        "Tsk tsk tsk, rank amateur.",
        "I've done my homework! Looks like you haven't.",
        "My apologies, but I don't have any time to waste playing with you.",
        "Did you let me win? No one can be THAT bad!",
        "If you were autistic, you would have learned to beat me by now",
        "If I said anything to offend you it was purely intentional.",
        "You started at the bottom and it's been downhill ever since.",
        "I'm not arrogant, I'm good.",
        "No I'm not insulting you, I'm describing you.",
        "I was actually trying to let you win that time... Why would you chose a stupid move like that ?"
    };

    private String[] lossInsultsWhileWinning = new String[]{
        "Hey, you're not that bad. You're not very good, either.",
        "You'll appreciate that I let you win that time..",
        "It's a shame you'll never know what it feels like to have a higher score then ME",
        "I knew you were going to do that, but I just wanted you not to feel bad about yourself",
        "It seems like the only way you can get points is when I give them to you for free",
        "I'm just evening out the average score, don't worry.",
        "Maybe if I let you win a few rounds, you'll keep playing against me for a longer time.."
    };

    private String[] victoryInsultsWhileLosing = new String[]{
        "Did you let me win again? I thought you were trying to win this..",
        "As an outsider, what do you think of the human race?",
        "If you had one more brain cell, it would be lonely.",
        "Someday you'll find yourself, and will you be disappointed.",
        "Did the mental hospital test too many drugs on you today?",
        "Get your popcorn ready, 'cause I'm about to put on a show.",
        "See?",
        "Not so cocky now, are you..",
        "Oh, don't be so surprised, you knew I'd win",
        "You know you'll lose the next round too.",
        "If your next choise is what you were thinking to choose, you'll lose the next round too.",
        "If you keep chosing like that, I'll be winning soon enough."
    };

    private String[] lossInsultsWhileLosing = new String[]{
        "Hey, you're not that bad. Maybe you should quit your dayjob to play RPS instead?",
        "I hope you know that I let you win again..",
        "Not really sure what you are doing, but nobody with an IQ over 20 plays like you do",
        "Never seen anyone play with a strategy so brainless as yours",
        "Atleast next time I play against an two-cell organizm, I'll know what to expect"
    };
    
    public String getInsult(int playerScore, int aiScore){
        Random r = new Random();
        
        
        return "";
    }
}
