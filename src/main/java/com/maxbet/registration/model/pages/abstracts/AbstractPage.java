package com.maxbet.registration.model.pages.abstracts;

import com.maxbet.registration.config.driver.BrowserDriverInit;
import com.maxbet.registration.model.pages.MaxebtMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by rodicad on 21/09/2017.
 */
@Component
public class AbstractPage extends  AbstractDriver {

    public void open (String url, Class instance) {
        getWebDriver().get( url );
        initElements(instance);
    }

    public void initElements(Class instance) {
        PageFactory.initElements( super.getWebDriver(), instance );


    }

    public String getPageTitle() {
        return getWebDriver().getTitle();
    }


}
