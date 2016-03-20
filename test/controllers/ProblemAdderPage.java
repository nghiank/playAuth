package controllers;

import codegen.models.Type;
import play.test.TestBrowser;

import java.util.List;

import static org.fluentlenium.core.filter.FilterConstructor.withName;
import static org.fluentlenium.core.filter.FilterConstructor.withText;
import static org.junit.Assert.assertThat;

/**
 * Created by nghian on 3/15/16.
 */
public class ProblemAdderPage {

    public ProblemAdderPage(TestBrowser browser) {
        this.browser = browser;
    }

    private TestBrowser browser;

    public void setName(String name) {
        browser.fill("#name").with(name);
    }

    public void setDesc(String desc) {
        browser.fill("#desc").with(desc);
    }

    public void setReturnType(Type type) {
        browser.fillSelect("#returnType").withValue(type.toString());
    }

    public void setFunctionName(String funcName) {
        browser.fill("#functionName").with(funcName);
    }

    public void setParams(List<Type> types) {
        for(int i = 0; i < types.size(); ++i) {
            String id = "#params_" + i;
            String value = types.get(i).toString();
            browser.fillSelect(id).withValue(value);
        }
    }

    public void submit() {
        browser.click("#createProblem");
    }

    public boolean isAt() {
        return browser.title().contains("New problem");
    }

    public void setBrowser(TestBrowser browser) {
        this.browser = browser;
    }
}
