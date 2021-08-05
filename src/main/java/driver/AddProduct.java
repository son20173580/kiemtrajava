package driver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import java.util.List;


public class AddProduct {
    ChromeDriver driver ;
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
    public void Before() {
        System.out.println("Start");
        //Khai bao webdriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/Chrome/chromedriver.exe");
        //Khoi tao webriver
        driver = new ChromeDriver();
        //Su dung phuong thuc voi webdriver

        driver.get("https://accounts.shopbase.com/sign-in");
        driver.manage().window().maximize();

    }

    @After
    public void After() {
        driver.quit();
        System.out.println("Finish");
    }

    @Test
    public void Test() throws InterruptedException {
        System.out.println("Start");
        String pageTitle = driver.getTitle();
        System.out.println("pageTitle: " + pageTitle);
        Assert.assertEquals("Sign In ~ ShopBase", pageTitle);

        inputToElement("//input[@id='email']","shopbase@beeketing.net");
        inputToElement("//input[@id='password']",";|Xh!Hz&Hgh0/m{");
        clickElement("//button[@type='submit']");
        // Vào shop au-ui-products
        clickElement("//li[@class='shopify box-create-shop']//following-sibling::li[@class='shopify']//a[@href='/open/?shop_data=au-ui-products.onshopbase.com&shop_name=au-ui-products&id_location=en']");
        clickElement("(//li[@class='pos-rlt menu li-product active']//child::a)[1]");
        clickElement("(//li[@class='li-last-step m-x-12'])[3]//child::a");
        clickElement("//span[@class='s-icon has-text-default is-small']//parent::button");
        inputToElement("//input[@placeholder='Short Sleeve T-Shirt']","Aó Khoác Mùa Đông");

        driver.switchTo().frame(0);
        inputToElement("//body[@id='tinymce']", "Hàng Việt Nam chất lượng cao");
        driver.switchTo().parentFrame();



        clickElement("//a[text()='Add image from URL']");

        inputToElement("//input[@id='url']","https://vcdn1-ione.vnecdn.net/2020/12/07/top1.jpg?w=900&h=540&q=100&dpr=1&fit=crop&s=m7g4eoHQiCPn1pcvYl3RQw");
        clickElement("(//button[@class='s-button is-primary'])[2]");

        inputToElement("//input[@id='price']","16");
        inputToElement("//input[@id='compare_price']","15");
        inputToElement("//input[@id='cost_price']","15.5");
        inputToElement("//input[@id='sku']","10000");
        inputToElement("//input[@id='barcode']","000 – 019");
        clickElement("//select//child::option[@value='shopbase']");
        inputToElement("//input[@id='quantity']","1234");
        clickElement("(//span[@class='s-check'])[1]");
        inputToElement("(//div[@class='s-input']//child::input)[5]","2");
        clickElement("//a[text()='Add custom option']");

        inputToElement("//input[@placeholder='Product type']","clothes");



        clickElement("(//div[@class='s-input'])[13]//child::input");
        clickElement("(//span[@class='s-dropdown-item'])[5]//child::span");
        inputToElement("//input[@placeholder='Search for collections']","n");
        clickElement("//span[@class='s-dropdown-item is-hovered']");
        inputToElement("//input[@placeholder=\"Nikola's Electrical Supplies\"]","Thanh Sơn");
        clickElement("//button[@class='s-button is-primary']");


        driver.close();





    }



}


