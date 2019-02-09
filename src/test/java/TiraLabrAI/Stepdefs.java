package TiraLabrAI;

import TiraLab.AI.RandomAI;
import TiraLab.Controllers.Move;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import java.util.Random;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    WebDriver driver;
    Random random;
    String baseUrl;
    int SleepTime;

    public Stepdefs() throws ClassNotFoundException {
        File file;
        SleepTime = 100;
        if (System.getProperty("os.name").matches("Mac OS X")) {
            file = new File("lib/macgeckodriver");
        } else {
            file = new File("lib/geckodriver");
        }
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.gecko.driver", absolutePath);

        if (System.getProperty("os.name").matches("Windows 10")) {
            this.driver = new ChromeDriver();
            //this.driver = new HtmlUnitDriver(true);
        } else {
            this.driver = new ChromeDriver();
        }
        baseUrl = "http://localhost:/";
        random = new Random();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("^user is at the main page$")
    public void user_is_at_the_main_page() throws Throwable {
        driver.get("http://localhost:" + 7777 + "/");
        Thread.sleep(1000);
    }

    @When("^a link is clicked$")
    public void a_link_is_clicked() throws Throwable {
        Thread.sleep(1000);
        clickLinkWithText("linkki");
        Thread.sleep(1000);
    }

    @Then("^\"([^\"]*)\" is shown$")
    public void is_shown(String content) throws Throwable {
        Thread.sleep(SleepTime);
        boolean isShown = false;
        for (int i = 0; i < 5; i++) {
            Thread.sleep(SleepTime);
            System.out.println("Expected element : " + content);
            //System.out.println("Page source : " + driver.getPageSource().toString());
            if (driver.getPageSource().contains(content)) {
                isShown = true;
                break;
            }
        }
        assertTrue(isShown);
        Thread.sleep(SleepTime);
    }

    @When("^user clicks button \"([^\"]*)\"$")
    public void user_clicks_button(String buttonID) throws Throwable {
        user_clicks_button_by(By.id(buttonID));
    }

    @Then("^\"([^\"]*)\" is changed to \"([^\"]*)\"$")
    public void is_changed_to(String elementToChange, String val) throws Throwable {
        Thread.sleep(SleepTime * 20);
        String selection = driver.findElement(By.id(elementToChange)).getText();
        Thread.sleep(SleepTime);
        assertTrue(selection.toLowerCase().equals(val.toLowerCase()));
    }

    @Given("^user plays \"([^\"]*)\" rounds against the AI$")
    public void user_plays_rounds_against_the_AI(String arg1) throws Throwable {
        RandomAI randy = new RandomAI();
        int rounds = Integer.parseInt(arg1);
        int played = 0;
        for (int i = 0; i < rounds; i++) {
            Move m = randy.giveMove();

            switch (m) {
                case PAPER: {
                    user_clicks_button_by(By.id("btnPaper"));
                    played++;
                    break;
                }
                case ROCK: {
                    user_clicks_button_by(By.id("btnRock"));
                    played++;
                    break;
                }
                case SCISSORS: {
                    user_clicks_button_by(By.id("btnScissors"));
                    played++;
                    break;
                }
            }
            Thread.sleep(25);
        }

        System.out.println("Played " + played + " out of " + rounds + " rounds");
        Thread.sleep(SleepTime * 40);

        assertTrue(played == rounds);
    }

    @Then("^Total amount of the scores and draws is \"([^\"]*)\"$")
    public void total_amount_of_the_scores_and_draws_is(String arg1) throws Throwable {
        String playerWins = getTextBy(By.id("playerScoreSpan"));
        String aiWins = getTextBy(By.id("aiScoreSpan"));
        String draws = getTextBy(By.id("drawSpan"));

        int total = Integer.parseInt(playerWins) + Integer.parseInt(aiWins) + Integer.parseInt(draws);
        assertTrue(total == Integer.parseInt(arg1));
    }

    @Given("^user plays \"([^\"]*)\" rounds against using an \"([^\"]*)\" pattern$")
    public void user_plays_rounds_agains_using_an_pattern(String rounds, String patternName) throws Throwable {
        switch (patternName) {
            case "repeating": {
                playRepeatingPattern(Integer.parseInt(rounds), "pprpsprpr");
            }
        }
    }

    @Given("^user plays \"([^\"]*)\" rounds against using the pattern \"([^\"]*)\"$")
    public void user_plays_rounds_against_using_the_pattern(String rounds, String pattern) throws Throwable {
        playRepeatingPattern(Integer.parseInt(rounds), pattern);
    }

    public void playRepeatingPattern(int rounds, String pattern) throws Throwable {
        String[] clicks = transformIntoButtons(pattern);
        int i = 0;
        int x = clicks.length;
        int y = 0;

        while (i < rounds) {
            user_clicks_button_by(By.id(clicks[y]));
            y++;
            if (y == x) {
                y = 0;
            }
            i++;
        }
    }

    public String[] transformIntoButtons(String shortMoveNames) {
        String[] buttonClicks = new String[shortMoveNames.length()];
        for (int i = 0; i < shortMoveNames.length(); i++) {
            if (shortMoveNames.charAt(i) == ('r')) {
                buttonClicks[i] = "btnRock";
                continue;
            }
            if (shortMoveNames.charAt(i) == ('p')) {
                buttonClicks[i] = "btnPaper";
                continue;
            }
            if (shortMoveNames.charAt(i) == ('s')) {
                buttonClicks[i] = "btnScissors";
                continue;
            }
        }
        return buttonClicks;
    }

    @Then("^player loses$")
    public void total_player_loses() throws Throwable {
        String playerWins = getTextBy(By.id("playerScoreSpan"));
        String aiWins = getTextBy(By.id("aiScoreSpan"));

        assertTrue(Integer.parseInt(playerWins) < Integer.parseInt(aiWins));
    }

    private String getTextBy(By by) {
        int trials = 0;
        while (trials++ < 10) {
            try {
                Thread.sleep(SleepTime);
                WebElement element = driver.findElement(by);
                if (element != null) {
                    return element.getText();
                }
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
        return "Element not found";
    }

    private void user_clicks_button_by(By by) throws Throwable {
        boolean found = false;
        int trials = 0;
        while (trials++ < 10) {
            try {
                Thread.sleep(SleepTime);
                WebElement element = driver.findElement(by);
                if (element != null) {
                    element.click();
                    found = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }

        if (!found) {
            System.out.println("Button " + by + " was never found....");
        }
    }

    private void clickLinkWithText(String text) {
        int trials = 0;
        while (trials++ < 5) {
            try {
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

}
