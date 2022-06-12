package com.enuygun.page;


import com.enuygun.utils.CustomLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private final CustomLogger logger = new CustomLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }

    protected void click(By by){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        try {
            element.click();
            logger.info("clicked to \'"+by.toString()+"\' element");
        }catch (Exception e){
            logger.info("could not clicked to \'"+by.toString()+"\' element");
        }
    }

    protected void write(By by,String text){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        try {
            element.sendKeys(text);
            logger.info("\'"+text+"\' is written to element \'"+by+"\'");
        }catch (Exception e){
            logger.error("\'"+text+"\' is not written to element \'"+by+"\'");
        }
    }

    protected void enter(By by){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        element.sendKeys(Keys.ENTER);
        logger.info("enter is sent to element '" +by.toString()+ "'");
    }


    protected void tab(By by){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        element.sendKeys(Keys.TAB);
        logger.info("tab is sent to element '" +by.toString()+ "'");

    }

    protected void waitSeconds(int seconds){
        try {
            Thread.sleep(seconds*1000);
            logger.info("waited  '" +seconds+ "' seconds");
        } catch (Exception e){
            logger.error("couldn't wait '" +seconds+ "' seconds");
        }
    }

    protected String getElementText(By by){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element.getText();
    }

    protected WebElement findElement(By by){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));;
        return element;
    }


    protected void clickWithOnFocus(By by){
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        try {
            element.click();
            logger.info("'" +by.toString()+ "' clicked with focus");
        }catch (Exception e){
            logger.error("'" +by.toString()+ "' could not clicked focus");
        }
    }

    protected boolean isChecked(By by){
        return findElement(by).isSelected();
    }

    protected String getAttribute(By by,String attributeName){
        return findElement(by).getAttribute(attributeName);
    }



    protected List<WebElement> getElementList(By by){
        List<WebElement> elementList = null;
        try {
            elementList=this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            logger.info("'" +by.toString()+ "' element list found.");
        }catch (Exception e){
            logger.error("'" +by.toString()+ "' element list not found.");
        }
        return elementList;
    }

    protected void hover(By by){
        Actions actions=new Actions(driver);
        WebElement element=this.wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        actions.moveToElement(element).build().perform();
        logger.info("'" +by.toString()+ "' hover to element.");
    }

    protected void visibilityOfElement(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void hoverToElement(WebElement element){
        Actions actions=new Actions(driver);
        actions.moveToElement(element).build().perform();
        logger.info("\'"+element.getText()+"\' hover to element.");
    }

    protected String getPageTitle(){
        return driver.getTitle();
    }

    protected boolean isDisplayed(By by){
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }

    protected int getRandomNumber(int limit){
        Random random=new Random();
        int randomNumber=random.nextInt(limit);
        logger.info("generated random number: "+randomNumber);
        return randomNumber;
    }

    protected void changeWindow(int windowNumber){
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(windowNumber));
        logger.info("switched to tab"+ (windowNumber+1));
    }


    protected int getElementListSize(By by){
        List<WebElement> elementList=getElementList(by);
        logger.info("'" +by.toString()+ "' element list size(): " +elementList.size() );
        return elementList.size();
    }

    protected WebElement getElementFromList(By by,int index){
        logger.info("get '" +index+ "'nd element from element list");
        return getElementList(by).get(index);
    }


    protected String getTextFromElementList(By by,int index){
        List<WebElement> elementList=getElementList(by);
        return elementList.get(index).getText();
    }


    protected void clear(By by){
        findElement(by).clear();
    }

    protected void clickElementFromElements(By by,String text) {
        List<WebElement> elementList = getElementList(by);
        for (WebElement element : elementList) {
            if(element.getText().equals(text)){
                try {
                    element.click();
                    logger.info("clicked to '" +by.toString()+ "' element");
                }catch (Exception e){
                    logger.info("could not clicked to '" +by.toString()+ "' element");
                }
                break;
            }
        }
    }

}