package TiraLabrAI;

import TiraLab.Main;
import org.junit.rules.ExternalResource;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

class ServerRule extends ExternalResource {

    private final int port;
    ConfigurableApplicationContext app;

    public ServerRule(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        this.app = SpringApplication.run(Main.class);
    }

    @Override
    protected void after() {
        app.close();
    }
}
