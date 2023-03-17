package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import automationbase.AutomationBase;

public class FirstTestClass extends AutomationBase {

    @Test
    public static void accessTest() {
    	WebElement siteLogo = driver.findElement(By.className("logo"));
    	webDriverWait.until(ExpectedConditions.visibilityOf(siteLogo));
        System.out.println("Page loaded successfully.....");
    }

    @Test
    public static boolean isSearchBarPresent() {
        WebElement searchBar = driver.findElement(By.className("search-field"));
        webDriverWait.until(ExpectedConditions.visibilityOf(searchBar));
        return searchBar.isDisplayed();
    }

    @Test
    public static void searchAnItem(String itemName) {
        WebElement searchBar = driver.findElement(By.className("search-field"));
        webDriverWait.until(ExpectedConditions.visibilityOf(searchBar));
        if(searchBar.isDisplayed()) {
            searchBar.click();
            searchBar.clear();
            searchBar.sendKeys(itemName);
        }
        WebElement searchButton = driver.findElement(By.className("search-submit"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }
}
