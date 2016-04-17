package swip.ch18datepicker.jsdatepick;

import swip.framework.Browser;
import swip.framework.ElementVisible;
import swip.framework.datepicker.DayPicker;


import static swip.locators.jsdatepick.JsDatepickByClassName.CALENDAR;
import static swip.locators.TagName.DIV;

public class JsDayPicker implements DayPicker {

    private Browser browser;

    public JsDayPicker(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void pick(int day) {
        browser.untilFound(CALENDAR).findElements(DIV)
            .filter(e -> e.getText().equals(String.valueOf(day)))
            .findFirst()
            .get()
            .click();
        browser.until(new ElementVisible(CALENDAR).negate());
    }
}
