package swip.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.TimeoutException;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.locators.react.ReactByClassName;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static swip.locators.react.ReactByClassName.TRIGGER_CONTAINER;

@RunWith(BrowserRunner.class)
public class TriggerByClassName_v2_IT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/react-datepicker.html");
    }

    @Test(expected = TimeoutException.class)
    public void trigger() {
        browser.untilFound(TRIGGER_CONTAINER).click(ReactByClassName.TRIGGER_BY);
    }
}
