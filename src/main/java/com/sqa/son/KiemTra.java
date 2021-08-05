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

import java.util.List;


import static java.lang.Thread.sleep;

public class KiemTra {
    static ChromeDriver driver;

    public WebElement getElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }
    public void verify(String xpath, String value) {
        waitElementVisible(xpath);
        WebElement element = getElement(xpath);
        element.getText();
        if(element.getText()==value){
            System.out.println("Testcase Pass");
        }
        else{System.out.println("Testcase Fail");}

    }
    public String gettext(String xpath) {
        waitElementVisible(xpath);

        return driver.findElement(By.xpath(xpath)).getText();

    }

    public void waitElementVisible(String xpath) {
        WebDriverWait driverWait = new WebDriverWait(driver, 60);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void inputToElement(String xpath, String value) {
        waitElementVisible(xpath);
        WebElement element = getElement(xpath);
        element.clear();
        element.sendKeys(value);
    }

    public void clickElement(String xpath) {
        waitElementVisible(xpath);
        WebElement element = getElement(xpath);
        element.click();
    }


    @Before
    public void Before() {
        System.out.println("Start");
        //Khai báo webdriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/Chrome/chromedriver.exe");
        //Khoi tao webriver
        driver = new ChromeDriver();
        //Su dung phuong thuc voi webdriver

        driver.get("https://au-webhook-adc1.onshopbase.com/?fbclid=IwAR3oW4IwxJ7PEptadfe2sCxDqGo4Dno1uwqP2gH8uFnMLgcMwqr-HzgASaY");
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
        Assert.assertEquals("au-webhook-adc1", pageTitle);


        clickElement("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/header[1]/div[1]/div[1]/div[3]/ul[1]/li[1]/a[1]");
        // search the product

        List<WebElement> products = driver.findElements(By.xpath("//a[contains(text(),'Dây Kháng Lực Đa năng Tập Gym')]"));

        for (WebElement product : products) {

            System.out.println(product.getText());
        }


        clickElement("//a[contains(text(),'Dây Kháng Lực Đa năng Tập Gym')]");

        // chọn số lượng
        clickElement("//body/div[@id='app']/div[1]/div[1]/div[5]/div[1]/section[1]/div[1]/section[1]/div[2]/div[2]/div[1]/button[2]");

        // Thêm sản phẩm vào giỏ hàng
        clickElement("//button[@id='add-to-cart']");
        Thread.sleep(3000);
        // click vào giỏ hàng
        clickElement("//header/div[1]/div[1]/div[4]/div[1]/button[2]");
        Thread.sleep(5000);


//Kiểm tra sản phẩm trong giỏ hàng
        // Kiểm tra sản phẩm có trong giỏ hàng hay không ?


        verify("//a[contains(text(),'Dây Kháng Lực Đa năng Tập Gym')]","Dây Kháng Lực Đa năng Tập Gym");


// DISCOUNT
        clickElement("//body/div[@id='app']/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/button[2]");
        inputToElement("//body/div[@id='app']/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]", "TESTING");
        clickElement("//button[contains(text(),'Apply')]");


        // CHECK OUT

        //email
        inputToElement("//input[@id='checkout_shipping_address_email']", "nguyenthithanhson1999@gmail.com");
        // radiobutton
        clickElement("//body/div[@id='app']/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/label[1]/span[1]");
        // lastname
        inputToElement("//input[@id='checkout_shipping_address_last_name']", "Son");
        // address
        inputToElement("//input[@id='checkout_shipping_address_address_line1']", "637 Truong Dinh");
        // Apartment
        inputToElement("//input[@id='checkout_shipping_address_address_line2']", "Thien Son");
        // Country
        clickElement("//select[@id='checkout_shipping_address_country_name']");
        clickElement("//option[contains(text(),'United States')]");


        // zip code
        inputToElement("//input[@id='checkout_shipping_address_zip']", "2");
        clickElement("//body/div[@id='app']/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/div[6]/div[1]/div[2]/div[1]/a[1]/div[1]/span[1]");

        // phone
        inputToElement("//input[@id='checkout_shipping_address_phone']", "086860725");
        //save infomations
        clickElement("(//span[@class='s-check'])[2]");
        // Continue to shipping
        clickElement("//button[contains(text(),'Continue to shipping method')]");

//Shipping method
        clickElement("//button[contains(text(),'Continue to payment method')]");

// Payment method
        // Card number
        driver.switchTo().frame(0);
        inputToElement("//input[@name='cardnumber' and @placeholder='Card number']", "4242 4242 4242 4242");
        driver.switchTo().parentFrame();
        // Cardholder number
        inputToElement("//body/div[@id='app']/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/fieldset[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]", "Thanh");
        // MM/YY
        inputToElement("//div[@id='stripe-card-expiry']", "09 / 23");
        //CVV
        inputToElement("//input[@name='cvc']", "123");
        //Billing address
        clickElement("//body/div[@id='app']/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/fieldset[1]/div[1]/div[1]/div[1]/label[1]/span[1]");

        // Complete order
        clickElement("//button[contains(text(),'Complete order')]");

// Verify Order
        // Verify discount, total


        String discountCodeDB = "TESTING";
        String discountValueDB = "5";
        String discountCodeSF = "";
        String discountValueSF = "-$5.00";


        //1. verify discount code

        verify("//span[contains(text(),'TESTING')]",discountCodeDB);

        // 2. Verify discount value

        verify("//span[contains(text(),'- $5.00')]","-$5.00");

        // 3.Verify email
        verify("//p[contains(text(),'mmmm@gmail.com')]","nguyenthithanhson1999@gmail.com");
        // 4.Verify total
        String total = gettext("//span[@class='payment-due__price']");
        verify("//body/div[@id='app']/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]/div[2]/table[2]/tfoot[1]/tr[1]/td[2]/span[2]",total);
        // 5.Verify Shipping address
        verify("(//address[@class='address'])[1]","Son"+"637 Truong Dinh"+"Thien Son"+"Washington"+"20001"+"District of Columbia"+"United States"+"086860725");

// CHECK
        //login
        inputToElement("//input[@id='email']","shopbase2@beeketing.net");
        inputToElement("//input[@id='password']","*esAS!z(:YeZ-5q");
        clickElement("//button[@type='submit']");
        // Vào shop
        clickElement("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[137]/a[1]/span[1]/span[1]");
        // Click Order
        clickElement("(//div[@class='d-flex justify-content-space-between'])[2]//parent::a");
        clickElement("(//tr[@class='cursor-default'])[17]");
        // CHECK THÔNG TIN ORDER
        // product
        verify("//p[@class='unfulfilled-card__name-product']//child::a","Dây Kháng Lực Đa năng Tập Gym");
        // total
        verify("//td[contains(text(),'Paid by customer')]//following::td","$29.99");
        // discount value
        verify("//span[contains(text(),'-$5.00')]","-$5.00");
        // shipping address
        verify("//body/div[@id='app']/div[1]/main[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[2]/section[1]/div[4]/div[1]/div[2]/div[1]/div[1]","Son"+"637 Truong Dinh"+"Thien Son"+"Washington"+"20001"+"District of Columbia"+"United States"+"086860725");

    }


    }
