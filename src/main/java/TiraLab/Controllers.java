package TiraLab;

import TiraLab.AI.AIntf;
import TiraLab.AI.ShuffleAI;
import TiraLab.AI.RememberingAI;
import TiraLab.GameLogic.Game;
import TiraLab.GameLogic.WinDecider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controllers {
    
    private HashMap<String, Game> sessions;
    private WinDecider decider;
    
    public enum Move {
        ROCK, PAPER, SCISSORS
    }
    
    public Controllers() {
        sessions = new HashMap<>();
        decider = new WinDecider();
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request) {
        String sessionID = getSession(request);
        if (!sessions.containsKey(sessionID)) {
            sessions.put(sessionID, new Game());
        }
        return "game";
    }
    
    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public String sessions(HttpServletRequest request, Model model) {
        String sessionID = getSession(request);
        if (!sessions.containsKey(sessionID)) {
            sessions.put(sessionID, new Game());
        }
        model.addAttribute("sessions", sessions.values());
        return "stats";
    }
    
    @RequestMapping(value = "/scores")
    public String scores(Model model, HttpServletRequest request, @RequestParam Optional<String> playerMove) {
        String sessionID = getSession(request);
        Game game = sessions.get(sessionID);
        double win = (double) game.getPlayerScore() / game.getRounds();
        if (Double.isNaN(win)) {
            win = 0;
        }
        model.addAttribute("playerPercentage", win * 100);
        model.addAttribute("bestPercentage", bestPercentage());
        model.addAttribute("game", sessions.get(sessionID));
        return "situation :: scoresFrag";
    }
    
    public double bestPercentage() {
        double best = 0;
        for (Game g : sessions.values()) {
            double win = (double) g.getPlayerScore() / g.getRounds();
            if (win > best) {
                best = win;
            }
        }
        return best * 100;
    }
    
    @RequestMapping(value = "/images")
    public String images(Model model, HttpServletRequest request, @RequestParam Optional<String> playerMove) {
        String sessionID = getSession(request);
        Move pMove = null;
        Move aiMove = null;
        if (playerMove.isPresent()) {
            switch (playerMove.get()) {
                case "Rock": {
                    pMove = Move.ROCK;
                    break;
                }
                case "Paper": {
                    pMove = Move.PAPER;
                    break;
                }
                case "Scissors": {
                    pMove = Move.SCISSORS;
                    break;
                }
            }

            //First, have the AI decide it's move, and then tell it what the player chose.
            aiMove = sessions.get(sessionID).getAIMove();
            sessions.get(sessionID).placeMove(playerMove.get());
        }
        
        if (!sessions.containsKey(sessionID)) {
            sessions.put(sessionID, new Game());
        }
        
        if (playerMove.isPresent()) {
            int wins = decider.playerWins(pMove, aiMove);
            switch (wins) {
                case 1: {
                    sessions.get(sessionID).playerWins(decider.getMoveThatBeats(pMove));
                    break;
                }
                case -1: {
                    sessions.get(sessionID).playerLoses(aiMove);
                    break;
                }
                default: {
                    sessions.get(sessionID).draw(decider.getMoveThatBeats(pMove));
                }
            }
            model.addAttribute("playerWins", wins);
            model.addAttribute("move", aiMove.toString());
            model.addAttribute("pMove", pMove.toString());
        }
        model.addAttribute("game", sessions.get(sessionID));
        
        return "situation :: imagesFrag";
    }
    
    public String getSession(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> cooki = Arrays.stream(cookies).filter(cookie -> cookie.getName().contains("JSESSION")).findFirst();
            if (cooki.isPresent()) {
                return cooki.get().getValue();
            }
        }
        return null;
    }
    
}
