package controllers;

import codegen.models.Type;
import org.junit.Before;
import org.junit.Test;
import play.test.Helpers;
import play.test.WithBrowser;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProblemAdderTest extends WithBrowser {

    public static final String PROBLEM_PAGE = "/problem";

    @Before
    public void setUp() throws Exception {
        browser.goTo(PROBLEM_PAGE);
    }

    @Test
    public void runHappilyWithProperInputForAllReturnType() {
        for(Type t: Type.values()) {
            if (t == Type.NONE) continue;
            browser.goTo(PROBLEM_PAGE);
            ProblemAdderPage page = setUpValidInput();
            page.setReturnType(t);
            page.submit();
            assertFalse("Expect returnType " + t.toString() + " to work properly",
                    page.isAt());
        }
    }

    @Test
    public void runHappilyWithProperInputForParam0Type() {
        for(Type t: Type.values()) {
            if (t == Type.NONE) continue;
            browser.goTo(PROBLEM_PAGE);
            ProblemAdderPage page = setUpValidInput();
            List<Type> params = new ArrayList<Type>();
            params.add(t);
            page.setParams(params);
            page.submit();
            assertFalse("Expect param0 " + t.toString() + " to work properly",
                    page.isAt());
        }
    }

    @Test
    public void emptyNameIsNotAllow() {
        ProblemAdderPage p = setUpValidInput();
        p.setName(" ");
        assertTrue(p.isAt());
    }

    private ProblemAdderPage setUpValidInput() {
        ProblemAdderPage page = new ProblemAdderPage(browser);
        page.setName("sort Array");
        page.setDesc("Given an array, please sort the array");
        page.setReturnType(Type.VEC_STRING);
        page.setFunctionName("sortArray");
        List<Type> types = new ArrayList<Type>();
        types.add(Type.VEC_INT);
        page.setParams(types);
        return page;
    }
}
