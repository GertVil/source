package swip.ch08unicorns.windows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.SeleniumWebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
@Config(exclude = {"browserName=safari"})
public class NewWindowStrategyIT {
    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void openNewWindow() throws Exception {
        driver.get(baseUrl + "/open-a-new-window.html");

        new WindowHandler(driver)
                .openWith(() -> driver.findElement(By.tagName("a")).click())
                .identifiedBy((String handle, String originalWindowHandle) -> !handle.equals(originalWindowHandle))
                .then(() -> assertEquals("You Are In The New Window", driver.findElement(By.tagName("h1")).getText()));

    }
}
