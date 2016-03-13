package controllers;

import org.junit.Test;
import play.test.Helpers;
import play.test.WithBrowser;

import static junit.framework.TestCase.assertNotNull;

public class ProblemAdderTest extends WithBrowser {

    @Test
    public void runInBrowser() {
        browser.goTo("/problem");
        assertNotNull(browser.$("title").getText());
    }

}
