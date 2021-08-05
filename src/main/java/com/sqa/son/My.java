package com.sqa.son;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;

public class My {
    WebDriver driver;
    public WebElement getElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void waitElementVisible(String xpath) {
        WebDriverWait driverWait = new WebDriverWait(driver,50);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void inputToElement(String xpath, String value) {
        waitElementVisible(xpath);
        WebElement element = getElement(xpath);
        element.clear();
        element.sendKeys(value);
    }
    public void clickElement(String xpath){
        waitElementVisible(xpath);
        WebElement element = getElement(xpath);
        element.click();
    }
    @Before
    public void Before(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/Chrome/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://accounts.shopbase.com/open/?shop_data=au-ui-products.onshopbase.com&shop_name=au-ui-products&id_location=en");
        driver.manage().window().maximize();

    }
    @After
    public void After(){
    }
    @Test
    public void testCreateNewProduct(){
        waitElementVisible("//div[@class='unite-ui-login__title']//p[@class='hidden-xs hidden-sm title-dashboard']");
        inputToElement("//input[@id='email']","shopbase@beeketing.net");
        inputToElement("//input[@id='password']",";|Xh!Hz&Hgh0/m{");
        clickElement("//button[@type='submit']");
        waitElementVisible("//li[@class='shopify box-create-shop']//following-sibling::li[@class='shopify']//a[@href='/open/?shop_data=au-ui-products.onshopbase.com&shop_name=au-ui-products&id_location=en']");
        clickElement("//li[@class='shopify box-create-shop']//following-sibling::li[@class='shopify']//a[@href='/open/?shop_data=au-ui-products.onshopbase.com&shop_name=au-ui-products&id_location=en']");
        waitElementVisible("//a[@href='/admin/products' and @class='text-decoration-none']");
        //clickElement("//a[@href='/admin/products' and @class='text-decoration-none']");
        driver.findElement(By.xpath("//a[@href='/admin/products']")).click();
        waitElementVisible("//span[@id='modal-import']//following-sibling::button[@type='button']");
        clickElement("//span[@id='modal-import']//following-sibling::button[@type='button']");
        waitElementVisible("//input[@placeholder='Short Sleeve T-Shirt']");
        inputToElement("//input[@placeholder='Short Sleeve T-Shirt']","Jean");
        //WebElement iframe = driver.findElement(By.xpath("//iframe[@title='Rich Text Area. Press ALT-0 for help.']"));
        //driver.switchTo().frame(getElement("//iframe[@id='tiny-vue_44365308721627988784827_ifr']"));
        driver.switchTo().frame(getElement("//iframe[@title='Rich Text Area. Press ALT-0 for help.']"));
        inputToElement("//body[@id='tinymce']//p"," Jean for everyone");
        driver.switchTo().parentFrame();
        getElement("//input[@type='file']").sendKeys("src/test/resources/Pictures/jeans1.jpg");
        getElement("//input[@id='price']").sendKeys("5");
        getElement("//input[@id='compare_price']").sendKeys("1");
        getElement("//input[@id='cost_price']").sendKeys("2");
        getElement("//input[@id='sku']").sendKeys("100");
        getElement("//input[@id='barcode']").sendKeys("190699190699");
        getElement("//option[@value='shopbase']").click();
    }











}