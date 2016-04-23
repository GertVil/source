package swip.ch06problems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

@RunWith(WebDriverRunner.class)
public class ExplicitFluentWaitIT {
    private final ExpectedCondition<WebElement> slowLoadingTextIsVisible = ExpectedConditions.visibilityOfElementLocated(By.id("theText"));
    @Inject
    private WebDriver driver;

    @Test
    public void exampleFluentWait() throws Exception {

        driver.get("/slow-loading-elements.html");

        driver.findElement(By.id("fadeInText")).click();

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(3, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS);

        WebElement paraElement = wait
                .withMessage("could not find the slowly loading text") // <3>
                .until(
                        ExpectedConditions
                                .visibilityOfElementLocated(By.id("theText")) // <4>
                );

        assertEquals("Some slowly loading text.", paraElement.getText());
    }

    @Test
    public void exampleFluentWaitWith() throws Exception {

        driver.get("/slow-loading-elements.html");

        driver.findElement(By.id("fadeInText")).click();

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(1, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.MILLISECONDS);

        wait
                .until(slowLoadingTextIsVisible);
    }


    @Test
    public void exampleFluentWaitWithTimeout() throws Exception {

        driver.get("/slow-loading-elements.html");
        try {
            new FluentWait<>(driver)
                    .withTimeout(1, TimeUnit.SECONDS)
                    .pollingEvery(10, TimeUnit.MILLISECONDS)
                    .withMessage("could not find the slowly loading text")
                    .until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(By.id("theText"))
                    );
            fail();
        } catch (TimeoutException expected) {
            assertThat(expected.getMessage(), startsWith("Timed out after 1 seconds: could not find the slowly loading text"));
        }
    }

    @Test
    public void exampleFluentWaitEffectivelyLogicalAnd() throws Exception {

        driver.get("/slow-loading-elements.html");

        driver.findElement(By.id("fadeInText")).click();

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(1, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.MILLISECONDS);


        WebElement paraElement = wait
                .withMessage("could not find the slowly loading text")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("theText")));

        wait
                .until(ExpectedConditions.textToBePresentInElement(paraElement, "Some slowly loading text."));
    }
}
