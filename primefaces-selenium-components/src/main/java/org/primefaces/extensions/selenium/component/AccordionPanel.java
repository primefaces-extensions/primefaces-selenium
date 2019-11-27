/**
 * Copyright 2011-2019 PrimeFaces Extensions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.extensions.selenium.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.primefaces.extensions.selenium.PrimeSelenium;

import java.util.List;
import java.util.stream.Collectors;
import org.primefaces.extensions.selenium.component.base.AbstractComponent;
import org.primefaces.extensions.selenium.component.base.ComponentUtils;

/**
 * Component wrapper for the PrimeFaces {@code p:accordionPanel}.
 */
public abstract class AccordionPanel extends AbstractComponent {

    @FindBy(css = ".ui-accordion-header")
    private List<WebElement> headers;

    /**
     * Toggle the tab denoted by the specified index.
     *
     * @param index the index of the tab to expand
     */
    public void toggleTab(int index) {
        if (ComponentUtils.hasAjaxBehavior(getRoot(), "tabChange")) {
            PrimeSelenium.guardAjax(headers.get(index)).click();
        }
        else {
            headers.get(index).click();
        }
    }

    /**
     * Provides the header of an {@link AccordionPanel} tab at the specified index.
     *
     * @param index the index
     * @return the header of the {@link AccordionPanel} tab
     */
    public String getTabHeader(int index) {
        return headers.get(index).getText();
    }

    /**
     * Provides the headers of the {@link AccordionPanel} tabs in their order.
     *
     * @return a copy of the headers in order
     */
    public List<String> getTabHeaders() {
        return headers.stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
    }
}
