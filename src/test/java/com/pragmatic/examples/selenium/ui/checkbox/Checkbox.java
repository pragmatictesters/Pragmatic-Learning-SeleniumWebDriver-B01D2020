package com.pragmatic.examples.selenium.ui.checkbox;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class Checkbox implements ICheckbox, WrapsElement {


    private WebElement element;

    public Checkbox(WebElement element) {
        String tagName = element.getTagName();
        String type = element.getAttribute("type");


        if (tagName != null & type != null & tagName.equalsIgnoreCase("input") & type.equalsIgnoreCase("checkbox")) {
            this.element = element;

        } else {
            throw new UnexpectedTagNameException("Checkbox", tagName);
        }


    }

    @Override
    public void check() {

        if (!element.isSelected() & element.isEnabled() & element.isDisplayed()) {
            element.click();
        }

    }

    @Override
    public void uncheck() {
        if (element.isSelected() & element.isEnabled() & element.isDisplayed()) {
            element.click();
        }
    }

    @Override
    public void toggle() {
        if (element.isEnabled() & element.isDisplayed()) {
            element.click();
        }
    }

    @Override
    public boolean isChecked() {
        return element.isSelected();
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }
}
