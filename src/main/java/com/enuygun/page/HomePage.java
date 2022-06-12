package com.enuygun.page;

import com.enuygun.utils.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


public class HomePage extends BasePage{

    private final CustomLogger logger = new CustomLogger(HomePage.class);

    private final By departureInputFld = By.id("OriginInput");
    private final By toInputBox = By.id("DestinationInput");
    private final By oneWayCheckbox = By.id("oneWayCheckbox");
    private final By destinationList=By.className("city_code");
    private final By departureDateBtn = By.id("DepartureDate");
    private final By nextDateBtn = By.xpath("//div[contains(@aria-label, 'forward')]");
    private final By monthAndYearTxt = By.xpath("(//*[contains(@class, 'CalendarMonth_caption')]/strong)[2]");
    private final By dateDayTxt = By.xpath("(//*[contains(@class, 'CalendarMonth_table')])[2]/tbody/tr/td/div");
    private final By nonStop = By.id("transitFilter");
    private final By passengerSelectBtn = By.xpath("//*[contains(@class,'passenger-select-button')]");
    private final By passengerTitles = By.xpath("//*[@data-testid='passengerTitle']");
    private final By passengerOkBtn = By.xpath("//*[@data-testid='okButtonMulti']");
    private final By findFlightBtn = By.xpath("//*[@data-testid='formSubmitButton']");
    private final By headerTxt = By.className("info");

    public HomePage(WebDriver driver) {
        super(driver);
    }



    public HomePage enterDeparture(String origin){
        write(departureInputFld,origin);
        clickElementFromElements(destinationList,origin);
        return this;
    }



    public HomePage enterDestination(String destination) {
        write(toInputBox,destination);
        clickElementFromElements(destinationList,destination);
        return this;
    }


    public HomePage enterDepartureDate(String dateTime){
        click(departureDateBtn);

        selectDate(dateTime);

        return this;
    }


    public HomePage enterReturnDate(String dateTime){

        click(oneWayCheckbox);
        selectDate(dateTime);
        return this;
    }

    public HomePage clickNonStop() {
        click(nonStop);
        return this;
    }

    public HomePage selectPassengerAndCount(String s, int clickCount) {
        click(passengerSelectBtn);
        List<WebElement> passengerList = getElementList(passengerTitles);
        int index = -1;
        for (int i = 0; i < passengerList.size(); i++) {
            if(passengerList.get(i).getText().equals(s)){
               index = i;
               break;
            }
        }
        if(passengerList.get(index).getText().equals("Yetişkin")){
            clickCount--;
        }
        By xpathOfButton = By.xpath("//*[@data-testid='passengerCountIncrease-"+index+"']");
        for (int i = 0; i < clickCount; i++) {
            click(xpathOfButton);
        }

        return this;
    }


    public HomePage clickClassType(String classType){
        By passengerClassType = By.xpath("//button[contains(text(), '"+classType+"')]");
        click(passengerClassType);
        click(passengerOkBtn);
        return this;
    }

    private void selectDate(String dateTime) {
        String currentMonth =  findElement(monthAndYearTxt).getText().split(" ")[0];

        String currentYear =  findElement(monthAndYearTxt).getText().split(" ")[1];

        String[] parameterizedDate = dateTime.split(" ");

        String parameterizedDay = parameterizedDate[0];
        String parameterizedMonth = parameterizedDate[1];
        String parameterizedYear = parameterizedDate[2];

        while(!(parameterizedMonth.equals(currentMonth) && parameterizedYear.equals(currentYear))){
            click(nextDateBtn);
            currentMonth =  findElement(monthAndYearTxt).getText().split(" ")[0];
            currentYear =  findElement(monthAndYearTxt).getText().split(" ")[1];
        }

        clickElementFromElements(dateDayTxt,parameterizedDay);
    }


    public HomePage clickFindFlightButton() {
        click(findFlightBtn);
        return this;
    }

    public String returnHeader(){
        return findElement(headerTxt).getText();
    }
}
