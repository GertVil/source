package swip.ch18datepicker.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch18datepicker.material_ui.v3.MaterialDatepickerPage;
import swip.framework.Browser;
import swip.framework.BrowserRunner;
import swip.tests.TestTimer;

import javax.inject.Inject;

import static java.time.Month.APRIL;
import static org.junit.Assert.assertEquals;

@RunWith(BrowserRunner.class)
public class MaterialDatepicker_v3_IT extends TestTimer {

    @Inject
    private Browser browser;

    @Before
    public void setup() {
        browser.get("/material-ui.html#/components/date-picker");
    }

    @Test
    public void pickADate() {
        new MaterialDatepickerPage(browser) {{
            pick(APRIL, 1, 2015);
            assertEquals("4/1/2015", super.getDate());
        }};
    }
}
