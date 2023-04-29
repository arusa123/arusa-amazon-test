import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.Utilities;

import java.util.ArrayList;
import java.util.List;

public class TestSuite extends Utilities {
    @Before
    public void setUpBrowser() {
        openBrowser("Chrome", "https://www.amazon.co.uk/");
    }

    //    1. Open the url https://www.amazon.co.uk/
    @Test
    public void verifyLaptop() throws InterruptedException {
//    2. Type "Dell Laptop" in the search box and press enter or click on search Button.
        clickOnElement(By.id("sp-cc-accept"));
        mouseHoverOnElement(By.id("twotabsearchtextbox"));
        sendTextToElement(By.id("twotabsearchtextbox"), "Dell Laptop");
        clickOnElement(By.xpath("(//input[@class='nav-input nav-progressive-attribute'])[2]"));

//    3. Click on the checkbox brand Dell on the left side.
        clickOnElement(By.linkText("Dell"));


        Thread.sleep(2000);


//    4. Verify that the  30(May be different) products are displayed on the page.
        int size = driver.findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/div[@data-uuid]/div/div/div/div/div/div[2]/div/div/div/h2/a/span[@class='a-size-medium a-color-base a-text-normal']")).size();
        Assert.assertEquals("Size is not correct", 24, size);

//    5. Print all product names in the console.
        boolean productfound = false;
        while (!productfound) {
            List<WebElement> list = driver.findElements(By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']/a/span[@class='a-size-medium a-color-base a-text-normal']"));
            ArrayList<String> productList = new ArrayList<>();
            for (WebElement e : list) {
                productList.add(e.getText());
            }
            System.out.println(productList);

            Thread.sleep(1000);
//    6. Click on the product name 'Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3" FHD TOUCH'
            List<WebElement> listOfProducts = driver.findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/div[@data-uuid]/div/div/div/div/div/div[2]/div/div/div/h2"));
            ArrayList<String> products = new ArrayList<>();
            for (WebElement f : listOfProducts) {
                products.add(f.getText());
            }
            System.out.println(products);

            for (String name : products) {
                if (name.equalsIgnoreCase("Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3\" FHD TOUCH")) {
                    clickOnElement(By.xpath("//span[contains(text(),'Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3\" FHD TOUCH')]"));
                    productfound = true;
                    break;
                }
            }
            if (!productfound && !products.contains("Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3\" FHD TOUCH")) {
                clickOnElement(By.xpath("//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']"));
            } else {
                break;
            }
        }

//    7. Varify the Product name 'Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3" FHD TOUCH'
        verifyText(By.xpath("//span[@id='productTitle']"), "Dell LATITUDE 5300 LAPTOP CORE I5 8365u 8GB 250GB SSD 13.3\" FHD TOUCH");
//    8. Close the Browser.

        //closeBrowser();

    }
}