package TiraLab.AI.Development;

// "Muistava tekoäly"
import TiraLab.AI.AIntf;
import TiraLab.AI.GameAI;
import TiraLab.Controllers.Move;
import java.util.Random;

public class FrequencyCountAI extends GameAI implements AIntf {

    private String[] memory;
    private int freeMemoryIndex;

    public FrequencyCountAI(int muistinKoko) {
        memory = new String[muistinKoko];
        freeMemoryIndex = 0;
        super.AiType = "FrequencyCountAI";
    }

    @Override
    public void placeMove(String playerMove) {
        if (freeMemoryIndex == memory.length) {
            for (int i = 1; i < memory.length; i++) {
                memory[i - 1] = memory[i];
            }
            freeMemoryIndex--;
        }
        memory[freeMemoryIndex] = playerMove;
        freeMemoryIndex++;
    }

    @Override
    public Move giveMove() {
        if (freeMemoryIndex == 0 || freeMemoryIndex == 1) {
            Move random = super.returnRandomMove();
            super.aiPreviousMove = random;
            return random;
        }

        Move given;
        String lastMove = memory[freeMemoryIndex - 1];
        int r = 0;
        int p = 0;
        int s = 0;

        for (int i = 0; i < freeMemoryIndex - 1; i++) {
            if (lastMove.equals(memory[i])) {
                String next = memory[i + 1];

                if ("Rock".equals(next)) {
                    r++;
                } else if ("Paper".equals(next)) {
                    p++;
                } else {
                    s++;
                }
            }
        }

        if (r > p && r > s) {
            given = Move.PAPER;
        } else if (p > r && p > s) {
            given = Move.SCISSORS;
        } else {
            given = Move.SCISSORS;
        }
        super.aiPreviousMove = given;
        return given;
    }

}
