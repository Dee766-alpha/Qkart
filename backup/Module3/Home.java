package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Text;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // SLEEP_STMT_10: Wait for Logout to complete
            // Wait for Logout to Complete
            Thread.sleep(3000);
            //driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);


            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any
     * errors
     */
    public Boolean searchForProduct(String product) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Clear the contents of the search box and Enter the product name in the search
            // box
            //WebElement search_box = driver.findElement(By.xpath(:"//*[@id='root']/div[1]/iv/div[1]/div[2]/div")));
            WebElement search_box= driver.findElement(By.xpath("//*[@id='root']/div[1]/div/div[1]/div[2]/div/input"));
            
            search_box.clear();
            Thread.sleep(2000);
            search_box.sendKeys(product);
            Thread.sleep(2000);
            //WebDriverWait wait = new WebDriverWait(driver, 30);
            //wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("css-yg30e6"),text:"YONEX Smash Badminton Racquet));

            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>() {
        };
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Find all webelements corresponding to the card content section of each of
            // search results
            searchResults=driver.findElements(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div/div"));
            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Check the presence of "No products found" text in the web page. Assign status
            // = true if the element is *displayed* else set status = false
            WebElement no_products = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div/div[2]/div/h4"));
            if(no_products.isDisplayed()){
                status= true;
            }
            else{
                status = false;
            }

            
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            List<WebElement> titleElements = driver.findElements
            (By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']"));
            List<WebElement> addToCartElements =
            driver.findElements(By.xpath("//button[text()='Add to cart']"));
                //"//button[text()='Add to cart']"));
            for(int i =0 ;i< titleElements.size();i++){
                WebElement titleElement =titleElements.get(i);
                String title =titleElement.getText();
                if(title.equals(productName)){
                   WebElement addToCartElement = addToCartElements.get(i);
                    addToCartElement.click();
                }
            }
            Thread.sleep(2000);
            return true;
             
            //System.out.println("Unable to find the given product");
            //return false;
        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find and click on the the Checkout button
            WebElement checkOutButtonElement =
            driver.findElement(By.xpath("//button[text()='Checkout']"));
            checkOutButtonElement.click();
            status = true;
            return status;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart
     * operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5

            // Find the item on the cart with the matching productName

            // Increment or decrement the quantity of the matching product until the current
            // quantity is reached (Note: Keep a look out when then input quantity is 0,
            // here we need to remove the item completely from the cart)
            List<WebElement> cartTitleElements = driver
            .findElements(By.xpath("//div[@class='MuiBox-root css-zgtx0t']/div[2]/div[1]"));
            List<WebElement> quantityElements =
            driver.findElements(By.xpath("//div[@data-testid='item-qty']"));
            List<WebElement> removeElements =
             driver.findElements(By.xpath("//button/*[name()='svg'][@data-testid='RemoveOutlinedIcon']"));
             List<WebElement> addElements =
             driver.findElements(By.xpath("//button/*[name()='svg'][@data-testid='AddOutlinedIcon']"));
             
             for(int i=0;i< cartTitleElements.size();i++){
                WebElement cartTitleElement = cartTitleElements.get(i);
                String actualProductName =cartTitleElement.getText();
                if(actualProductName.equals(productName)){
                    while(true){
                        WebElement quantityElement = quantityElements.get(i);
                        String quantityString = quantityElement.getText();
                        int actualQuantity =Integer.parseInt(quantityString);
                        if(actualQuantity == quantity){
                            break;
                        }
                        if(actualQuantity < quantity){
                            WebElement addElement =addElements.get(i);
                            addElement.click();
                        } else if (actualQuantity > quantity){
                            WebElement removeElement = removeElements.get(i);
                            removeElement.click();
                        }
                        Thread.sleep(2000);
                        if(quantity == 0){
                            break ;
                        }
                    }
                }
             }


            return true;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println("exception occurred when updating cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6

            // Get all the cart items as an array of webelements

            // Iterate through expectedCartContents and check if item with matching product
            // name is present in the cart
            List<WebElement> cartItems = 
            driver.findElements(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[1]"));
            if(cartItems.size()== expectedCartContents.size()){
                for(int i=0;i<expectedCartContents.size();i++){
                if(!cartItems.get(i).getText().equals(expectedCartContents.get(i))){
                    return false;
                }


                }
            }
            return true;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
