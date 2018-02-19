package setup;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

import static setup.DriverSetup.getDriver;

public class CommonWaits {

    Logger logger = Logger.getLogger(Log.class.getName());


    private static final int DEFAULT_TIMEOUT = 10;

    public static CommonWaits getWait(){
        CommonWaits commonWaits = new CommonWaits();
        return commonWaits;
    }

    public CommonWaits waitForElementIsVisible(WebElement webElement){
        logger.info("Waiting for Element to be visible " + webElement.toString());
        try {
            new WebDriverWait(getDriver(), DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(webElement));
            return this;
        }catch (WebDriverException e){
            throw new Error("Element was not visible " + webElement.toString());
        }
    }

    public CommonWaits waitForElementIsVisible(By location){
        return  waitForElementIsVisible(getDriver().findElement(location));
    }


    public CommonWaits waitForElementIsVisible(String cssSelector){
        return  waitForElementIsVisible(By.cssSelector(cssSelector));
    }



    public CommonWaits waitForElementIsClickable(WebElement webElement){
        logger.info("Waiting for Element to be clickable " + webElement.toString());
        try {
            new WebDriverWait(getDriver(), DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
            return this;
        }catch (WebDriverException e){
            throw new Error("Element was not clickable " + webElement.toString());
        }
    }

    public CommonWaits waitForUrlChangedFrom(String oldUrl){
        logger.info("Waiting for url change from " + oldUrl);
        new WebDriverWait(getDriver(), DEFAULT_TIMEOUT)
                .until((ExpectedCondition<Boolean>) driver -> {
                    String url = driver != null ? getDriver().getCurrentUrl(): null;
                    return url.contains(oldUrl);
                });
        return this;
    }

    public static CommonWaits isLoaded(){
        CommonWaits commonWaits = new CommonWaits();
        return commonWaits;
    }
}