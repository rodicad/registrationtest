package com.maxbet.registration.model.pages;

import com.maxbet.registration.config.BrowserController;
import com.maxbet.registration.model.components.HeaderComponent;
import com.maxbet.registration.model.components.RegistrationForm;
import com.maxbet.registration.model.components.SportsPromos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource( "classpath:application.properties" )
public class MaxebtMainPage extends  AbstractPage{

    @Value( "${maxbet.main.page.url}" )
    public String maxbetPageUrl;

    private HeaderComponent header;

    private SportsPromos sportsPromos;

    private RegistrationForm registrationForm;

    public MaxebtMainPage( BrowserController browserController ) {
        super( browserController );
    }

    public void open() {
        super.open( maxbetPageUrl );
    }

//    public void open(BrowserController browserController) throws Exception {
//        super.open( maxbetPageUrl );
//
////        Assert.assertTrue( waitElementToBePresent( By.id( "results__container" ), 20 ) != null );
////        PageFactory.initElements( browserController.getDriver(), MainResultsPage.class );
////        Thread.sleep( 2000 );
////        System.out.println( "title is: " + resultsTitle.getText() );
////        super.open(maxbetPageUrl);
//    }



}
