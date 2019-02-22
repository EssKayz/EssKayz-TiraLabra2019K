package TiraLab;

import TiraLab.AI.*;
import TiraLab.Structures.RandomGen;
import TiraLab.GameLogic.Game;
import TiraLab.GameLogic.WinDecider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Configuration
@Controller
public class Controllers {

    private HashMap<String, Game> sessions;
    private WinDecider decider;

    public enum Move {
        ROCK("https://s3-us-west-2.amazonaws.com/s.cdpn.io/201958/Rock-Paper-Scissors-01.png"),
        PAPER("https://s3-us-west-2.amazonaws.com/s.cdpn.io/201958/Rock-Paper-Scissors-02.png"),
        SCISSORS("https://s3-us-west-2.amazonaws.com/s.cdpn.io/201958/Rock-Paper-Scissors-03.png");

        private String imgLink;

        Move(String link) {
            this.imgLink = link;
        }

        public String getImgLink() {
            return imgLink;
        }
    }

    public Controllers() {
        sessions = new HashMap<>();
        decider = new WinDecider();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
        String sessionID = getSession(request);
        if (sessionID == null) {
            RandomGen r = new RandomGen();
            int rr = r.getRandomInt(Integer.MAX_VALUE);
            Cookie cookie = new Cookie("GameID", String.valueOf(rr));
            cookie.setValue(String.valueOf(rr));

            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24 * 365 * 1);
            response.addCookie(cookie);
        }
        sessionID = getSession(request);
        if (!sessions.containsKey(sessionID)) {
            System.out.println("-------/////////--------");
            System.out.println("New session added : ID " + sessionID);
            System.out.println("-------/////////--------");
            sessions.put(sessionID, new Game(sessionID));
        }
        model.addAttribute("game", sessions.get(sessionID));
        return "game";
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public String sessions(HttpServletRequest request, Model model) {
        String sessionID = getSession(request);
        if (!sessions.containsKey(sessionID)) {
            System.out.println("-------/////////--------");
            System.out.println("New session added : ID " + sessionID);
            System.out.println("-------/////////--------");
            sessions.put(sessionID, new Game(sessionID));
        }
        model.addAttribute("sessions", sessions.values());
        return "stats";
    }

    @RequestMapping(value = "/reset")
    public String resetGame(HttpServletRequest request, Model model, @RequestParam String id) {
        sessions.get(id).resetScore();
        model.addAttribute("game", sessions.get(id));
        return "game";
    }

    @RequestMapping(value = "/scores")
    public String scores(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam Optional<String> playerMove) throws InterruptedException {
        String sessionID = getSession(request);
        if (sessionID == null) {
            RandomGen r = new RandomGen();
            int rr = r.getRandomInt(Integer.MAX_VALUE);
            Cookie cookie = new Cookie("GameID", String.valueOf(rr));
            cookie.setValue(String.valueOf(rr));

            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24 * 365 * 1);
            response.addCookie(cookie);
        }

        Thread.sleep(100);

        sessionID = getSession(request);
        Game game = sessions.get(sessionID);
        double win = (double) game.getPlayerScore() / (game.getAiScore() + game.getPlayerScore());
        if (Double.isNaN(win)) {
            win = 0;
        }

        Thread.sleep(100);

        model.addAttribute("avgPercentage", Math.floor((averagePercentage() * 100) * 100) / 100);
        model.addAttribute("playerPercentage", Math.floor((win * 100) * 100) / 100);
        model.addAttribute("bestPercentage", Math.floor(bestPercentage() * 100) / 100);
        model.addAttribute("game", sessions.get(sessionID));
        return "situation :: scoresFrag";
    }

    public double averagePercentage() {
        double playerScore = 0;
        double aiScore = 0;
        for (Game g : sessions.values()) {
            playerScore += g.getPlayerScore();
            aiScore += g.getAiScore();
        }
        double win = (double) playerScore / (aiScore + playerScore);
        if (Double.isNaN(win)) {
            return 0;
        }
        return win;
    }

    public double bestPercentage() {
        double best = 0;
        for (Game g : sessions.values()) {
            double win = (double) g.getPlayerScore() / (g.getAiScore() + g.getPlayerScore());
            if (win > best) {
                best = win;
            }
        }
        return best * 100;
    }

    @RequestMapping(value = "/images")
    public String images(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam Optional<String> playerMove) {
        String sessionID = getSession(request);
        if (sessionID == null) {
            RandomGen r = new RandomGen();
            int rr = r.getRandomInt(Integer.MAX_VALUE);
            Cookie cookie = new Cookie("GameID", String.valueOf(rr));
            cookie.setValue(String.valueOf(rr));

            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24 * 365 * 1);
            response.addCookie(cookie);
        }
        sessionID = getSession(request);
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

            System.out.println("----------");
            //First, have the AI decide it's move, and then tell it what the player chose.
            aiMove = sessions.get(sessionID).getAIMove();
            System.out.println("Player played move : " + playerMove.get());
            sessions.get(sessionID).placeMove(playerMove.get());
        }

        if (!sessions.containsKey(sessionID)) {
            sessions.put(sessionID, new Game(sessionID));
        }

        if (playerMove.isPresent()) {
            int wins = decider.playerWins(pMove, aiMove);
            switch (wins) {
                case 1: {
                    System.out.println("Player wins.");
                    sessions.get(sessionID).playerWins(decider.getMoveThatBeats(pMove));
                    break;
                }
                case -1: {
                    System.out.println("Player loses.");
                    sessions.get(sessionID).playerLoses(aiMove);
                    break;
                }
                default: {
                    System.out.println("Draw!");
                    sessions.get(sessionID).draw(decider.getMoveThatBeats(pMove));
                }
            }
            System.out.println("----------");
            model.addAttribute("playerWins", wins);
            model.addAttribute("move", aiMove);
            model.addAttribute("pMove", pMove);
        }
        model.addAttribute("game", sessions.get(sessionID));

        return "situation :: imagesFrag";
    }

    public String getSession(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> cooki = Arrays.stream(cookies).filter(cookie -> cookie.getName().contains("GameID")).findFirst();
            if (cooki.isPresent()) {
                return cooki.get().getValue();
            }
        }
        return null;
    }

}
