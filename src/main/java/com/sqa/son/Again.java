package com.sqa.son;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.PublicKey;

public class Again {
    ChromeDriver driver;
    @Before
    public void  Before(){
        System.out.println("Start");
        //Khai bao webdriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/Chrome/chromedriver.exe");
        //Khoi tao webriver
        driver = new ChromeDriver();
        //Su dung phuong thuc voi webdriver
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
    }
    @After
    public void After(){
        driver.quit();
        System.out.println("Finish");
    }
    @Test
    public void Test() throws InterruptedException{
        System.out.println("Start");
        String pageTitle = driver.getTitle();
        System.out.println("pageTitle: " + pageTitle);
        Assert.assertEquals("Google", pageTitle);

        clickElement("//a[text()='Đăng nhập']");
        inputToElement("//input[@type='email']", "phatnguyen@beeketing.net");
        clickElement("//button[child::span[text()='Tiếp theo']]");
        inputToElement("//input[@type='password']", "123456");
        clickElement("//button[child::span[text()='Tiếp theo']]");
    }
    public WebElement getElement(String xpath){
        return driver.findElement(By.xpath(xpath));
    }
    public void clickElement(String xpath){
        waitElementVisible(xpath);
        getElement(xpath).click();
    }
    public  String getElementText(String xpath){
        waitElementVisible(xpath);
        return  getElement(xpath).getText();
    }
    public void inputToElement(String xpath, String value){
        waitElementVisible(xpath);
        WebElement e = getElement(xpath);
        e.clear();
        e.sendKeys(value);
    }
    public void waitElementVisible(String xpath){
        WebDriverWait driverWait = new WebDriverWait(driver, 30);
        driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
    }
}





