package TiraLab.AI;

// "Muistava tekoäly"
import TiraLab.Controllers.Move;

public class RememberingAI extends GameAI implements AIntf {

    private String[] memory;
    private int freeMemoryIndex;

    public RememberingAI(int muistinKoko) {
        memory = new String[muistinKoko];
        freeMemoryIndex = 0;
        super.AiType = "RememberingAI";
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
            super.lastMove = Move.ROCK;
            return Move.ROCK;
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
        super.lastMove = given;
        return given;
    }

    @Override
    public void increaseWinRating(Move winningMove) {
        if (super.lastMove == winningMove) {
            super.wins++;
        }
    }
}
